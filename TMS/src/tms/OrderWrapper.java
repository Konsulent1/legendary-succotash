/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThomasSTodal
 */
public class OrderWrapper
{
    private List<Order> orders;
    private final Communicator comm;
    
    public OrderWrapper()
    {
        comm = new Communicator();
    }
    
    /**
     * Retrieves all Order objects from ERP database.
     * 
     * @return true if successful, false if not.
     */
    public boolean retrieveAllOrders()
    {
        orders = comm.ordersGet();
        if (orders != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Order> getAllOrders()
    {
        return orders;
    }
    
    public Order getOrderByID(int orderID)
    {
        Order returnOrder = null;
        for(Order order : orders) {
            if(order.getOrderID() == orderID) {
                returnOrder = order;
            }
        }
        return returnOrder;
    }
    
    
}
