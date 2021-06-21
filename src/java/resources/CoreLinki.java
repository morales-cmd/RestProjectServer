/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kuba2108
 */
@XmlRootElement
public class CoreLinki {
    public Link sklep_link;
    public Link koszyk_link;
    CoreLinki(){};
    CoreLinki(UriInfo uriInfo) {
        this.sklep_link=new Link(uriInfo.getBaseUriBuilder().path("sklep").build(),"sklep");
        this.koszyk_link=new Link(uriInfo.getBaseUriBuilder().path("sklep").path("user").path("koszyk").build(),"koszyk");
        
    }
    
}
