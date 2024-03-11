package model.controller;

import java.util.ArrayList;
import java.util.List;
import model.blackjackgame.BlackjackGame;
import model.card.Card;
import model.card.CardDispenser;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Players;
import model.view.InputView;
import model.view.OutputView;

public class BlackjackGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = inputView.requestPlayersName();
        BlackjackGame blackjackGame = new BlackjackGame(new Dealer(), players);
        List<Card> generatedCards = new ArrayList<>();
        for (int i = 0; i < (players.getPlayers().size() + 1) * 2; i++) {
            generatedCards.add(new Card(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape()));
        }
        Cards cards = new Cards(generatedCards);
        blackjackGame.distributeCardsForSetting(cards);
        outputView.printDistributeCards(players);
    }
}
