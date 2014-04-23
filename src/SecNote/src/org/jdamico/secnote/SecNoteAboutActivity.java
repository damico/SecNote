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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecNoteAboutActivity extends Activity {
	
	Button back_button = null;
	TextView version = null;
	TextView author = null;
	TextView source = null;
	TextView lic = null;
			

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secnote_about);
		
		back_button = (Button) findViewById(R.id.back_button);
		back_button.setText(AppMessages.getInstance().getMessage("GLOBAL.back_button"));
		
		version = (TextView) findViewById(R.id.version);
		version.setText(AppMessages.getInstance().getMessage("GLOBAL.version"));
		
		author = (TextView) findViewById(R.id.author);
		author.setText(AppMessages.getInstance().getMessage("GLOBAL.author"));
		
		source = (TextView) findViewById(R.id.source);
		source.setText(AppMessages.getInstance().getMessage("GLOBAL.source"));
		
		lic = (TextView) findViewById(R.id.lic);
		lic.setText(AppMessages.getInstance().getMessage("GLOBAL.lic"));
		
		back_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), SecNoteMainActivity.class);
				startActivityForResult(intent, 0);
				
			}
		});
	}



}
