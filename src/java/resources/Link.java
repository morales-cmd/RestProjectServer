/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kuba2108
 */
//@XmlRootElement
public class Link {
    public URI uri;
    public String rel;
    public Link(URI uri,String rel)
    {
        this.uri=uri;
        this.rel=rel;
    }
}
