package domain.user;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.result.Result;

import java.util.List;
import java.util.Objects;

public abstract class User {
    public static final int INIT_CARDS_SIZE = 2;
    public static final int BLACKJACK_VALUE = 21;
    protected final PlayingCards playingCards;
    private final String name;
    protected final Money money;

    User(PlayingCards playingCards, String name, Money money) {
        this.playingCards = playingCards;
        this.name = name;
        this.money = money;
    }

    void addCard(Card card) {
        playingCards.add(card);
    }

    public int calculateScore() {
        return playingCards.calculate();
    }

    public boolean isBlackjack() {
        int score = playingCards.calculate();
        return playingCards.size() == INIT_CARDS_SIZE && score == BLACKJACK_VALUE;
    }

    public boolean isNotBlackjack() {
        int score = playingCards.calculate();
        return playingCards.size() != INIT_CARDS_SIZE || score != BLACKJACK_VALUE;
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public boolean isNotBust() {return playingCards.isNotBust();}

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return playingCards.getCards();
    }

//    public abstract int calculateProfit(Result result);

    void hit(Card card) {
        playingCards.add(card);
    }

    int countCards() {
        return playingCards.size();
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
