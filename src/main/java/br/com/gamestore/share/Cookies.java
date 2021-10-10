package br.com.gamestore.share;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gamestore.dominio.Carrinho;

@Component
public class Cookies {
	/**
	 * 
	 * @param key chave do cookie
	 * @param carrinho carrinho de compras
	 * @param response
	 * @return 
	 */
public Cookie writeAsJson(String key,Carrinho carrinho,HttpServletResponse response) {

		Cookie cookie;
		try {
			cookie = new Cookie(key, new ObjectMapper().writeValueAsString(carrinho));
			cookie.setHttpOnly(true);
			cookie.setPath("/");
			//response.addCookie(cookie);
			return cookie;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
			


}
}
