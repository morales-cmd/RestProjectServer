/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;


import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import com.sun.xml.wss.impl.misc.Base64;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;




/**
 *
 * @author kuba2108
 */

public class Karta {
public String nazwa;
public String opis;
public String ilustracja;
public Karta(String nazwa,String opis,String nazwa_il)
{
    this.nazwa= nazwa;
    this.opis=opis;
    if (!nazwa_il.equals("null"))
    {
    try {
            
            File image = new File("C:\\Users\\kuba2\\Desktop\\RSI\\ProjektSoapSerwer\\src\\java\\klasy\\ilustracje\\"+ nazwa_il);
            ilustracja = encodeFileToBase64Binary(image);
            
           
       } catch (Exception e) {            
          e.printStackTrace(); 
          
       }
    }
    else
    {
        this.ilustracja=null;
    }
}
public Karta kopiuj ()
{
    Karta karta =new Karta(nazwa,opis,"null");
    karta.ilustracja=null;
    return karta;
}
private  String encodeFileToBase64Binary(File file) throws Exception{
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            return new String(Base64.encode(bytes));
        }
    
}
