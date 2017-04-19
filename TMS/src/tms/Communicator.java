package tms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import no.ntnu.alesund.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Norway92
 */
public class Communicator
  {
    private final FetchWebString fWString;
    private final JsonMarshalling jsonMarshall;
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
        jsonMarshall = new JsonMarshalling();
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
    
    public static void main(String[] args)
    {
        new Communicator().testGet();
    }
    
    public void testGet()
    {
        Customer customer;
        String getString = fWString.httpGet(url + customersUrl + "/1", null);
        System.out.println(getString + "\n");
        try {
            customer = mapper.readValue(getString, Customer.class);
            System.out.println(customer.getPhoneNumber());
            System.out.println(customer.getAddress());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    public void testPost()
    {
        Customer customer = new Customer();
        customer.setName("asdftest");
        customer.setAddress("testgata 11");
        customer.setPhoneNumber("24688462");
        customer.setEmail("e@ma.il");
        ZipCode zipCode = new ZipCode();
        zipCode.setZipCode("6412");
        customer.setZipCode(zipCode);
        try {
            System.out.println(mapper.writeValueAsString(customer));
            String postString = mapper.writeValueAsString(customer);
            httpPost(url + customersUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }

    private static String httpGet(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String input;
        StringBuffer response = new StringBuffer();

        while((input = in.readLine()) != null){
            response.append(input);
        }
        in.close();
        System.out.println(response.toString());
        return response.toString();
    }


    private static void httpPost(String urlString, String body) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(body);
        out.flush();
        out.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String input;
        StringBuffer response = new StringBuffer();

        while((input = in.readLine()) != null){
            response.append(input);
        }
        in.close();

        System.out.println(response.toString());
    }
  }
