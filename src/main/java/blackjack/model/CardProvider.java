package blackjack.model;

import static blackjack.model.constant.Constant.INIT_CARDS_END_IDX;
import static blackjack.model.constant.Constant.INIT_CARDS_START_IDX;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.card.Suit;
import blackjack.model.user.Dealer;
import blackjack.model.user.Player;
import blackjack.model.user.User;
import blackjack.model.user.Users;
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
