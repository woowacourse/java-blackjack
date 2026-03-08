package domain.participants;

import domain.card.Card;
import domain.card.Hand;
import domain.Score.Score;
import util.ErrorMessage;
import java.util.Collections;
import java.util.List;

public abstract class Participant {
    private static final int MIN_NAME_SIZE = 2;
    private static final int MAX_NAME_SIZE = 7;

    private final String name;
    private final Hand hand;

    protected Participant(String name, Hand hand) {
        validateNameLength(name);
        this.name = name;
        this.hand = hand;
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NAME.getMessage());
        }
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.getCards());
    }

    public String getName() {
        return name;
    }

    public Score getScore() {
        return hand.getScore();
    }

    protected Hand getHand() {
        return hand;
    }
}