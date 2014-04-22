package org.jdamico.secnote.commons;

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
import java.io.IOException;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;


public class ActivityHelper {

	private static ActivityHelper INSTANCE = null;

	private ActivityHelper(){}

	public static ActivityHelper getInstance(){
		if(null == INSTANCE) INSTANCE = new ActivityHelper();
		return INSTANCE;
	}

	public Intent takePicture(View v){

		String yapeaDir = Utils.getInstance().getAppContentDir();
		String file = yapeaDir+Utils.getInstance().getCurrentDateTimeFormated(Constants.TIMESTAMP_FORMAT)+".jpg";



		File newfile = new File(file);
		try {
			newfile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}       

		Uri outputFileUri = Uri.fromFile(newfile);

		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

		return cameraIntent;

	}



}
