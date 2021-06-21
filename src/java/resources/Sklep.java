/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kuba2108
 */
public class Sklep {
    
    public ArrayList<Stan> lista_st;
    public Sklep() throws FileNotFoundException
    {
        lista_st=new ArrayList<Stan>();
        File plik = new File("C:\\Users\\kuba2\\Desktop\\RSI\\RestProjectServer\\src\\java\\files\\magazyn.txt");
        Scanner odczyt = new Scanner( plik );
        while (odczyt.hasNext()){
            String linia1=odczyt.nextLine();
            //System.out.println(linia1);
            String linia2=odczyt.nextLine();
            //System.out.println(linia2);
            String linia3=odczyt.nextLine();
            //System.out.println(linia3);
            String linia4=odczyt.nextLine();
            //System.out.println(linia4);
            String linia5=odczyt.nextLine();
            //System.out.println(linia5);
            String linia6=odczyt.nextLine();
            //System.out.println(linia6);
            
            Stan stan=new Stan(new Karta(linia1,linia2,linia3),Integer.parseInt(linia4),Float.parseFloat(linia5));
            lista_st.add(stan);
            //System.out.println("www");
            
        }
    }
    public Stan zwroc_stan(String name) throws Exception
    {
        for (int i=0;i<lista_st.size();i++)
        {
            if(name.equals(lista_st.get(i).karta.nazwa))
                return lista_st.get(i);
        }
        throw new Exception("brak elemntu");
    }
    public Karta zwroc_karte(String name) throws Exception
    {
        for (int i=0;i<lista_st.size();i++)
        {
            if(name.equals(lista_st.get(i).karta.nazwa))
                return lista_st.get(i).karta;
        }
        throw new Exception("brak elemntu");
    }
    
    void weryfikuj_zamowienie(Koszyk koszyk) throws Exception {
        float kwota=0;
        for (int i=0;i<koszyk.koszyk.size();i++)
        {
            Stan pom =zwroc_stan(koszyk.koszyk.get(i).karta.nazwa);
            if(koszyk.koszyk.get(i).na_stanie>pom.na_stanie)
                throw new Exception("brak towaru na składzie");
            kwota+=koszyk.koszyk.get(i).na_stanie*koszyk.koszyk.get(i).cena; 
        }
        if(kwota>koszyk.daneKlienta.stan_konta)
            throw new Exception("niewystarczające środki");
        //To change body of generated methods, choose Tools | Templates.
    }

    float aktualizuj_magazyn(ArrayList<Stan> zamowienie) throws Exception {
        float kwota=0;
        for (int i=0;i<zamowienie.size();i++)
        {
            Stan pom =zwroc_stan(zamowienie.get(i).karta.nazwa);
            kwota+=zamowienie.get(i).na_stanie*zamowienie.get(i).cena; 
            pom.na_stanie=pom.na_stanie-zamowienie.get(i).na_stanie;
        }
        return kwota;
    }

    public ArrayList<Stan> getMagazyn(UriInfo uriInfo) {
        for (int i=0;i<lista_st.size();i++)
        {
            lista_st.get(i).addLinkMagazyn(uriInfo);
        }
        return this.lista_st;
    }
    
    
}
