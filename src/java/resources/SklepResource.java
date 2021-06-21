/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;
import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author kuba2108
 */
@Singleton
@Path("/sklep")
public class SklepResource {
    @Context
   public UriInfo uriInfo;
    @Context
    HttpHeaders headers;
    public Sklep sklep;
    public Koszyk koszyk;
    public SklepResource() throws FileNotFoundException
    {
        //UriBuilder uri=uriInfo.getAbsolutePathBuilder();
        //String login,String haslo, float stan_konta, String miasto, String kod_pocztowy,String adres,String email,String numer_telefonu,String imie,String nazwisko
        this.koszyk=new Koszyk("Lukasz","Kosmaty", (float) 1000000,"Bialystok","15-888","ul. szkolna 17","oookosmaty@gmail.com","666662222","Lukasz","Kosmaty");
        this.sklep=new Sklep();
        
    }
    @GET
    @Authentication
    //@Path("getMessages")
//    @Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Stan> getMagazyn() {
        //TODO return proper representation object
        return sklep.getMagazyn(uriInfo);
    }
    @GET
    @Path("konto")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public Dane_Klienta getKonto() throws Exception {
        //TODO return proper representation object
        return koszyk.daneKlienta;
    }
    @GET
    @Path("/stan/{cardName}")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public Stan getStan(@PathParam("cardName")  String name) throws Exception {
        //TODO return proper representation object
        return sklep.zwroc_stan(name);
    }
    @PUT
    @Path("/stan/{cardName}")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public CoreLinki updateKoszyk(@PathParam("cardName")  String name,@QueryParam("ilosc")  int ilosc) throws Exception {
        //TODO return proper representation object
        Stan stan= sklep.zwroc_stan(name);
        koszyk.dodaj_do_koszyka(stan.karta, stan.cena, ilosc, uriInfo);
        return new CoreLinki( uriInfo);
        
        
    }
    
    @GET
    
    @Path("/user/koszyk")
//    @Produces(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Stan> getKoszyk() throws Exception {
        //TODO return proper representation object
        return koszyk.zwroc_koszyk();
    }
    @GET
    @Path("/user/koszyk/{cardName}")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public Stan zwrocpozycjeZKoszyka(@PathParam("cardName")  String name) throws Exception {
        //TODO return proper representation object
        return koszyk.zwroc_stan(name);
    }
    @DELETE
    @Path("/user/koszyk/{cardName}")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public CoreLinki deleteFromKoszyk(@PathParam("cardName")  String name) throws Exception {
        //TODO return proper representation object
        koszyk.pop_stan(name);
        return new CoreLinki( uriInfo);
    }
    @POST
    @Path("/user/koszyk")
//    @Produces(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_JSON)
    public Potw_zamowienia zlozZamowienie() throws Exception {
        //TODO return proper representation object
        
        return  zloz_zamowienie();
        
        
    }
    private Potw_zamowienia zloz_zamowienie( ) throws Exception
    {
        //Konto konto=zaloguj(login,haslo);
        try {
            sklep.weryfikuj_zamowienie(koszyk);
        } catch (Exception ex) {
           return new Potw_zamowienia(false,ex.getMessage());
           
        }
          
        try {
             Potw_zamowienia potw=finalizuj_zamowienie(koszyk);
             koszyk.oproznij_koszyk();
             System.out.println("klasy.Mtgsklep_serwerImpl.zloz_zamowienie()");
             return potw;
        } catch (Exception ex) {
            return new Potw_zamowienia(false,ex.getMessage());
        }
        
    }
    private Potw_zamowienia finalizuj_zamowienie(Koszyk koszyk) throws Exception{
        float kwota= sklep.aktualizuj_magazyn(koszyk.koszyk);
        koszyk.aktualizuj_stan_konta(kwota);
        Potw_zamowienia potw=new Potw_zamowienia(true,"zamowienie potwierdzone");
        potw.dane_sklepu=new Dane_sklepu();
        potw.dane_klienta=koszyk.daneKlienta;
        potw.kwota=kwota;
        potw.kwota_netto=(float) Math.round(kwota/1.23*100)/100;
        potw.koszyk= new ArrayList<Stan>(koszyk.koszyk);
        return potw;
        
    }
}
