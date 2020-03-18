package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {
    public static final int BLACKJACK_SCORE = 21;
    private static final int START_INDEX = 0;
    private static final int COUNT_OF_INITIAL_DISTRIBUTE_CARDS = 2;

    private List<Card> cards = new ArrayList<>();
    private Status status;

    public Cards() {
        this.status = Status.NONE;
    }

    public void receiveInitialCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, COUNT_OF_INITIAL_DISTRIBUTE_CARDS)
                .forEach(i -> cards.add(cardDeck.pop()));
        changeStatusIfBlackJack();
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        this.cards.add(cardDeck.pop());
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
        List<Type> types = this.cards.stream()
                .map(Card::getType)
                .sorted((Comparator.comparingInt(Type::getPoint)))
                .collect(Collectors.toList());

        int score = 0;
        for (Type type : types) {
            score += type.getPointUsingPreviousScore(score);
        }
        return score;
    }

    public boolean isStatusNone() {
        return status == Status.NONE;
    }

    public boolean isScoreUnder(int targetScore) {
        return calculateScore() <= targetScore;
    }

    public static int getInitialSize() {
        return COUNT_OF_INITIAL_DISTRIBUTE_CARDS;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }

    public Status getStatus() {
        return this.status;
    }
}