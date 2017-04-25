/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;

/**
 *
 * @author Kristian
 */
public class PalletCom {
    
    private String PalletID;
    private String Quantity;
    private String Destination;
    private String Product;
    
    public PalletCom(String PalletID, String Quantity, String Destination, String Product){
        this.PalletID = PalletID;
        this.Quantity = Quantity;
        this.Destination = Destination;
        this.Product = Product;  
        System.out.println("product:" + Product);
    }

    public String getPalletID() {
        return PalletID;
    }

    public void setPalletID(String PalletID) {
        this.PalletID = PalletID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String Product) {
        this.Product = Product;
    }

}


//INSERT INTO Pallet ('PalletID','Quantity','Destination'(choose one),'ProductID')