package br.com.gamestore.share;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LocalUpload implements UploadeFiles{

	@Override
	public String upload(MultipartFile file) {
		System.out.println("enviado arquivo...."+file);
		String retorno = null;
		try {
		retorno=file.getOriginalFilename();	
		} catch (Exception e) {
			System.out.println(e);
		}
		return "https://s3.amazon/bucket/"+retorno;
	}

}
