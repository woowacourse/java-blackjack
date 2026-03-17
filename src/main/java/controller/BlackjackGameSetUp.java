package controller;

import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import util.InputParser;
import view.InputView;

import java.util.List;

import static domain.game.BlackjackRule.DEALER_NAME;

public class BlackjackGameSetUp {
    private final InputView inputView;

    public BlackjackGameSetUp(InputView inputView) {
        this.inputView = inputView;
    }

    public Players setUpPlayer(){
        List<String> parsedNames = InputParser.parseName(inputView.getNames());

        Players players = new Players(parsedNames
                .stream()
                .map(Player::new).toList()
        );

        players.getPlayers().forEach(player -> player.bet(inputView.getBetAmount(player.name())));
        return players;
    }

    public Dealer setUpDealer() {
        return new Dealer(DEALER_NAME);
    }

    public Deck setUpDeck(){
        CardShuffleStrategy cardShuffleStrategy = new RandomShuffleStrategy();
        return Deck.createDeck(cardShuffleStrategy);
    }
}
