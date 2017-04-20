/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by øystein
 */
@Data
@NoArgsConstructor
public class Employee {
    
    private int employeeID;
    private String firstname;
    private String lastName;
    private String location;
    private Department department;
    private String address;
    private ZipCode zipCode;
    private boolean active;
    
}
