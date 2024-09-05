package com.mycompany.app;
import com.mycompany.app.presentation.Menu;

public class Main {
    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.displayMenu();
        }catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
