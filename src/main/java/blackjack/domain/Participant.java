package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {

    List<Card> cards;
    Score score;

    public Participant(List<Card> cards) {
        this.cards = cards;
        this.score = new Score();
    }

    abstract boolean isAbleToReceive();

    public void receiveCard(Card card) {
        cards.add(card);
    }

    protected List<TrumpNumber> extractNumbers() {
        return cards.stream()
                .map(Card::getTrumpNumber)
                .collect(Collectors.toList());
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score.getScore();
    }
}
