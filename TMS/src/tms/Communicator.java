package tms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * This class connects to the ERP SQL database
 * and can get and post information from/to it.
 *
 * 
 * @author Norway92
 */
public class Communicator
  {
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
    
    /**
     * 
     */
    public Communicator ()
    {
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
    
    /**
     * 
     * @return 
     */
    public List<Customer> customersGet()
    {
        List<Customer> cList = null;
        try {
            String getString = httpGet(url + customersUrl);
            cList = mapper.readValue(getString, new TypeReference<List<Customer>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cList;
    }
    
    /**
     * 
     * @param customer 
     */
    public void customerPost(Customer customer)
    {
        try {
            //System.out.println(mapper.writeValueAsString(customer));
            String postString = mapper.writeValueAsString(customer);
            httpPost(url + customersUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Employee> employeesGet()
    {
        List<Employee> eList = null;
        try {
            String getString = httpGet(url + employeesUrl);
            eList = mapper.readValue(getString, new TypeReference<List<Employee>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return eList;
    }
    
    /**
     * 
     * @param employee 
     */
    public void employeePost(Employee employee)
    {
        try {
            //System.out.println(mapper.writeValueAsString(employee));
            String postString = mapper.writeValueAsString(employee);
            httpPost(url + employeesUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Product> productsGet()
    {
        List<Product> pList = null;
        try {
            String getString = httpGet(url + productsUrl);
            pList = mapper.readValue(getString, new TypeReference<List<Product>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pList;
    }
    
    /**
     * 
     * @param product 
     */
    public void productPost(Product product)
    {
        try {
            //System.out.println(mapper.writeValueAsString(product));
            String postString = mapper.writeValueAsString(product);
            httpPost(url + productsUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Order> ordersGet()
    {
        List<Order> oList = null;
        try {
            String getString = httpGet(url + ordersUrl);
            oList = mapper.readValue(getString, new TypeReference<List<Order>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return oList;
    }
    
    /**
     * 
     * @param order 
     */
    public void orderPost(Order order)
    {
        try {
            //System.out.println(mapper.writeValueAsString(order));
            String postString = mapper.writeValueAsString(order);
            httpPost(url + ordersUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<ZipCode> zipCodesGet()
    {
        List<ZipCode> zCList = null;
        try {
            String getString = httpGet(url + zipcodesUrl);
            zCList = mapper.readValue(getString, new TypeReference<List<ZipCode>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return zCList;
    }
    
    /**
     * 
     * @param zipCode 
     */
    public void zipCodePost(ZipCode zipCode)
    {
        try {
            //System.out.println(mapper.writeValueAsString(zipCode));
            String postString = mapper.writeValueAsString(zipCode);
            httpPost(url + zipcodesUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Department> departmentsGet()
    {
        List<Department> dList = null;
        try {
            String getString = httpGet(url + departmentsUrl);
            dList = mapper.readValue(getString, new TypeReference<List<Department>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dList;
    }
    
    /**
     * 
     * @param department 
     */
    public void departmentPost(Department department)
    {
        try {
            //System.out.println(mapper.writeValueAsString(department));
            String postString = mapper.writeValueAsString(department);
            httpPost(url + departmentsUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Orderline> orderlinesGet()
    {
        List<Orderline> oList = null;
        try {
            String getString = httpGet(url + orderlinesUrl);
            oList = mapper.readValue(getString, new TypeReference<List<Orderline>>(){});
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return oList;
    }
    
    /**
     * 
     * @param orderline 
     */
    public void orderlinePost(Orderline orderline)
    {
        try {
            //System.out.println(mapper.writeValueAsString(orderline));
            String postString = mapper.writeValueAsString(orderline);
            httpPost(url + orderlinesUrl, postString);
        } catch (JsonProcessingException jpe) {
            System.out.println(jpe.getMessage());
        } catch (Exception ioe) {
            ioe.getMessage();
        }
    }

    /**
     * 
     * @param urlString
     * @return
     * @throws Exception 
     */
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

    /**
     * 
     * @param urlString
     * @param body
     * @throws Exception 
     */
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
