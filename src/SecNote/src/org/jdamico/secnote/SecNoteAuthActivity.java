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

import org.jdamico.secnote.commons.AppMessages;
import org.jdamico.secnote.commons.SecNoteException;
import org.jdamico.secnote.commons.Utils;
import org.jdamico.secnote.crypto.CryptoUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecNoteAuthActivity extends Activity {

	Button authButton = null;
	EditText keyEt = null;
	TextView key_textView = null;
	int countPanic = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secnote_auth);

		authButton = (Button) findViewById(R.id.auth_button);
		authButton.setText(AppMessages.getInstance().getMessage("GLOBAL.authButton"));
		
		key_textView = (TextView) findViewById(R.id.key_textView);
		key_textView.setText(AppMessages.getInstance().getMessage("GLOBAL.key_textView"));

		keyEt = (EditText) findViewById(R.id.key_text);

		authButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(Utils.getInstance().isAuthenticated(v.getContext(), keyEt.getText().toString())){
					
					
					try {
						CryptoUtils.getInstance().storeKeyInCache(keyEt.getText().toString(), v.getContext());
					} catch (SecNoteException e) {
						Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage("AuthActivity.onCreate.failToStoreKeyInCache"), Toast.LENGTH_LONG).show();
					}
					
					
					Intent intent = new Intent(v.getContext(), SecNoteMainActivity.class);
					startActivityForResult(intent, 0);
				}else{
					countPanic = countPanic + Utils.getInstance().isPanicAuthenticated(v.getContext(), keyEt.getText().toString(), countPanic);
					//System.out.println("============== countPanic"+countPanic);
					Toast.makeText(getApplicationContext(), AppMessages.getInstance().getMessage("AuthActivity.onCreate.WrongKey"), Toast.LENGTH_LONG).show();
				}
				keyEt.setText("");
			}
		});
	}

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
	public void onBackPressed() {
	    moveTaskToBack(true);
	}

}
