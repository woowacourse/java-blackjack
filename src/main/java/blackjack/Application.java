package blackjack;

import blackjack.controller.BlackjackController;
import blackjack.service.CardDistributor;
import blackjack.service.RandomCardPicker;
import blackjack.service.RandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        RandomCardPicker cardPicker = new RandomCardPicker(randomNumberGenerator);
        CardDistributor cardDistributor = new CardDistributor(cardPicker);

        BlackjackController blackjackController = new BlackjackController(cardDistributor);

        blackjackController.startGame();
    }
}
