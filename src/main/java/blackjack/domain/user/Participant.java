package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;
import blackjack.domain.game.ScoreState;
import blackjack.domain.vo.Score;
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

    public abstract List<Card> getFirstCard();

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public void addCards(List<Card> cards) {
        playerCards.addAll(cards);
    }

    public boolean isBust() {
        return getState().isBust();
    }

    public ScoreState getState() {
        return ScoreState.of(playerCards.getScore());
    }

    public List<Card> getAllCards() {
        return playerCards.toList();
    }

    public Score getScore() {
        return playerCards.getScore();
    }

    public String getName() {
        return name.getValue();
    }
}
