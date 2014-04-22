package org.jdamico.secnote;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.jdamico.secnote.commons.AppMessages;
import org.jdamico.secnote.commons.Constants;
import org.jdamico.secnote.commons.SecNoteException;
import org.jdamico.secnote.commons.StaticObj;
import org.jdamico.secnote.commons.Utils;
import org.jdamico.secnote.crypto.CryptoUtils;
import org.jdamico.secnote.dataobjects.NoteItemObj;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecNoteEditorActivity extends Activity {
	
	TextView note_title = null;
	TextView note_content = null;
	Button note_save_button = null;
	Button note_del_button = null;
	String key = null;
	Context currentCtx = null;
	String filename = null;
	AlertDialog.Builder builder = null;
	DialogInterface.OnClickListener dialogClickListener = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secnote_editor);
		((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
		
		try {
			key = CryptoUtils.getInstance().retrieveKeyFromCache(getApplicationContext());
		} catch (SecNoteException e) {
			Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
		}
		note_save_button = (Button) findViewById(R.id.noteSaveButton);
		note_del_button = (Button) findViewById(R.id.noteDelButton);
		note_title = (TextView) findViewById(R.id.noteTitleEditText);
		note_content = (TextView) findViewById(R.id.noteContentEditText);
		
		
		if(StaticObj.NOTE_MD5 != null){
			filename = StaticObj.NOTE_MD5;
			note_del_button.setEnabled(true);
			
			getDataFromExistentNote();
			
		}else{
			note_del_button.setEnabled(false);
		}
		
		note_save_button.setText(AppMessages.getInstance().getMessage("GLOBAL.note_save_button"));
		note_del_button.setText(AppMessages.getInstance().getMessage("GLOBAL.note_del_button"));
		
		
		
		currentCtx = this;
		
		
		dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		        	String notesDir = Utils.getInstance().getAppContentDir();
		            File fDel = new File(notesDir+StaticObj.NOTE_MD5+"."+Constants.APP_NAME);
		            fDel.delete();
		            Intent intent = new Intent(currentCtx, SecNoteListActivity.class);
					intent.putExtra("isConfigExistent", true);
					startActivityForResult(intent, 0);
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            break;
		        }
		    }
		};
		builder = new AlertDialog.Builder(this);
		builder.setMessage(AppMessages.getInstance().getMessage("GLOBAL.confirm_del")).setPositiveButton(AppMessages.getInstance().getMessage("GLOBAL.yes"), dialogClickListener).setNegativeButton(AppMessages.getInstance().getMessage("GLOBAL.no"), dialogClickListener);
		
		note_del_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				builder.show();
				InputMethodManager inputManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE); 
				inputManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); 
			}
		});
		
		note_save_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				
				try {
					 
					
					String input = (note_title.getText().toString() + note_content.getText().toString());
					if(filename == null) filename = Utils.getInstance().getMd5FromString(input+String.valueOf(new Date().getTime()));
					//Toast.makeText(getApplicationContext(), filename, Toast.LENGTH_LONG).show();
					
					byte[] plainContent = note_content.getText().toString().getBytes();
					byte[] cipherContent = CryptoUtils.getInstance().enc(getApplicationContext(), key, plainContent, Utils.getInstance().getConfigFile(getApplicationContext()).getEncAlgo());
					
					String hexCipherContent = Utils.getInstance().byteArrayToHexString(cipherContent);
					
					String ts = Utils.getInstance().getCurrentDateTimeFormated("dd/MMM/yyyy HH:mm:ss");
					
					//Toast.makeText(getApplicationContext(), ts, Toast.LENGTH_LONG).show();
					
					
					NoteItemObj note = new NoteItemObj(note_title.getText().toString(), filename, hexCipherContent, ts);
					String noteXml = Utils.getInstance().convertNoteObj2XmlStr(note);
					
					String notesDir = Utils.getInstance().getAppContentDir();
					Utils.getInstance().byteArrayToFile(noteXml.getBytes(), notesDir+filename+"."+Constants.APP_NAME);
					
					Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage("GLOBAL.note_saved_success"), Toast.LENGTH_LONG).show();
					
				} catch (SecNoteException e) {
					Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
				} catch (UnsupportedEncodingException e) {
					Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
				}
				
				
				
				
				

			}
		});
		
		

	}

	private void getDataFromExistentNote() {
		String dir = Utils.getInstance().getAppContentDir();
		String file = dir+StaticObj.NOTE_MD5+"."+Constants.APP_NAME;

		try {
			List<NoteItemObj> noteObj = Utils.getInstance().convertXmlStrNoteLst(Utils.getInstance().getStringFromFile(file));
			
			byte[] cipherContent = Utils.getInstance().hexStringToByteArray(noteObj.get(0).getNoteContent());
			byte[] plainContent = CryptoUtils.getInstance().dec(this, CryptoUtils.getInstance().retrieveKeyFromCache(this), cipherContent, Utils.getInstance().getConfigFile(this).getEncAlgo());
			
			note_title.setText(new String(noteObj.get(0).getNoteTitle()));
			note_content.setText(new String(plainContent));
			
			
		} catch (SecNoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sec_note_editor, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(currentCtx, SecNoteListActivity.class);
		intent.putExtra("isConfigExistent", true);
		startActivityForResult(intent, 0);
	}

}
