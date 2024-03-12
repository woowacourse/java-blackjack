package blackjackgame;

import blackjackgame.controller.BlackjackController;
import blackjackgame.domain.blackjack.BetMakerGamer;
import blackjackgame.domain.blackjack.BetMoney;
import blackjackgame.domain.blackjack.CardHolderGamer;
import blackjackgame.domain.blackjack.HoldingCards;
import blackjackgame.domain.card.Deck;
import blackjackgame.view.BetMoneyInputView;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BlackjackController blackjackController = new BlackjackController();
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
