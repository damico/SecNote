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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Images. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link SecNoteDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link SecNoteListFragment} and the item details (if present) is a
 * {@link SecNoteDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link SecNoteListFragment.Callbacks} interface to listen for item selections.
 */
public class SecNoteListActivity extends FragmentActivity implements SecNoteListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secnote_list);

		if (findViewById(R.id.note_detail_container) != null) {
			mTwoPane = true;
			((SecNoteListFragment) getSupportFragmentManager().findFragmentById(R.id.note_list)).setActivateOnItemClick(true);
		}
	}

	/**
	 * Callback method from {@link SecNoteListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(SecNoteDetailFragment.ARG_ITEM_ID, id);
			SecNoteDetailFragment fragment = new SecNoteDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction().replace(R.id.note_detail_container, fragment).commit();

		} else {
			Intent detailIntent = new Intent(this, SecNoteDetailActivity.class);
			detailIntent.putExtra(SecNoteDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, SecNoteMainActivity.class);
		startActivityForResult(intent, 0);
	}
}
