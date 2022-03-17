package blackjack.domain.paticipant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.running.PlayerRunning;
import java.util.List;

public final class Player extends AbstractParticipant {

    private final int betMoney;

    private Player(final Name name, final int betMoney, final BlackjackGameState gameState) {
        super(name, gameState);
        checkNotPositiveBetMoney(betMoney);
        this.betMoney = betMoney;
    }

    public Player(final Name name, final int betMoney, final Cards cards) {
        this(name, betMoney, new PlayerRunning(cards));
    }

    public Player(final Name name, final int betMoney, final CardDeck cardDeck) {
        this(name, betMoney, new Cards(List.of(cardDeck.provideCard(), cardDeck.provideCard())));
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
