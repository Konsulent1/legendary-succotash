package Wearhouse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Alexander Eilert Berg
 */
public class UserLogin
{
    //Username,Password
    private HashMap<String,String> loginData;
    private boolean correctUsername = false;
    private boolean correctPassword = false;
    
    
    /**
     * 
     * @param userInputUsername
     * @param userInputPassword
     * @return 
     */
    public boolean checkInput(String userInputUsername, String userInputPassword)
    {
        boolean correctInput = false;
        
        checkUsername(userInputUsername);
        checkPassword(userInputPassword);
        
        if (correctUsername && correctPassword)
        {
            correctInput = true;
        }
        
        return correctInput; 
    }
            
    
    
    
    /**
     * 
     * @param userInput
     */
    public void checkUsername(String userInput)
    {
        Iterator it = loginData.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry thisEntry = (Map.Entry) it.next();
            
            if(thisEntry.getKey().equals(userInput))
            {
                this.correctUsername = true; 
            }
        }
    }
    
    
    /**
     * 
     * @param userInput
     */
    public void checkPassword(String userInput)
    {
        Iterator it = loginData.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry thisEntry = (Map.Entry) it.next();
            
            if(thisEntry.getKey().equals(userInput))
            {
                this.correctPassword = true; 
            }
        }
    }
    
    public void addDummyData()
    {

    }
    
<<<<<<< HEAD
    private String userId;
    private String userPw;
    
    UserLogin(String userId, String userPw)
    {
        this.userId = userId;
        this.userPw = userPw;
    }
    
    public String getUserId()
    {
        return userId;
    }
    
    public String getUserPw()
    {
        return userPw;
    }
    
=======
            
>>>>>>> 7f83aa627782f2362412a14080bdabe4b963928d
}
