package tms;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oebar
 */
@Data
@NoArgsConstructor
public class Order
{
    private int orderID;
    private int customerID;
    private int employeeID;
    private String placedDate;
    private String invoiceDate;
    private List<Orderline> orderline;
}