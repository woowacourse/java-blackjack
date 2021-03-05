package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer {
    public static final int TURN_OVER_COUNT = 16;

    private final String name = "딜러";
    private final List<Card> cards = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void addFirstCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isGameOver(final int gameOverScore) {
        int score = calculateScore(gameOverScore);

        return (score > TURN_OVER_COUNT);
    }

    public int calculateScore(final int gameOverScore) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = 0;
        if (score > gameOverScore) {
            aceCount = findAceCount();
        }

        while (emptyAceCard(aceCount) && !belowScore(score, gameOverScore)) {
            score = Number.ACE.useSecondScore(score);
        }
        return score;
    }

    private boolean belowScore(final int score, final int gameOverScore) {
        return score <= gameOverScore;
    }

    private boolean emptyAceCard(final int aceCount) {
        return aceCount > 0;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public void addCard(final Card makeOneCard) {
        cards.add(makeOneCard);
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }
}
