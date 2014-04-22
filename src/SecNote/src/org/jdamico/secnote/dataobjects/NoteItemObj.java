package org.jdamico.secnote.dataobjects;

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

public class NoteItemObj {
	
	private String noteTitle = null;
	private String noteMd5 = null;
	private String noteContent = null;
	private String noteTimeStampStr = null;
	
	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteMd5() {
		return noteMd5;
	}

	public void setNoteMd5(String noteMd5) {
		this.noteMd5 = noteMd5;
	}

	public NoteItemObj(String noteTitle, String noteMd5, String noteContent, String noteTimeStampStr) {
		super();
		this.noteTitle = noteTitle;
		this.noteMd5 = noteMd5;
		this.noteContent = noteContent;
		this.noteTimeStampStr = noteTimeStampStr;
	}
	
	public NoteItemObj() {
		super();
	}

	public String getNoteTimeStampStr() {
		return noteTimeStampStr;
	}

	public void setNoteTimeStampStr(String noteTimeStampStr) {
		this.noteTimeStampStr = noteTimeStampStr;
	}

	@Override
    public String toString() {
            return noteTitle + " (" + noteTimeStampStr + ")";
    }
	

}
