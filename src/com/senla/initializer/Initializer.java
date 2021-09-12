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
    String path = "src/com/senla/resources/data/Card.txt";

    FileWorker fileWorker = new FileWorker(path);
    Parser parser = new Parser(fileWorker);

    CardDAO cardDAO = new CardDAO(parser, fileWorker, path);

    CardService cardService = new CardService(cardDAO);

    Authorization authorization = new Authorization(cardService);

    public void run() throws Exception {
        MenuController menu = new MenuController(authorization,new Builder(authorization,cardService), new Navigator());
        menu.run();
    }
      /*  for(var i:cardDAO.getListOfCards()){
            System.out.println(i.toString());
        }
        String inputCardNumber=new Scanner(System.in).nextLine();
        String inputCode=new Scanner(System.in).nextLine();

        cardService.compareCardNumber(inputCardNumber);
        Card card = cardService.findCardNumber(inputCardNumber,Integer.parseInt(inputCode),cardDAO.getListOfCards());
        if(card!=null){
            cardService.addToBalance(1000,card);
        }*/
       /* Card card = new Card("6770-6500-1281-2831",4444,40);
        System.out.println(card.toString());
        cardDAO.saveCard(card);
        *//*String inputCardNumber=new Scanner(System.in).nextLine();
        cardService.compareCardNumber(inputCardNumber);*//*
        cardService.addToBalance(32,card);
        cardDAO.update(card);
        System.out.println(card.getBalance());*/
}

