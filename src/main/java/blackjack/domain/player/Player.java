package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final PlayerCards playerCards;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.playerCards = PlayerCards.createEmptyCards();
    }

    public void draw(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public Outcome calculateOutcome() {
        return playerCards.calculateOutcome();
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public boolean isDrawAble() {
        Outcome outcome = calculateOutcome();
        if (outcome.isBusted() || outcome.isMaxScore()) {
            return false;
        }
        return true;
    }

    public int getScore() {
        Outcome outcome = calculateOutcome();
        return outcome.getScore();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public String getName() {
        return playerName.name();
    }
}
