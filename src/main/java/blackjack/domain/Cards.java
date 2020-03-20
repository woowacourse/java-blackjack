package blackjack.domain;

import java.util.*;
import java.util.stream.IntStream;

public class Cards {
    private static final int BLACKJACK_SCORE = 21;
    private static final int START_INDEX = 0;
    private static final int DEFAULT_SCORE = 0;
    public static final int INITIAL_CARDS_SIZE = 2;

    private PriorityQueue<Card> cards = new PriorityQueue<>();
    private Status status;

    public Cards() {
        status = Status.HIT_ABLE;
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
        int score = DEFAULT_SCORE;
        for (Card card : cards) {
            score += card.getPoint(score);
        }
        return score;
    }

    public boolean isStatusNone() {
        return status == Status.HIT_ABLE;
    }

    public boolean isScoreUnder(int targetScore) {
        return calculateScore() <= targetScore;
    }

    public boolean isBlackJackStatus() {
        return status == Status.BLACKJACK;
    }

    public boolean isBustStatus() {
        return status == Status.BUST;
    }

    public Status getStatus() {
        return status;
    }

    public List<Card> getInitialCards(int initialCardsSize) {
        return new ArrayList<>(cards)
                .subList(START_INDEX, initialCardsSize);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
