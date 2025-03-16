package blackjack.gambler;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.constant.MatchResult;
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


    public String getUsername() {
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
