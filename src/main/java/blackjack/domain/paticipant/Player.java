package blackjack.domain.paticipant;

import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.PlayerRunning;

public class Player extends AbstractParticipant {

    private final int betMoney;

    private Player(final String name, final int betMoney, final BlackjackGameState gameState) {
        super(name, gameState);
        checkNotPositiveBetMoney(betMoney);
        this.betMoney = betMoney;
    }

    public Player(final String name, final int betMoney, final Cards cards) {
        this(name, betMoney, new PlayerRunning(cards));
    }

    private void checkNotPositiveBetMoney(final int betMoney) {
        if (betMoney <= 0) {
            throw new IllegalArgumentException("배팅금액은 0이하의 값이 들어올 수 없습니다.");
        }
    }

    public double profit(final Dealer dealer) {
        return blackjackGameState.profit(betMoney, dealer.blackjackGameState);
    }
}
