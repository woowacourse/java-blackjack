package domain.user;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.result.MatchRule;
import domain.result.Result;

import java.util.Objects;

public abstract class User {
    protected static final int LOSE_PENALTY_RATE = -1;
    private static final int INIT_CARDS_SIZE = 2;
    private static final int BLACKJACK_VALUE = 21;

    protected final String name;
    protected PlayingCards playingCards;
    protected Profit profit = new Profit(0);

    User(String name) {
        this.name = name;
    }

    User(String name, PlayingCards playingCards) {
        this.name = name;
        this.playingCards = playingCards;
    }

    public abstract Result match(User user, MatchRule matchRule);

    public int calculateScore() {
        return playingCards.calculate();
    }

    public boolean isBlackjack() {
        int score = playingCards.calculate();
        return playingCards.isSameSize(INIT_CARDS_SIZE) && score == BLACKJACK_VALUE;
    }

    public boolean isNotBlackjack() {
        int score = playingCards.calculate();
        return playingCards.isSameSize(INIT_CARDS_SIZE) || score != BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    protected void hit(Card card) {
        playingCards = playingCards.add(card);
    }

    public int getProfit() {
        return profit.getValue();
    }

    void addCard(Card card) {
        playingCards= playingCards.add(card);
    }

    int countCards() {
        return playingCards.size();
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(playingCards, user.playingCards) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCards, name);
    }
}
