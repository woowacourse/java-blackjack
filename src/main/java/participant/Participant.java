package participant;

import card.Card;
import card.Cards;
import java.util.Collections;
import java.util.List;

public class Participant {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int BUST_NUMBER = 22;

    private final String nickname;
    private final Cards cards;

    public Participant(String nickname) {
        this.nickname = nickname;
        this.cards = new Cards();
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return cards.calculateNearestTotal() == BLACKJACK_NUMBER && getCards().size() == 2;
    }

    public boolean isBust() {
        return cards.calculateNearestTotal() >= BUST_NUMBER;
    }

    public int score() {
        return cards.calculateNearestTotal();
    }

    public String getNickname() {
        return nickname;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
