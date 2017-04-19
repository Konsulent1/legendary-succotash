package tms;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.ntnu.alesund.*;

/**
 *
 * @author Norway92
 */
public class Communicator
  {
    //private Encoder encoder;
    private final FetchWebString fWString;
    private final JsonMarshalling jsonMarshal;
    
    // URL-fragments
    private final String url;
    private final String customersUrl;
    private final String employeesUrl;
    private final String productsUrl;
    private final String ordersUrl;
    private final String zipcodesUrl;
    private final String departmentsUrl;
    private final String orderlinesUrl;
    
    public Communicator ()
    {
        fWString = new FetchWebString();
        jsonMarshal = new JsonMarshalling();
        
        url = "http://kaysl-logix.uials.no:8080";
        customersUrl = "/customers";
        employeesUrl = "/employees";
        productsUrl = "/products";
        ordersUrl = "/orders";
        zipcodesUrl = "/zipcodes";
        departmentsUrl = "/departments";
        orderlinesUrl = "/orderlines";
        
        /*try {
            encoder = new Encoder();
        } catch (NoSuchAlgorithmException nSAE) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, nSAE);
        }*/
    }
    
    public void testGet()
    {
        System.out.println(fWString.httpGet(url + customersUrl, null));
    }
    
    public static void main(String[] args)
    {
        new Communicator().testGet();
    }
  }
