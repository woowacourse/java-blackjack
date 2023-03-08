package blackjack.domain;

import java.util.List;

public abstract class Participant {
    protected static final String DEALER_NAME = "딜러";

    private final PlayerCards playerCards;
    private final Name name;

    Participant(Name name) {
        this.name = name;
        this.playerCards = new PlayerCards();
    }

    public abstract boolean isDrawable();

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public void addCards(List<Card> cards) {
        playerCards.addAll(cards);
    }

    public ScoreState getState() {
        return ScoreState.of(playerCards.getScore());
    }

    public List<Card> getCards() {
        return playerCards.toList();
    }

    public int getScore() {
        return playerCards.getScore();
    }

    public String getName() {
        return name.getValue();
    }
}
