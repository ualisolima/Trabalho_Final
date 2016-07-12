package br.ufc.trabalho_final.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public static void saveFile(String path, MultipartFile file) {
		File saveFile = new File(path);
		try {
			FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
			System.out.println("Salvo em: " + path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
