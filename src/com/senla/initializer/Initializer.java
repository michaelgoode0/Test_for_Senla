package com.senla.initializer;

import com.senla.console.actions.Authorization;
import com.senla.console.Builder;
import com.senla.console.MenuController;
import com.senla.console.Navigator;
import com.senla.dao.CardDAO;
import com.senla.fileworker.FileWorker;
import com.senla.fileworker.Parser;
import com.senla.services.CardService;

public class Initializer {
    String path = "./src/com/senla/resources/data/Card.txt";

    FileWorker fileWorker = new FileWorker(path);
    Parser parser = new Parser(fileWorker);

    CardDAO cardDAO = new CardDAO(parser, fileWorker, path);

    CardService cardService = new CardService(cardDAO);

    Authorization authorization = new Authorization(cardService);

    public void run() {
        MenuController menu = new MenuController(authorization, new Builder(authorization, cardService), new Navigator());
        menu.run();
    }
}

