/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

import java.util.ArrayList;

/**
 *
 * @author oebar
 */
public class Route {
    
    private ArrayList routeList;
    
    public Route()  {
        routeList = new ArrayList<String>();
    }
    
    public ArrayList getRoutes(){
        
        return this.routeList;
        
    }
    
    public void addToRoutes(String route){
        
        this.routeList.add(route);
        
    }
    
}
