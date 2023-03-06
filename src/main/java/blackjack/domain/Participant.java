package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {
    protected final List<Card> cards;
    protected final Score score;

    protected Participant(List<Card> cards) {
        this.cards = cards;
        this.score = new Score();
    }

    abstract boolean isAbleToReceive();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    protected List<Letter> extractNumbers() {
        return cards.stream()
                .map(Card::getCardLetter)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return score.getScore();
    }
}
