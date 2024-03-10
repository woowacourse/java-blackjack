package domain.gamer;

import domain.cards.Card;
import domain.cards.gamercards.PlayerCards;
import java.util.Collections;
import java.util.List;

public class Player {

    private final GamerName name;
    protected final PlayerCards cards;

    public Player(String name, PlayerCards cards) {
        this.name = new GamerName(name);
        this.cards = cards;
    }

    public void hit(Card card) {
        cards.addCard(card);
    }

    public boolean isNotBust() {
        return cards.hasScoreUnderBustThreshold();
    }

    public int finalizeCardsScore() {
        return cards.calculateScore();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }
}
