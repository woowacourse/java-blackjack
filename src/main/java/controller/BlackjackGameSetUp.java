package controller;

import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import util.InputParser;
import view.InputView;

import java.util.ArrayList;
import java.util.List;

import static domain.game.BlackjackRule.DEALER_NAME;

public class BlackjackGameSetUp {
    private final InputView inputView;

    public BlackjackGameSetUp(InputView inputView) {
        this.inputView = inputView;
    }

    public Players setUpPlayer(){
        List<String> parsedNames = InputParser.parseName(inputView.getNames());

        List<Player> players = new ArrayList<>();
        parsedNames.forEach(parsedName -> {
            BetAmount betAmount = new BetAmount(inputView.getBetAmount(parsedName));
            players.add(new Player(parsedName, betAmount));
        });

        return new Players(players);
    }

    public Dealer setUpDealer() {
        return new Dealer(DEALER_NAME);
    }

    public Deck setUpDeck(){
        CardShuffleStrategy cardShuffleStrategy = new RandomShuffleStrategy();
        return Deck.createDeck(cardShuffleStrategy);
    }
}
