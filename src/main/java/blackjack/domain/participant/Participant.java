package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public abstract class Participant {

    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    protected final String name;
    protected final Hand cardHand;

    public Participant(String name, Hand cardHand) {
        validateName(name);
        this.name = name;
        this.cardHand = cardHand;
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException("이름은 1~5자만 가능합니다");
        }
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public abstract int getHandTotal();
}
