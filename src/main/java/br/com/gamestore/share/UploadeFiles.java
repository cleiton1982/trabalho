package br.com.gamestore.share;

import org.springframework.web.multipart.MultipartFile;
@FunctionalInterface
public interface UploadeFiles {
public String upload(MultipartFile file);
}
