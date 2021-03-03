package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class BlackJackGame {

    private final Players players;
    private final Deck deck;

    public BlackJackGame(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return this.deck.getCards();
    }
}
