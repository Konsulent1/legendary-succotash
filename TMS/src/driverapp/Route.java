/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author oebar
 */
public class Route {
    
    private HashMap routeList;
    
    public Route()  {
        routeList = new HashMap();
    }
    
    /**
     * TO BE IMPLEMENTED WITH SQL
     * @return HashMap of routes
     */
    public HashMap getRoutes(){
        addToRoutes("Rema 1000 Larsgården", getDistance());
        addToRoutes("Kiwi Larsgården", getDistance());
        addToRoutes("Kiwi Ålesund Sentrum", getDistance());
        addToRoutes("Spar Larsgården", getDistance());
        addToRoutes("Coop Larsgården", getDistance());
        addToRoutes("Rema 1000 Ålesund Sentrum", getDistance());
        
        return this.routeList;
        
    }
    
    public void addToRoutes(String route, int distance){
        
        this.routeList.put(distance, route);
        
    }
    /**
     * dummy class, returns a random number between 0 and 1000km. To be 
     * implemented with sql
     * return kilometers
    */
    public int getDistance(){
        
        Random rand = new Random();
       
        
        int result = rand.nextInt(1000);
        
        return result;
    }
    
}
