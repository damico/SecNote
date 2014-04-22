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

public class ConfigObj {

	private String encAlgo = null;
	private String hashedKey = null;
	private boolean isGpsEnabled = false;
	private String panicPassword = null;
	private int panicNumber = 0;
	
	public ConfigObj(String encAlgo, String hashedKey, boolean isGpsEnabled, String panicPassword, int panicNumber) {
		super();
		this.encAlgo = encAlgo;
		this.hashedKey = hashedKey;
		this.isGpsEnabled = isGpsEnabled;
		this.panicPassword = panicPassword;
		this.panicNumber = panicNumber;
	}
	public ConfigObj() {}
	public String getEncAlgo() {
		return encAlgo;
	}
	public void setEncAlgo(String encAlgo) {
		this.encAlgo = encAlgo;
	}
	public String getHashedKey() {
		return hashedKey;
	}
	public void setHashedKey(String hashedKey) {
		this.hashedKey = hashedKey;
	}
	public boolean isGpsEnabled() {
		return isGpsEnabled;
	}
	public void setGpsEnabled(boolean isGpsEnabled) {
		this.isGpsEnabled = isGpsEnabled;
	}
	public String getPanicPassword() {
		return panicPassword;
	}
	public void setPanicPassword(String panicPassword) {
		this.panicPassword = panicPassword;
	}
	public int getPanicNumber() {
		return panicNumber;
	}
	public void setPanicNumber(int panicNumber) {
		this.panicNumber = panicNumber;
	}
	
	
	
	
}
