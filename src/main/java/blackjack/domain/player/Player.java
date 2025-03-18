package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameScore;

import java.util.List;
import java.util.Objects;

public abstract class Player {

    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName, Cards cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public void pushDealCards(final List<Card> cards) {
        this.cards.addAll(cards);
    }

    public GameScore getGameScore() {
        return cards.getGameScore();
    }

    public boolean isPlayerBust() {
        return getGameScore().isBust();
    }

    public boolean isPlayerNotBust() {
        return !isPlayerBust();
    }

    public String getName() {
        return playerName.name();
    }

    abstract public List<Card> getOpenedCards();

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
