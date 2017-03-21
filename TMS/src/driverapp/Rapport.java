/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driverapp;

/**
 *
 * @author HaIIvard
 */
public class Rapport
{
    private String delayReason;
    private String delayInMin;
    
    public Rapport(String delayReason, String delayInMin){
        this.delayInMin = delayInMin;
        this.delayReason = delayReason;
    }
    
    public String getDelayReason(){
        return delayReason;
    }
    
    public String getDelayInMin(){
        return delayInMin;
    }
}
