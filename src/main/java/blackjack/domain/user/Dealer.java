package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Dealer {
    public static final int TURN_OVER_COUNT = 16;
    private static final String name = "딜러";

    private final List<Card> cards = new ArrayList<>();

    public void addFirstCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public static String getName() {
        return name;
    }

    public boolean isGameOver(int gameOverScore) {
        int score = calculateScore(gameOverScore);

        return (score > TURN_OVER_COUNT);
    }

    public int calculateScore(int gameOverScore) {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        int aceCount = 0;
        if (score > gameOverScore) {
            aceCount = findAceCount();
        }

        for (int i = 0; i < aceCount; i++) {
            score = score - 11;
            score = score + 1;
            if (score <= gameOverScore) {
                break;
            }
        }
        return score;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public void addCard(Card makeOneCard) {
        cards.add(makeOneCard);
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }
}
