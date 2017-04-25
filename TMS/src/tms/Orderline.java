package tms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oebar
 */
@Data
@NoArgsConstructor
public class Orderline
{
    private int orderLineID;
    private int orderID;
    private int productID;
    private int quantity;
}