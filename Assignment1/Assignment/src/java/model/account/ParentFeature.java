/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.account;

/**
 *
 * @author ASUS
 */
public class ParentFeature {
    private ParentAccount username;
    private  Feature fid;

    public ParentAccount getUsername() {
        return username;
    }

    public void setUsername(ParentAccount username) {
        this.username = username;
    }

    public Feature getFid() {
        return fid;
    }

    public void setFid(Feature fid) {
        this.fid = fid;
    }
    
}
