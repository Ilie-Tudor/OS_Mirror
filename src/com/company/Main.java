package com.company;
import java.util.Vector;

import Application.BaseApplication;
import Application.UserDefinedApplication;

import Handlers.*;
import Menu.Menu;

public class Main {

    public static void main(String[] args) {

        Menu menu = Menu.get_instance();

        while (true){
            System.out.println("--------------------------------------");
            menu.displayOptions();
            menu.selectOption();
            menu.handleOption();
        }

    }
}
