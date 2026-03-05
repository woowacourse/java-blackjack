package blackjack.model;

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
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.addCard(deck.poll());
            }
            dealer.addCard(deck.poll());
        }
    }

    public void provideOneCard(User user) {
        user.addCard(deck.poll());
    }

    public boolean isGreaterThanTwentyOne(Player player, CardCalculator cardCalculator) {
        return cardCalculator.totalScore(player.getCardStatus().getCards()) >= 21;
    }
}
