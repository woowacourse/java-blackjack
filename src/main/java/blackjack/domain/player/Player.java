package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.score.Score;
import java.util.List;

public class Player {
    private final PlayerName playerName;
    private final PlayerCards playerCards;

    public Player(String name) {
        this.playerName = new PlayerName(name);
        this.playerCards = new PlayerCards();
    }

    public void hit(Deck deck) {
        Card card = deck.draw();
        playerCards.append(card);
    }

    public Score calculateScore() {
        return playerCards.calculateScore();
    }

    public List<Card> getCards() {
        return playerCards.getCards();
    }

    public boolean shouldHit() {
        Score score = calculateScore();
        if (score.isBusted() || score.isMaxScore()) {
            return false;
        }
        return true;
    }

    public String getName() {
        return playerName.name();
    }
}
