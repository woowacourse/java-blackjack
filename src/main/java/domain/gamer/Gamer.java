package domain.gamer;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.List;
import java.util.Objects;

public class Gamer {
    final PlayingCards playingCards;
    private final String name;

    public Gamer(PlayingCards playingCards, String name) {
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

    public List<Card> getPlayingCards() {
        return playingCards.getCards();
    }

    int countCards() {
        return playingCards.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(playingCards, gamer.playingCards) &&
                Objects.equals(name, gamer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCards, name);
    }
}
