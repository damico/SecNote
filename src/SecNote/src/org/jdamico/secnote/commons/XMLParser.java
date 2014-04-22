package org.jdamico.secnote.commons;

import java.util.ArrayList;
import java.util.List;

import org.jdamico.secnote.dataobjects.NoteItemObj;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser  extends DefaultHandler {
	
	List<NoteItemObj> list=null;
    
    // string builder acts as a buffer
    StringBuilder builder;
 
    NoteItemObj note =null;
     
     
     // Initialize the arraylist
     // @throws SAXException
      
    @Override
    public void startDocument() throws SAXException {
         
        /******* Create ArrayList To Store XmlValuesModel object ******/
    	//System.out.println(">>>>> startDocument()");
        list = new ArrayList<NoteItemObj>();
    }
 
     
     // Initialize the temp XmlValuesModel object which will hold the parsed info
     // and the string builder that will store the read characters
     // @param uri
     // @param localName ( Parsed Node name will come in localName  )
     // @param qName
     // @param attributes
     // @throws SAXException
      
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
         
    	//System.out.println(">>>>> startElement("+localName+", "+qName+")");
    	
        /****  When New XML Node initiating to parse this function called *****/
         
        // Create StringBuilder object to store xml node value
        builder=new StringBuilder();
         
        if(qName.equals("secnote")){
             
        }
        else if(qName.equals("note")){
        	
        	//System.out.println(">>>>> startElement(note)");
        	
            note = new NoteItemObj();
            note.setNoteTitle(attributes.getValue("title"));
            note.setNoteMd5(attributes.getValue("md5"));
            note.setNoteTimeStampStr(attributes.getValue("timestamp"));
        }
    }
     
     
     
     // Finished reading the login tag, add it to arraylist
     // @param uri
     // @param localName
     // @param qName
     // @throws SAXException
      
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        
         
        if(qName.equals("note")){

            note.setNoteContent(builder.toString());
            list.add( note );
             
        }
    }
 
    
     // Read the value of each xml NODE
     // @param ch
     // @param start
     // @param length
     // @throws SAXException
      
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
             
        /******  Read the characters and append them to the buffer  ******/
        String tempString=new String(ch, start, length);
         builder.append(tempString);
    }

    
    public List<NoteItemObj> getNoteLst(){
    	return list;
    }
}
