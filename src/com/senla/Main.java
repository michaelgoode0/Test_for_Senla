package com.senla;

import com.senla.dao.CardDAO;
import com.senla.fileworker.FileWorker;
import com.senla.fileworker.Parser;
import com.senla.initializer.Initializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws Exception {
        Initializer initializer= new Initializer();
        initializer.run();
    }
}
