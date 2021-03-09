package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;

import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    public abstract String getName();

    public abstract boolean isGameOver(int gameOverScore);

    protected abstract List<Card> getCards();

    public void addFirstCards(List<Card> cards) {
        getCards().addAll(cards);
    }

    public int calculateScore(int gameOverScore) {
        int score = getCards().stream()
                .mapToInt(Card::score)
                .sum();
        int aceCount = 0;
        if (score > gameOverScore) {
            aceCount = findAceCount();
        }
        return changeAceScore(gameOverScore, score, aceCount);
    }

    private int changeAceScore(int gameOverScore, int score, int aceCount) {
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
        return (int) getCards().stream()
                .filter(Card::containAce)
                .count();
    }

    public void addCard(Card card) {
        getCards().add(card);
    }

    public List<String> getCardsStatus() {
        return getCards().stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.toList());
    }
}
