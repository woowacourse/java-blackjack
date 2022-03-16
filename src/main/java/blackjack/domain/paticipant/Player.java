package blackjack.domain.paticipant;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.state.BlackjackGameState;
import blackjack.domain.state.PlayerRunning;
import java.util.List;

public class Player extends AbstractParticipant {

    private final int betMoney;

    private Player(final Name name, final int betMoney, final BlackjackGameState gameState) {
        super(name, gameState);
        checkNotPositiveBetMoney(betMoney);
        this.betMoney = betMoney;
    }

    public Player(final Name name, final int betMoney, final Cards cards) {
        this(name, betMoney, new PlayerRunning(cards));
    }

    public static Player createPlayer(final Name name, final int betMoney, final CardDeck cardDeck) {
        final Cards cards = new Cards(List.of(cardDeck.provideCard(), cardDeck.provideCard()));
        return new Player(name, betMoney, cards);
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
