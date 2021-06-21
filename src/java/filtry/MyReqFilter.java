/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtry;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import resources.Authentication;

/**
 *
 * @author kuba2108
 */
@Provider
@Authentication
public class MyReqFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException
    {
        List<String>authHeader=requestContext.getHeaders().get("login");
        System.out.println("==========");
        String login=authHeader.get(0);
        authHeader=requestContext.getHeaders().get("haslo");
        System.out.println(login);
//        
        System.out.println("==========");
        String password=authHeader.get(0);
        System.out.println(password);
        if(!login.equals("lukasz") || !password.equals("kosmaty"))
            throw new IOException("nieprawidlowy login i/lub haslo");
//        String AuthToken2= authToken.replaceFirst("Basic ","");
//        System.out.println(AuthToken2);
//        byte[] decodedString=null;
//        byte[] bytes= AuthToken2.getBytes();
//        byte[] decodedBytes2=Base64.getDecoder().decode(bytes);
//        String decodedString2= new String (decodedBytes2);
//        System.out.println(decodedString2);
//        System.out.println("==========");
//        StringTokenizer tokenizer=new StringTokenizer(decodedString2,":");
//        String username=tokenizer.nextToken();
//        String password=tokenizer.nextToken();
//        System.out.println(username);
//        System.out.println(password);
    
    }
    
}
