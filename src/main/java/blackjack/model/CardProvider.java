package blackjack.model;

import static blackjack.model.Constant.INIT_CARDS_END_IDX;
import static blackjack.model.Constant.INIT_CARDS_START_IDX;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardProvider {
    private final Queue<Card> deck = new LinkedList<>();

    public CardProvider() {
        initDeck();
    }

    private void initDeck() {
        List<Card> cards = new ArrayList<>();
        for (Card card : Card.values()) {
            cards.add(card);
        }
        Collections.shuffle(cards);
        this.deck.addAll(cards);
    }

    public void provideInitCards(List<Player> players, Dealer dealer) {
        for (int i = INIT_CARDS_START_IDX; i < INIT_CARDS_END_IDX; i++) {
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
