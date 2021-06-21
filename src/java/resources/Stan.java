/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kuba2108
 */
@XmlRootElement
public class Stan {
    public Karta karta;
    public int na_stanie;
    public float cena;
    public float wartosc_razem;
    public Link self;
    public Link sklep_link;
    public Link koszyk_link;
    public Stan(){};
    public Stan(Karta karta,int na_stanie, float cena)
    {
        this.karta=karta;
        this.na_stanie=na_stanie;
        this.cena=cena;
        this.wartosc_razem=cena*na_stanie;
        //
    }

    void addLinkMagazyn(UriInfo uriInfo) {
        this.sklep_link=new Link(uriInfo.getAbsolutePathBuilder().build(),"sklep");
        this.koszyk_link=new Link(uriInfo.getAbsolutePathBuilder().path("user").path("koszyk").build(),"koszyk");
        this.self=new Link(uriInfo.getAbsolutePathBuilder().path("stan").path(this.karta.nazwa).build(),"stan"); //To change body of generated methods, choose Tools | Templates.
    }
    void addLinkKoszyk(UriInfo uriInfo) {
        this.sklep_link=new Link(uriInfo.getBaseUriBuilder().path("sklep").build(),"sklep");
        this.koszyk_link=new Link(uriInfo.getBaseUriBuilder().path("sklep").path("user").path("koszyk").build(),"koszyk");
        this.self=new Link(uriInfo.getBaseUriBuilder().path("sklep").path("user").path("koszyk").path(this.karta.nazwa).build(),"pozycja w koszyku"); //To change body of generated methods, choose Tools | Templates.
    }
}
