package domain.participant;

import domain.card.Card;
import domain.card.HandCards;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {
    public static final int MIN_BUST_NUMBER = 21;

    private final Name name;
    private final HandCards handCards;

    public Participant(Name name) {
        this.name = name;
        this.handCards = new HandCards();
    }

    public int calculateScore() {
        int aceCount = handCards.getAceCount();
        int scoreSum = handCards.getCards().stream()
                .mapToInt(Card::getValue)
                .sum();
        while (scoreSum > 21 && aceCount > 0) {
            scoreSum -= 10;
            aceCount--;
        }
        return scoreSum;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(handCards.getCards());
    }

    public List<String> getCardNames() {
        return handCards.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCard(Card card) {
        handCards.takeCard(card);
    }
}
