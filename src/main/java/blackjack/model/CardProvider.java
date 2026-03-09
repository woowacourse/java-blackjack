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
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
        this.deck.addAll(cards);
    }

    public void provideInitCards(Users users) {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        for (int i = INIT_CARDS_START_IDX; i < INIT_CARDS_END_IDX; i++) {
            for (Player player : players) {
                provideOneCard(player);
            }
            provideOneCard(dealer);
        }
    }

    public void provideOneCard(User user) {
        if (deck.peek() == null) {
            initDeck();
        }
        user.addCard(deck.poll());
    }
}
