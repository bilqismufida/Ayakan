/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.method.ayakan.model;

/**
 *
 * @author Nayaka
 */
public class UserProfil {
    private int userId;
    private String password, nama;

    public UserProfil(int userId, String password, String nama) {
        this.userId = userId;
        this.password = password;
        this.nama = nama;
    }
    
    
    public void updateProfile(String nama, String password){
        this.nama = nama;
        this.password = password;
    }
    
    public void getInfo(){
        System.out.println("User ID: " + userId);
        System.out.println("Username:" + nama);
    }
}
