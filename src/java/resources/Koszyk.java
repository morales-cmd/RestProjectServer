/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ArrayList;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kuba2108
 */
public class Koszyk {
    
    public Dane_Klienta daneKlienta;
    public ArrayList<Stan> koszyk;
    public Koszyk (String login,String haslo, float stan_konta, String miasto, String kod_pocztowy,String adres,String email,String numer_telefonu,String imie,String nazwisko){
        this.daneKlienta=new Dane_Klienta();
        this.daneKlienta.login=login;
        this.daneKlienta.haslo=haslo;
        this.daneKlienta.stan_konta=stan_konta;
        this.daneKlienta.miasto=miasto;
        this.daneKlienta.kod_pocztowy=kod_pocztowy;
        this.daneKlienta.adres=adres;
        this.daneKlienta.email=email;
        this.daneKlienta.numer_telefonu=numer_telefonu;
        this.koszyk= new ArrayList<Stan>();
        this.daneKlienta.imie=imie;
        this.daneKlienta.nazwisko=nazwisko;
}
    public Stan zwroc_stan(String name) throws Exception
    {
        for (int i=0;i<koszyk.size();i++)
        {
            if(name.equals(koszyk.get(i).karta.nazwa))
                return koszyk.get(i);
        }
        throw new Exception("brak elementu");
    }
    public void pop_stan(String name) 
        {
            for (int i=0;i<koszyk.size();i++)
            {
                if(name.equals(koszyk.get(i).karta.nazwa))
                    koszyk.remove(i);
            }
        }

    void dodaj_do_koszyka(Karta karta,float cena, int liczba,UriInfo uriInfo) {
        try {Stan pom=this.zwroc_stan(karta.nazwa);
            pom.na_stanie=liczba;
            pom.wartosc_razem=liczba* pom.cena;
                }
        catch(Exception ex){
            Stan stan=new Stan(karta.kopiuj(),liczba,cena);
            stan.addLinkKoszyk(uriInfo);
            koszyk.add(stan);
        }//To change body of generated methods, choose Tools | Templates.
    }

    void usun_z_koszyka(Karta karta) {
         this.pop_stan(karta.nazwa);
            
                
        } //To change body of generated methods, choose Tools | Templates.

    void oproznij_koszyk() {
        while(koszyk.size()>0)
            koszyk.remove(0);//To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Stan> zwroc_koszyk() {
        return this.koszyk;
    }

    void aktualizuj_stan_konta(float kwota) {
        this.daneKlienta.stan_konta=this.daneKlienta.stan_konta-kwota;
    }
    
}
