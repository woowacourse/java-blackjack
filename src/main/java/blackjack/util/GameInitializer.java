package blackjack.util;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.exception.InvalidNameInputException;

import java.util.Collections;
import java.util.List;

public class GameInitializer {

    public static final int STARTING_CARD_COUNT = 2;

    private GameInitializer() {
    }

    public static Deck initializeDeck() {
        List<Card> allCards = Card.getAllCards();
        Collections.shuffle(allCards);
        return new Deck(allCards);
    }

    public static Players initializePlayers(List<Player> players, Deck deck) throws InvalidNameInputException {
        Players initializedPlayers = new Players(players);
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            initializedPlayers.unwrap().forEach(player -> player.draw(deck.draw()));
        }
        return initializedPlayers;
    }

    public static Dealer initializeDealer(Deck deck) throws InvalidNameInputException {
        Dealer dealer = new Dealer();
        for (int i = 0; i < STARTING_CARD_COUNT; i++) {
            dealer.draw(deck.draw());
        }
        return dealer;
    }
}
