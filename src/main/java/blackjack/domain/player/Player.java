package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final PlayerCards playerCards;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.playerCards = new PlayerCards();
    }

    public void draw(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public Score calculateScore() {
        return playerCards.calculateScore();
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public boolean isDrawAble() {
        Score score = calculateScore();
        if (score.isBusted() || score.isMaxScore()) {
            return false;
        }
        return true;
    }

    public int getScore() {
        Score score = calculateScore();
        return score.getScore();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public String getName() {
        return playerName.name();
    }
}
