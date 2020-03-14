package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class User {
    private static final int ACE_INCREMENT = 10;
    private static final int MAX_SCORE = 21;

    protected final String name;
    protected final List<Card> cards;

    public User(String name) {
        this.name = name;
        this.cards = new LinkedList<>();
    }

    public void receiveInitialCards(List<Card> initialCards) {
        this.cards.addAll(Objects.requireNonNull(initialCards));
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public void receiveCard(List<Card> newCards) {
        cards.addAll(newCards);
    }

    public int getTotalScore() {
        int score = incrementAceScore(cards.stream()
            .mapToInt(Card::getScore)
            .sum());
        if(score > MAX_SCORE) {
            return 0;
        }
        return score;
    }

    public String getName() {
        return this.name;
    }

    public boolean isBusted() {
        return getTotalScore() == 0;
    }

    public boolean isBlackJack() {
        return cards.size() == 2
                && getTotalScore() == MAX_SCORE;
    }

    public abstract List<Card> getInitialCards();

    public List<Card> getCards() {
        return cards;
    }

    private int incrementAceScore(int score) {
        if (cards.stream().anyMatch(Card::isAce) && score <= MAX_SCORE - ACE_INCREMENT) {
            score += ACE_INCREMENT;
        }
        return score;
    }


}
