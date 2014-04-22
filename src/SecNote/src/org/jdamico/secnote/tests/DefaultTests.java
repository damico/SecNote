package org.jdamico.secnote.tests;

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

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdamico.secnote.commons.Utils;
import org.jdamico.secnote.commons.XMLParser;
import org.jdamico.secnote.dataobjects.NoteItemObj;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class DefaultTests {

	//@Test
	public void testGetSalt() {
		String a = "test.jpg";
		String b = a.substring(a.length()-4, a.length()-1);
		System.out.println(b);
	}

	@Test
	public void TestXML(){
		
		
		String noteContent = "conteúdo";
		String noteTitle = "título";
		String noteMd5 = "md5";
		
		NoteItemObj note = new NoteItemObj(noteTitle , noteMd5 , noteContent, null );
		
		String XMLData = Utils.getInstance().convertNoteObj2XmlStr(note);
		
		try {
			
			/************** Read XML *************/
            
            BufferedReader br=new BufferedReader(new StringReader(XMLData));
            InputSource is=new InputSource(br);
             
            /************  Parse XML **************/

            XMLParser parser=new XMLParser();
            SAXParserFactory factory=SAXParserFactory.newInstance();
            SAXParser sp=factory.newSAXParser();
            XMLReader reader=sp.getXMLReader();
            reader.setContentHandler(parser);
            reader.parse(is);
			
            List<NoteItemObj> noteLst = parser.getNoteLst();
            
            note = noteLst.get(0);
            
            System.out.println(note.getNoteTitle() + " - " + note.getNoteMd5() + " - " + note.getNoteContent());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
