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

public class CryptoAlgoObj {
	
	private String algoName = null;
	private String algoInstance = null;
	private int ivLength = 0;
	public String getAlgoName() {
		return algoName;
	}
	public void setAlgoName(String algoName) {
		this.algoName = algoName;
	}
	public String getAlgoInstance() {
		return algoInstance;
	}
	public void setAlgoInstance(String algoInstance) {
		this.algoInstance = algoInstance;
	}
	public int getIvLength() {
		return ivLength;
	}
	public void setIvLength(int ivLength) {
		this.ivLength = ivLength;
	}
	public CryptoAlgoObj(String algoName, String algoInstance, int ivLength) {
		super();
		this.algoName = algoName;
		this.algoInstance = algoInstance;
		this.ivLength = ivLength;
	}
	
	
	
}
