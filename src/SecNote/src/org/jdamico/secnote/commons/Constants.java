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

public interface Constants {
	
	public static final String CONFIG_FILE = ".secnote.config";
	public static final String XML_CONFIG_ROOT_TAG = "secnote";
	public static final String XML_CONFIG_KEY_TAG = "key";
	public static final String XML_CONFIG_KEY_HASH_ATTRIB = "hash";
	public static final String XML_CONFIG_ALGO_TAG = "algo";
	public static final String XML_CONFIG_ALGO_TYPE_ATTRIB = "type";
	public static final int PBKDF2_KEY_LENGTH = 64;
	public static final int TAKE_PHOTO_CODE = 0;
	public static final String TIMESTAMP_FORMAT = "yyyyMMMdd_HH_mm_ss";
	public static final String XML_CONFIG_KEY_PANICPASSWD_ATTRIB = "panicPasswd";
	public static final String XML_CONFIG_KEY_PANICNUMBER_ATTRIB = "panicNumber";
	public static final String VERSION = "0.2";
	public static final String APP_NAME = XML_CONFIG_ROOT_TAG;

}
