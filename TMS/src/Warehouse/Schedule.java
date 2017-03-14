/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warehouse;

import java.sql.Timestamp;

/**
 *   Approved by Øystein, funka sikkert!
 *      TODO
 * 
 * @author Vinh
 */

public class Schedule {
    
    private Timestamp departureDateTimeStamp;
    
    private String departureDateTime;
    
    
    private String deliveryLocation;
    private String driverFirstName;
    private String driverLastName;
    
    
    /**
     * 
     * @param departureTime
     * @param departureDate
     * @param place
     * @param driverName 
     */
    public Schedule(Timestamp departureTime, String departureDate , String place, String driverName)
    {
        this.departureDateTimeStamp = departureTime;
        this.departureDateTime = new String(departureDateTimeStamp.toString());
        
        this.deliveryLocation = deliveryLocation;
        this.driverFirstName = new String(driverFirstName);
        this.driverLastName = new String(driverLastName);
        
    }
    
    /**
     * 
     * @return 
     */
   public String getDepartureTime()
   {
       return this.departureDateTime;
   }
   
   /**
    * 
    * @return 
    */
   public String getDeliveryLocation()
   {
       return this.deliveryLocation;
   }
   
   /**
    * 
    * @return 
    */ 
   public String getdriverFirstName()
   {
       return this.driverFirstName;
   }
   
   /**
    * 
    * @return 
    */
   public String getDriverLastName()
   {
       return this.driverLastName;
   }
    
}
