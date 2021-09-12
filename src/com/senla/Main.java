package com.senla;

import com.senla.dao.CardDAO;
import com.senla.fileworker.FileWorker;
import com.senla.fileworker.Parser;
import com.senla.initializer.Initializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {



    public static void main(String[] args) throws Exception {
       /* String REGEX="[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]";
        String INPUT="6770-6500-1281-2831";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);
        System.out.println("lookingAt(): "+matcher.lookingAt());
        System.out.println("matches(): "+matcher.matches());*/
        Initializer initializer= new Initializer();
        initializer.run();
    }
}
