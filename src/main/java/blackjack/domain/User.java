package blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public abstract class User {
    private static final int BLACKJACK_SCORE = 21;
    private static final int INITIAL_CARDS_SIZE = 2;
    protected static final int START_INDEX = 0;

    protected Queue<Card> cards = new LinkedList<>();
    protected String name;
    protected Status status;

    public void receiveDistributedCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, INITIAL_CARDS_SIZE)
                .forEach(i -> cards.offer(cardDeck.getOneCard()));
        changeStatusIfBlackJack();
    }

    private void changeStatusIfBlackJack() {
        if (calculateScore() == BLACKJACK_SCORE) {
            this.status = Status.BLACKJACK;
        }
    }

    public void receiveOneMoreCard(CardDeck cardDeck) {
        this.cards.offer(cardDeck.getOneCard());
        changeStatusIfBust();
    }

    private void changeStatusIfBust() {
        if (calculateScore() > BLACKJACK_SCORE) {
            this.status = Status.BUST;
        }
    }

    public int getCardsSize() {
        return this.cards.size();
    }

    public int calculateScore() {
        int score = calculateRawScore();
        if (hasAce()) {
            score += Type.chooseAcePoint(score, BLACKJACK_SCORE);
        }
        return score;
    }

    private int calculateRawScore() {
        return cards.stream()
                .filter(card -> !card.isAce())
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
        return new ArrayList<>(cards);
    }

    public abstract List<Card> getInitialCards();

    public abstract boolean isReceivableOneMoreCard();
}
