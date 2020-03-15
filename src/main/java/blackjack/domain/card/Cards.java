package blackjack.domain.card;

import blackjack.domain.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cards {
    protected static final int BLACKJACK_SCORE = 21;

    private List<Card> cards;
    private Status status;

    public Cards() {
        this.cards = new ArrayList<Card>();
        this.status = Status.NONE;
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public int calculateScore() {
        int score = this.cards.stream()
                .filter(Predicate.not(Card::isAce))
                .mapToInt(Card::getPoint)
                .sum();

        for (Card aceCard : getAceCards()) {
            score += aceCard.getPointOfAceUsing(score);
        }
        return score;

    }

    public void changeStatus() {
        if (calculateScore() == BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
        if (calculateScore() > BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
    }

    public int size() {
        return this.cards.size();
    }

    public boolean isNoneStatus() {
        return this.status == Status.NONE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    public List<Card> getAceCards() {
        return this.cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
    }

    public Status getStatus() {
        return this.status;
    }
}
