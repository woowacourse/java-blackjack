package blackjack;

import blackjack.controller.BlackjackController;
import java.util.Random;
import blackjack.service.CardDistributor;
import blackjack.service.RandomCardPicker;

public class Application {

    public static void main(String[] args) {
        Random random = new Random();
        CardDistributor cardDistributor = new CardDistributor(new RandomCardPicker(random));

        BlackjackController blackjackController = new BlackjackController(cardDistributor);

        blackjackController.startGame();
    }
}
