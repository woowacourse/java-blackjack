package blackjack.model.card;

import blackjack.model.user.User;
import blackjack.model.user.Users;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class CardProvider {

    private static final int INIT_CARDS_START_IDX = 0;
    private static final int INIT_CARDS_END_IDX = 2;

    private final Queue<Card> deck = new LinkedList<>();

    public CardProvider() {
        initDeck();
    }

    private void initDeck() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(cards);
        this.deck.addAll(cards);
    }

    public void provideInitCards(Users users) {
        for (User user : users.getUsers()) {
            provideTwoCard(user);
        }
    }

    public void provideOneCard(User user) {
        if (deck.peek() == null) {
            initDeck();
        }
        user.addCard(deck.poll());
    }

    private void provideTwoCard(User user) {
        for (int i = INIT_CARDS_START_IDX; i < INIT_CARDS_END_IDX; i++) {
            provideOneCard(user);
        }
    }
}
