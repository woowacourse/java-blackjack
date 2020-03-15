package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public abstract class User {
    private static final int ACE_CRITICAL_POINT = 11;
    private static final int ACE_UPPER_POINT = 11;
    private static final int ACE_LOWER_POINT = 1;
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARDS_SIZE = 2;
    protected static final int START_INDEX = 0;

    protected List<Card> cards = new ArrayList<>();
    protected String name;
    protected Status status;

    public void receiveDistributedCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> cards.add(cardDeck.getOneCard()));
        changeStatusIfBlackJack();
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        this.cards.add(cardDeck.getOneCard());
        changeStatusIfBust();
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce() && score <= ACE_CRITICAL_POINT) {
            score += ACE_UPPER_POINT - ACE_LOWER_POINT;
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

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
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

    public abstract List<Card> getInitialCards();

    public abstract boolean isReceivableOneMoreCard();
}
