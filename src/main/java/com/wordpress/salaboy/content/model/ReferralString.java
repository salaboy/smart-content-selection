/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.wordpress.salaboy.content.model;

import java.util.UUID;

/**
 *
 * @author salaboy
 */
public class ReferralString {
    private String key;
    private String referralString;

    public ReferralString(String referralString) {
        this.key = UUID.randomUUID().toString();
        this.referralString = referralString;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getReferralString() {
        return referralString;
    }

    @Override
    public String toString() {
        return "ReferralString{" + "key=" + key + ", referralString=" + referralString + '}';
    }

    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReferralString other = (ReferralString) obj;
        if ((this.key == null) ? (other.key != null) : !this.key.equals(other.key)) {
            return false;
        }
        if ((this.referralString == null) ? (other.referralString != null) : !this.referralString.equals(other.referralString)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.key != null ? this.key.hashCode() : 0);
        hash = 97 * hash + (this.referralString != null ? this.referralString.hashCode() : 0);
        return hash;
    }
    
    

}
