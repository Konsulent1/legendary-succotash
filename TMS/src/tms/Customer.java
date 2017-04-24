package tms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Robert on 23-Mar-17.
 */
@Data
@NoArgsConstructor
public class Customer
{
    private int customerID;
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private ZipCode zipCode;
    private boolean active;
}