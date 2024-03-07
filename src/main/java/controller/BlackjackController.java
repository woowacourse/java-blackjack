package controller;

import model.blackjackgame.BlackjackGame;
import model.card.CardDispenser;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Players;
import view.InputView;
import view.OutputView;

public class BlackjackController {

    private final CardDispenser cardDispenser = CardDispenser.getCardDispenser();

    public void run() {
        BlackjackGame blackjackGame = start();
        setting(blackjackGame);
    }

    private BlackjackGame start() {
        Players players = Players.from(InputView.askPlayerNames());
        Dealer dealer = new Dealer();
        return new BlackjackGame(dealer, players);
    }

    private void setting(BlackjackGame blackjackGame) {
        int cardCount = blackjackGame.countSettingCard();
        Cards cardsForSetting = cardDispenser.dispenseCards(cardCount);

        blackjackGame.distributeCardsForSetting(cardsForSetting);
        OutputView.printCardsAfterSetting(blackjackGame);
    }
}
