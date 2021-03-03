package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public static void play() {
        OutputView.printInputNames();
        BlackJackGame blackJackGame = new BlackJackGame(InputView.inputString(), Card.getAllCards());
        blackJackGame.prepare();
        OutputView.printGameInitializeMessage(blackJackGame.getDealer(), blackJackGame.getPlayers(), BlackJackGame.STARTING_CARD_COUNT);

    }

    public static void main(String[] args) {
        BlackJackController.play();
    }

}
