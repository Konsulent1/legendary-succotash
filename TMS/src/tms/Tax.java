package tms;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author oebar
 */
@Data
@NoArgsConstructor
public class Tax
{
    private int taxID;
    private String value;
}