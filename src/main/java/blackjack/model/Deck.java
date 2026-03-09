package blackjack.model;

import static blackjack.model.Constant.INITIAL_CARD_COUNT;
import static blackjack.model.Constant.INIT_CARDS_START_IDX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private final Queue<Card> deck = new LinkedList<>();

    public Deck() {
        initDeck();
    }

    private void initDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
        this.deck.addAll(cards);
    }

    public void provideInitCards(List<Player> players, Dealer dealer) {
        for (int i = INIT_CARDS_START_IDX; i < INITIAL_CARD_COUNT; i++) {
            for (Player player : players) {
                player.addCard(deck.poll());
            }
            dealer.addCard(deck.poll());
        }
    }

    public void provideOneCard(User user) {
        user.addCard(deck.poll());
    }
}

