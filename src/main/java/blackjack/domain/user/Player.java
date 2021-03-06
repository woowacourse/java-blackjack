package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Player implements User {
    private final List<Card> cards = new ArrayList<>();
    private final Name name;

    public Player(String name) {
        this.name = new Name(name);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addFirstCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public void removeAll() {
        cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isGameOver(int gameOverScore) {
        int score = calculateScore(gameOverScore);
        return (score > gameOverScore);
    }

    public int calculateScore(int gameOverScore) {
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

    private boolean belowScore(int score, int gameOverScore) {
        return score <= gameOverScore;
    }

    private boolean emptyAceCard(int aceCount) {
        return aceCount > 0;
    }

    private int findAceCount() {
        return (int) cards.stream()
                .filter(Card::containAce)
                .count();
    }

    public String getName() {
        return name.getName();
    }

    public List<String> getCardsStatus() {
        return cards.stream()
                .map(Card::getCardStatus)
                .collect(Collectors.toList());
    }
}
