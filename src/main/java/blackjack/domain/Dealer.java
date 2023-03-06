package blackjack.domain;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER_NAME = "딜러";

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME, initialGroup);
    }

    @Override
    public List<Card> getFirstOpenCardGroup() {
        return List.of(getStatus().get(0));
    }

    public WinningStatus comparePlayer(final Player player) {
        if (BlackJackRule.isBust(this)) {
            return compareByBust(player);
        }
        return compareByScore(player);
    }

    private WinningStatus compareByBust(final Player player) {
        if (BlackJackRule.isBust(player)) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }

    private WinningStatus compareByScore(final Player player) {
        if (BlackJackRule.isBust(player) || BlackJackRule.getScore(this) > BlackJackRule.getScore(player)) {
            return WinningStatus.LOSE;
        }
        if (BlackJackRule.getScore(this) == BlackJackRule.getScore(player)) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }
}
