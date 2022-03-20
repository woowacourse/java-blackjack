package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends Participant {

    private final Bet bet;

    public Player(final String name, final Bet bet) {
        super(name, new ArrayList<>());
        this.bet = bet;
        validatePlayerName(name);
    }

    private static void validatePlayerName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어명은 공백이 될 수 없습니다.");
        }
    }

    public double getProfit(final double profitRate) {
        return bet.calculateProfit(profitRate);
    }

    @Override
    public List<Card> showFirstCards() {
        return Collections.unmodifiableList(getCards());
    }

    @Override
    public boolean canDraw() {
        return !isBust();
    }
}
