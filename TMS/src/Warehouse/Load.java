/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Warehouse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Vinh
 */
public class Load {
    
    private HashMap<String, String> PalletStatus;
    
    private int locationRow;
    
    private int locationNr;
    
    private boolean loadStatus = false;
    
    /**
     * 
     * @param locationRow
     * @param locationNr 
     */
    public Load(int locationRow, int locationNr)
    {
        this.locationRow = locationRow;
        this.locationNr = locationNr;
    }
    
    /**
     * 
     * @return 
     */
    public int getLocationRow()
    {
        return this.locationRow;
    }
    
    /**
     * 
     * @return 
     */
    public int getLocationNr()
    {
        return this.locationNr;
    }
    
    /**
     * 
     * @param palletInput 
     */
    public void chechPalletStatus(String palletInput)
    {
        Iterator it = PalletStatus.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry thisEntry = (Map.Entry) it.next();
            
            if(thisEntry.getKey().equals(palletInput))
            {
                this.loadStatus = true;
            }
        }
    }
    
}
