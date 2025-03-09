package blackjack.domain.user;

import blackjack.domain.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final PlayerName playerName;

    public Player(final String name) {
        super();
        this.playerName = new PlayerName(name);
    }

    @Override
    public List<Card> openInitialCards() {
        return super.cards;
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    public String getName() {
        return playerName.getName();
    }
}
