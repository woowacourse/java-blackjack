package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player extends Participant {

    private static final int START_CARD_SIZE = 2;

    private final Name name;

    Player(List<Card> cards, Name name) {
        super(cards);
        this.name = Objects.requireNonNull(name);
    }

    public static Player from(String name) {
        return new Player(Collections.emptyList(), new Name(name));
    }

    public boolean isWin(Dealer dealer) {
        return !dealer.isWin(this);
    }

    public boolean isEqualName(Player player) {
        return name.equals(player.name);
    }

    @Override
    protected int getMaxDrawableScore() {
        return BLACKJACK_SCORE;
    }

    @Override
    protected int getStartCardSize() {
        return START_CARD_SIZE;
    }

    public String getName() {
        return name.getName();
    }
}
