package model.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.blackjackgame.BlackjackGame;
import model.card.Card;
import model.card.CardType;
import model.card.CardDispenser;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Player;
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
        Dealer dealer = new Dealer();
        BlackjackGame blackjackGame = new BlackjackGame(dealer, players);
        List<Card> generatedCards = new ArrayList<>();
        for (int i = 0; i < (players.getPlayers().size() + 1) * 2; i++) {
            generatedCards.add(new Card(CardDispenser.generateCardNumber(), CardDispenser.generateCardShape()));
        }
        Cards cards = new Cards(generatedCards);
        blackjackGame.distributeCardsForSetting(cards);
        outputView.printDistributeCards(blackjackGame.getDealer(), players);
        outputView.printCardsStock(blackjackGame.getDealer().getName(),
                Collections.singletonList(captureCardType(blackjackGame.getDealer()).get(0)));
        blackjackGame.getPlayers().getPlayers()
                .forEach(player -> outputView.printCardsStock(player.getName(), captureCardType(player)));
    }

    private List<CardType> captureCardType(Dealer dealer) {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < dealer.cardsSize(); i++) {
            cardTypes.add(new CardType(dealer.getCards().getCards().get(i)));
        }
        return cardTypes;
    }

    private List<CardType> captureCardType(Player player) {
        List<CardType> cardTypes = new ArrayList<>();
        for (int i = 0; i < player.cardsSize(); i++) {
            cardTypes.add(new CardType(player.getCards().getCards().get(i)));
        }
        return cardTypes;
    }
}
