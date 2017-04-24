package tms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oebar
 */
@Data
@NoArgsConstructor
public class Product
{
    private int productID;
    private String name;
    private String description;
    private String price;
    private Tax tax;
    private boolean active;
}