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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdamico.secnote.commons.SecNoteException;
import org.jdamico.secnote.commons.StaticObj;
import org.jdamico.secnote.commons.Utils;
import org.jdamico.secnote.dataobjects.NoteItemObj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment representing a single Image detail screen. This fragment is either
 * contained in a {@link SecNoteListActivity} in two-pane mode (on tablets) or a
 * {@link SecNoteDetailActivity} on handsets.
 */
public class SecNoteDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	/**
	 * The dummy content this fragment is presenting.
	 */
	private NoteItemObj mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public SecNoteDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Map<String, NoteItemObj> ITEM_MAP = new HashMap<String, NoteItemObj>();
		
		String dir = Utils.getInstance().getAppContentDir();
		
		File folder = new File(dir);
		
		if(folder.exists()){
			
			String[] contents = folder.list();
			for (int i = 0; i < contents.length; i++) {
				
				try {
					String noteXml = Utils.getInstance().getStringFromFile(dir+contents[i]);
					List<NoteItemObj> noteObj = Utils.getInstance().convertXmlStrNoteLst(noteXml);
					
					ITEM_MAP.put(noteObj.get(0).getNoteMd5(), new NoteItemObj(noteObj.get(0).getNoteTitle(), noteObj.get(0).getNoteMd5(), noteObj.get(0).getNoteContent(), noteObj.get(0).getNoteTimeStampStr()));
				} catch (SecNoteException e) {
					e.printStackTrace();
				}
			}
			
		} 

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_secnote_editor, container, false);

		
		// Show the dummy content as text in a TextView.
		if (mItem != null) {
			
			
			StaticObj.NOTE_MD5 = mItem.getNoteMd5();
			Intent intent = new Intent(rootView.getContext(), SecNoteEditorActivity.class);
			startActivityForResult(intent, 0);

			
		}

		return rootView;
	}
}
