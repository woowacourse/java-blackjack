package blackjack.domain.participant;

import blackjack.domain.ScoreState;
import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final Name name;
    private final PlayerCards playerCards;

    Participant(Name name) {
        this.name = name;
        this.playerCards = new PlayerCards();
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public void addCards(List<Card> cards) {
        playerCards.addAll(cards);
    }

    public ScoreState getState() {
        return ScoreState.of(playerCards.getScore(), playerCards.size());
    }

    public int getScore() {
        return playerCards.getScore();
    }

    public List<Card> getCards() {
        return playerCards.toList();
    }

    public String getName() {
        return name.getValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
