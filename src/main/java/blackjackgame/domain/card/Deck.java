package blackjackgame.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import blackjackgame.domain.player.Dealer;
import blackjackgame.domain.player.Guests;
import blackjackgame.domain.player.Player;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        this(initializeCards());
    }

    public Deck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (final Symbol symbol : Symbol.values()) {
            for (final CardValue cardValue : CardValue.values()) {
                cards.add(new Card(symbol, cardValue));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public void initializePlayersCards(final Guests guests, final Dealer dealer) {
        for (int count = 0; count < 2; count++) {
            distributeCard(dealer);
            guests.getGuests()
                .forEach(this::distributeCard);
        }
    }

    public void distributeCard(final Player player) {
        player.addCard(pickOne());
    }

    public Card pickOne() {
        if(cards.peekFirst() == null){
            throw new IndexOutOfBoundsException("카드가 비었습니다.");
        }
        return cards.removeFirst();
    }
}
