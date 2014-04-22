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

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppMessages {

	private static AppMessages INSTANCE = null;

	private AppMessages(){}

	public static AppMessages getInstance(){
		if(null == INSTANCE) INSTANCE = new AppMessages();
		return INSTANCE;
	}

	public String getMessage(String messageId){

		String locale = Locale.getDefault().toString();

		String msg = null;
		try{

			if(getPtMap().containsKey(messageId)){

				if(locale.contains("pt")) msg = getPtMap().get(messageId);
				else msg = getEnMap().get(messageId);

			}else msg = messageId + " ["+locale+"]";

		}catch(Exception e){
			msg = messageId + " ["+locale+"]";
		}



		return msg;

	}

	public Map<String, String> getPtMap(){

		Map<String, String> ptMap = new HashMap<String, String>();
		ptMap.put("Utils.createConfig.failedToTransformKeyHash", "Falha ao transformar o hash da chave.");
		ptMap.put("Utils.changeConfig.diffPasswd", "As senhas diferem!");
		ptMap.put("Utils.changeConfig.wrongPasswd", "Senha incorreta!");
		ptMap.put("Utils.changeConfig.nullContext", "O contexto da aplicação é nulo.");
		ptMap.put("Utils.writeTextToFile.failToWriteFile", "Erro ao escrever arquivo.");
		ptMap.put("Utils.getBytesFromFile.fileTooLArge", "O arquivo é muito grande.");
		ptMap.put("GLOBAL.note_save_button", "Salvar");
		ptMap.put("GLOBAL.note_del_button", "Del");
		ptMap.put("GLOBAL.confirm_del", "Tem certeza que deseja apagar essa nota?");
		ptMap.put("GLOBAL.back_button", "Voltar");
		ptMap.put("GLOBAL.version", "Versão: "+Constants.VERSION);
		ptMap.put("GLOBAL.author", "Autor: Jose Damico <damico@tix11.com>");
		ptMap.put("GLOBAL.source", "Código-fonte: http://github.com/damico");
		ptMap.put("GLOBAL.lic", "Licença: GPL v2");
		ptMap.put("GLOBAL.authButton", "Desbloquear");
		ptMap.put("GLOBAL.key_textView", "Chave:");
		ptMap.put("AuthActivity.onCreate.WrongKey", "Senha incorreta!");
		ptMap.put("GLOBAL.about", "Sobre");
		ptMap.put("GLOBAL.algoTv", "Algoritmo:");
		ptMap.put("GLOBAL.oldPtV", "Senha antiga:");
		ptMap.put("GLOBAL.newPtV", "Nova senha:");
		ptMap.put("GLOBAL.newP2tV", "Repita a nova senha:");
		ptMap.put("GLOBAL.reset_button", "Reset");
		ptMap.put("GLOBAL.save_config_button", "Salvar");
		ptMap.put("ConfigActivity.onCreate.dumpAppData", "Apagar todos os dados da aplicação?");
		ptMap.put("GLOBAL.yes", "Sim");
		ptMap.put("GLOBAL.no", "Não");
		ptMap.put("GLOBAL.cam_button", "Criar Nota");
		ptMap.put("GLOBAL.gallery_button", "Notas Salvas");
		ptMap.put("GLOBAL.config_button", "Config");
		ptMap.put("YapeaMainActivity.onCreate.keyInCache", "Chave armazenada em memória.");
		ptMap.put("CryptoUtils.normalizeIvByteArray.nullSource", "A fonte é nula.");
		ptMap.put("AuthActivity.onCreate.failToStoreKeyInCache", "Erro ao armazenar a chave em cache.");
		ptMap.put("GLOBAL.clear_cache_button", "Limpar cache");
		ptMap.put("GLOBAL.cache_cleaned", "Cache limpo.");
		ptMap.put("GLOBAL.panicTv", "Senha de pânico:");
		ptMap.put("GLOBAL.panicNumberTv", "Número de tentativas para a ativação da senha de pânico:");
		ptMap.put("Utils.changeConfig.panicPasswdMustBeDiff", "A senha de pânico deve ser diferente das demais.");
		ptMap.put("GLOBAL.note_saved_success", "Nota gravada!");

		return ptMap;
	}

	public Map<String, String> getEnMap(){

		Map<String, String> enMap = new HashMap<String, String>();
		enMap.put("Utils.createConfig.failedToTransformKeyHash", "Failed to transform key hash.");
		enMap.put("Utils.changeConfig.diffPasswd", "The password are different!");
		enMap.put("Utils.changeConfig.wrongPasswd", "Wrong password!");
		enMap.put("Utils.changeConfig.nullContext", "The application context is null.");
		enMap.put("Utils.writeTextToFile.failToWriteFile", "Fail to write file.");
		enMap.put("Utils.getBytesFromFile.fileTooLArge", "File is too large.");
		enMap.put("GLOBAL.note_save_button", "Save");
		enMap.put("GLOBAL.note_del_button", "Del");
		
		enMap.put("GLOBAL.confirm_del", "Are you sure you want delete this note?");
		
		enMap.put("GLOBAL.back_button", "Back");
		enMap.put("GLOBAL.version", "Version: "+Constants.VERSION);
		enMap.put("GLOBAL.author", "Author: Jose Damico <damico@tix11.com>");
		enMap.put("GLOBAL.source", "Source code: http://github.com/damico");
		enMap.put("GLOBAL.lic", "Licence: GPL v2");
		enMap.put("GLOBAL.authButton", "Unlock");
		enMap.put("GLOBAL.key_textView", "Key:");
		enMap.put("AuthActivity.onCreate.WrongKey", "Wrong password!");
		enMap.put("GLOBAL.about", "About");
		enMap.put("GLOBAL.algoTv", "Algorithm:");
		enMap.put("GLOBAL.oldPtV", "Old password:");
		enMap.put("GLOBAL.newPtV", "New password:");
		enMap.put("GLOBAL.newP2tV", "Type new password again:");
		enMap.put("GLOBAL.reset_button", "Reset");
		enMap.put("GLOBAL.save_config_button", "Save");
		enMap.put("ConfigActivity.onCreate.dumpAppData", "Clear all application data?");
		enMap.put("GLOBAL.yes", "Yes");
		enMap.put("GLOBAL.no", "No");
		enMap.put("GLOBAL.cam_button", "New Note");
		enMap.put("GLOBAL.gallery_button", "Stored Notes");
		enMap.put("GLOBAL.config_button", "Config");
		enMap.put("YapeaMainActivity.onCreate.keyInCache", "Key stored in memory.");
		enMap.put("CryptoUtils.normalizeIvByteArray.nullSource", "The source is null.");
		enMap.put("AuthActivity.onCreate.failToStoreKeyInCache", "Failed trying to store key in cache.");
		enMap.put("GLOBAL.clear_cache_button", "Clear cache");
		enMap.put("GLOBAL.cache_cleaned", "Cache cleaned.");
		enMap.put("GLOBAL.panicTv", "Panic password:");
		enMap.put("GLOBAL.panicNumberTv", "Number of tries before panic password activation:");
		enMap.put("Utils.changeConfig.panicPasswdMustBeDiff", "The panic password must be different from others.");
		enMap.put("GLOBAL.note_saved_success", "Note saved!");


		return enMap;
	}
}
