package org.jdamico.secnote;

/*
 * This file is part of SECNOTE (written by Jose Damico).
 * 
 *    SECNOTE is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License (version 2) 
 *    as published by the Free Software Foundation.
 *
 *    SECNOTE is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with SECNOTE.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.io.File;

import org.jdamico.secnote.commons.AppMessages;
import org.jdamico.secnote.commons.Constants;
import org.jdamico.secnote.commons.SecNoteException;
import org.jdamico.secnote.commons.StaticObj;
import org.jdamico.secnote.commons.Utils;
import org.jdamico.secnote.crypto.CryptoUtils;
import org.jdamico.secnote.dataobjects.ConfigObj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.ServerManagedPolicy;
*/

public class SecNoteMainActivity extends Activity {

	Button new_note_button = null;
	Button saved_notes = null;
	Button config_button = null;
	Boolean isConfigExistent = false;
	TextView chachedMemTv = null;
	String key = null;
	
	private static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgtYOOhJn7JiHawr6kcrgGCoOuWB5brE6EB/2r478mrf1atO5fmXODR4wAKWqIo7s3IjLm16Nm+MaoFhRyYDMohSmNMJR3zjj3h+C1jhaBVKOgq50Sqc2V+SAY8kCkBJhZAhmL8euFt4ntv3ykkYNdPMo9182autvPgxWlIFfN674Hv47u/ghhUAH0ZPThBsCKWZM43DOcAuWZ3DF2HCZ+Z3V2KLM7RpwR+/yGDMq71vW5qStHgl25jZ8yjqpFt7H51Az1Ug/5TqL/a2wI12vaQJ7ArWZIA0rYzq/TONmHm/qqmocmUFjpGVV5/jgRs+8hTYRSgIfkX9ywBu7WpYGdwIDAQAB";
	private static final byte[] SALT = new byte[] { 111, -41, -62, 12, 114, -121, -125, -26, -38, -31, 42, 14, 115, 17, -81, 75, 75, 53, -5, 115 };
	
	/*
	private LicenseCheckerCallback mLicenseCheckerCallback;
    private LicenseChecker mChecker;
    private Handler mHandler;
    */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secnote_main);
		
		chachedMemTv = (TextView) findViewById(R.id.mem_key_textView);

		/*
		mHandler = new Handler();
		mLicenseCheckerCallback = new MyLicenseCheckerCallback();
		String android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);
        mChecker = new LicenseChecker(this, new ServerManagedPolicy(this, new AESObfuscator(SALT, getPackageName(), android_id)), BASE64_PUBLIC_KEY);
        doCheck();
		*/
		
		try {
			key = CryptoUtils.getInstance().retrieveKeyFromCache(getApplicationContext());
		} catch (SecNoteException e) {
			Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
		}

		new_note_button = (Button) findViewById(R.id.cam_button);
		saved_notes = (Button) findViewById(R.id.gallery_button);
		config_button = (Button) findViewById(R.id.config_button);
		
		
		new_note_button.setText(AppMessages.getInstance().getMessage("GLOBAL.cam_button"));
		saved_notes.setText(AppMessages.getInstance().getMessage("GLOBAL.gallery_button"));
		config_button.setText(AppMessages.getInstance().getMessage("GLOBAL.config_button"));


		Context context = getApplicationContext();
		ConfigObj config = null;
		try {
			config = Utils.getInstance().getConfigFile(context);
		} catch (SecNoteException e) {
			Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
		}
		if(null == config){
			new_note_button.setEnabled(false);
			saved_notes.setEnabled(false);
		}else{
			isConfigExistent = true;
			
			
			
			if(key != null  && Utils.getInstance().isAuthenticated(getApplicationContext(), key)) chachedMemTv.setText(AppMessages.getInstance().getMessage("YapeaMainActivity.onCreate.keyInCache"));
			else{
				Intent intent = new Intent(context, SecNoteAuthActivity.class);
				startActivityForResult(intent, 0);
			}

			new_note_button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if(key != null && Utils.getInstance().isAuthenticated(v.getContext(), key)){
						StaticObj.NOTE_MD5 = null;
						Intent intent = new Intent(v.getContext(), SecNoteEditorActivity.class);
						startActivityForResult(intent, 0);
					}else{
						Intent intent = new Intent(v.getContext(), SecNoteAuthActivity.class);
						startActivityForResult(intent, 0);
					}
				}
			});
		}

		config_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(!isConfigExistent){
					enterCfg(v);
				}else if(isConfigExistent && key != null && Utils.getInstance().isAuthenticated(v.getContext(), key)){
					enterCfg(v);
				}else{
					Intent intent = new Intent(v.getContext(), SecNoteAuthActivity.class);
					startActivityForResult(intent, 0);
				}
			}

			public void enterCfg(View v){
				Intent intent = new Intent(v.getContext(), SecNoteConfigActivity.class);
				intent.putExtra("isConfigExistent", isConfigExistent);
				startActivityForResult(intent, 0);
			}
		});

		saved_notes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(key != null && Utils.getInstance().isAuthenticated(v.getContext(), key)){
					Intent intent = new Intent(v.getContext(), SecNoteListActivity.class);
					intent.putExtra("isConfigExistent", isConfigExistent);
					startActivityForResult(intent, 0);
				}else{
					Intent intent = new Intent(v.getContext(), SecNoteAuthActivity.class);
					startActivityForResult(intent, 0);
				}

			}
		});

	}
/*
	private void doCheck() {
        mChecker.checkAccess(mLicenseCheckerCallback);
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(AppMessages.getInstance().getMessage("GLOBAL.about"));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case 0:
			Intent intent = new Intent(getApplicationContext(), SecNoteAboutActivity.class);
			startActivityForResult(intent, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == Constants.TAKE_PHOTO_CODE && resultCode == RESULT_OK) {

			String yapeaDir = Utils.getInstance().getAppContentDir();

			File imageDir = new File(yapeaDir);

			if(imageDir.exists()){

				String[] contents = imageDir.list();
				for (int i = 0; i < contents.length; i++) {
					if(contents[i].substring(contents[i].length()-3, contents[i].length()).equalsIgnoreCase("jpg")){

						File f = new File(yapeaDir+contents[i]);
						try {
							byte[] plainContent = Utils.getInstance().getBytesFromFile(f);
							byte[] cipherContent = CryptoUtils.getInstance().enc(getApplicationContext(), key, plainContent, Utils.getInstance().getConfigFile(getApplicationContext()).getEncAlgo());
							Utils.getInstance().byteArrayToFile(cipherContent, yapeaDir+contents[i]+".yapea");
						} catch (SecNoteException e) {
							Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage(e.getMessage()), Toast.LENGTH_LONG).show();
						}finally{
							f.delete();
						}

					}
				}

			} //TODO add exception

		}else{
			String yapeaDir = Utils.getInstance().getAppContentDir();

			File imageDir = new File(yapeaDir);

			if(imageDir.exists()){

				String[] contents = imageDir.list();
				for (int i = 0; i < contents.length; i++) {
					if(contents[i].substring(contents[i].length()-3, contents[i].length()).equalsIgnoreCase("jpg")){

						File f = new File(yapeaDir+contents[i]);
						f.delete();


					}
				}

			} //TODO add exception
		}
	}

	@Override
	public void onBackPressed() {
		
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);

	}
	
	@Override
    protected void onDestroy() {
        super.onDestroy();
       // mChecker.onDestroy();
    }
	/*
	private void displayResult(final String result) {
        mHandler.post(new Runnable() {
            public void run() {
            	chachedMemTv.setText(result);
                setProgressBarIndeterminateVisibility(false);
                //mCheckLicenseButton.setEnabled(true);
            }
        });
    }

	/*
	private class MyLicenseCheckerCallback implements LicenseCheckerCallback {
	    public void allow(int reason) {
	        if (isFinishing()) {
	            return;
	        }
	       // displayResult("yes");
	    }

	    public void dontAllow(int reason) {
	        if (isFinishing()) {
	            return;
	        }
	        // displayResult("No");
	        
//	        if (reason == Policy.RETRY) {
//	        	System.out.println("DIALOG_RETRY");
//	        } else {
//	        	System.out.println("DIALOG_GOTOMARKET");
//	        }
	    }

	    
	    
	    
		@Override
		public void applicationError(int errorCode) {}
	}
	*/
}
