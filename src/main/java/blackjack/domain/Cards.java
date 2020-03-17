package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Cards {
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARDS_SIZE = 2;
    private static final int START_INDEX = 0;

    private Queue<Card> cards = new LinkedList<>();
    private Status status;

    public Cards() {
        status = Status.NONE;
    }

    public void receiveDistributedCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> cards.offer(cardDeck.getOneCard()));
        changeStatusIfBlackJack();
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        this.cards.offer(cardDeck.getOneCard());
        changeStatusIfBust();
    }

    private void changeStatusIfBlackJack() {
        if (calculateScore() == BLACKJACK_SCORE) {
            this.status = Status.BLACKJACK;
        }
    }

    private void changeStatusIfBust() {
        if (calculateScore() > BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce()) {
            score += Type.addExtraAcePointWhenUnderCriticalPoint(score, BLACKJACK_SCORE);
        }
        return score;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isStatusNone() {
        return status == Status.NONE;
    }

    public boolean isScoreUnder(int targetScore) {
        return calculateScore() <= targetScore;
    }

    public Status getStatus() {
        return status;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
