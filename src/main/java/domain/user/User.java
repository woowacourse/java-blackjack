package domain.user;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.List;
import java.util.Objects;

public abstract class User {
    protected final PlayingCards playingCards;
    private final String name;

    public User(PlayingCards playingCards, String name) {
        this.playingCards = playingCards;
        this.name = name;
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public int calculateScore() {
        return playingCards.calculateScore();
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
