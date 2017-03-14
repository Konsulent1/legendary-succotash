/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Wearhouse;

/**
 *
 * @author Vinh
 */
public class UserLogin {
    
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
    
}
