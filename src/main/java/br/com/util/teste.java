/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.util.Date;

/**
 *
 * @author guilu
 */
public class teste {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        System.out.println(date.getTime());
        
        Thread.sleep(3000);
        Date data = new Date();
        System.out.println(data.getTime());
        
        long name = Math.abs(data.getTime() - date.getTime())/1000;
        System.out.println(name);
    }
}
