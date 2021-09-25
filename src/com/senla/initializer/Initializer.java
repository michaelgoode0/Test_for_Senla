package com.senla.initializer;

import com.senla.console.Builder;
import com.senla.console.MenuController;
import com.senla.console.Navigator;
import com.senla.dao.CardDao;
import com.senla.entity.Card;
import com.senla.entity.Session;
import com.senla.tools.fileworker.FileWorker;
import com.senla.tools.fileworker.Parser;
import com.senla.services.CardService;
import com.senla.services.SessionService;

public class Initializer {
    Session session=new Session();
    String path = "./src/com/senla/resources/data/Card.csv";

    FileWorker fileWorker = new FileWorker(path);
    Parser parser = new Parser(fileWorker);

    CardDao cardDAO = new CardDao(parser, fileWorker, path);

    CardService cardService = new CardService(cardDAO);

    SessionService service= new SessionService();

    public void run(){
        MenuController menu = new MenuController(service,session, new Builder(session, cardService,service), new Navigator());
        menu.run();
    }
}

