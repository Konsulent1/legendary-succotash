package tms;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import no.ntnu.alesund.*;

/**
 *
 * @author Norway92
 */
public class Communicator
  {
    private final FetchWebString fWString;
    private final JsonMarshalling jsonMarshal;
    private static ObjectMapper mapper;
    
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
        mapper = new ObjectMapper();
        
        url = "http://kaysl-logix.uials.no:8080";
        customersUrl = "/customers";
        employeesUrl = "/employees";
        productsUrl = "/products";
        ordersUrl = "/orders";
        zipcodesUrl = "/zipcodes";
        departmentsUrl = "/departments";
        orderlinesUrl = "/orderlines";
    }
    
    public void testGet()
    {
        Customer customer;
        String getString = fWString.httpGet(url + customersUrl, null);
        System.out.println(getString + "\n");
        try {
            //jsonMarshal.setAlias("customer");
            //customer = (Customer)jsonMarshal.unmarshall(getString, Customer.class);
            customer = mapper.readValue(getString, Customer.class);
            System.out.println(customer.getPhoneNumber());
            System.out.println(customer.getAddress());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    public static void main(String[] args)
    {
        new Communicator().testGet();
    }
  }
