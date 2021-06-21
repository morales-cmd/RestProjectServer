/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kuba2108
 */
@XmlRootElement
public class Dane_Klienta {
    public String login;
    public String haslo;
    public String imie;
    public String nazwisko;
    public float stan_konta;
    public String miasto;
    public String kod_pocztowy;
    public String adres;
    public String email;
    public String numer_telefonu;
    public Dane_Klienta(){};
    
}
