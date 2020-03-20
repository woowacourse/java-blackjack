package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cards {
    public static final int BLACKJACK_SCORE = 21;
    private static final int START_INDEX = 0;
    private static final int COUNT_OF_INITIAL_DISTRIBUTE_CARDS = 2;

    private List<Card> cards = new ArrayList<>();

    public Cards() {
    }

    public void addInitialCards(CardDeck cardDeck) {
        IntStream.range(START_INDEX, COUNT_OF_INITIAL_DISTRIBUTE_CARDS)
                .forEach(i -> cards.add(cardDeck.pop()));
    }

    public void addOneMoreCard(CardDeck cardDeck) {
        this.cards.add(cardDeck.pop());
    }

    public int getScore() {
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

    public boolean isScoreUnder(int targetScore) {
        return getScore() <= targetScore;
    }

    public boolean isBlackJack() {
        return cards.size() == COUNT_OF_INITIAL_DISTRIBUTE_CARDS && getScore() == BLACKJACK_SCORE;
    }

    public boolean isNotBlackJack() {
        return !isBlackJack();
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SCORE;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public List<Card> subList(int startIndex, int endIndex) {
        return this.cards.subList(startIndex, endIndex);
    }

    public static int getInitialSize() {
        return COUNT_OF_INITIAL_DISTRIBUTE_CARDS;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}