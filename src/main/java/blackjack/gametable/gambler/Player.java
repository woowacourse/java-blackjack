package blackjack.gametable.gambler;

import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import java.util.List;
import java.util.Objects;

public class Player extends Gambler {

    private final PlayerName playerName;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
    }

    @Override
    public List<Card> openInitialCards() {
        return cards.openPlayerInitialCards();
    }

    public String getPlayerName() {
        return playerName.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerName);
    }
}
