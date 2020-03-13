package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {
    private Result result;
    private Players players;

    @BeforeEach
    void setUp() {
        Dealer dealer = DefaultDealer.dealer();
        dealer.giveCards(Card.of(Symbol.ACE, Type.SPADE));

        players = Players.of("그니, 무늬, 포비");
        players.giveCards(0, Card.of(Symbol.TEN, Type.DIAMOND));
        players.giveCards(1, Card.of(Symbol.FIVE, Type.CLUB),
                Card.of(Symbol.SEVEN, Type.HEART));
        players.giveCards(2, Card.of(Symbol.TWO, Type.DIAMOND));

        result = Result.of(dealer, players);
    }

    @Test
    void of() {
        assertThat(result).isNotNull();
    }

    @Test
    void isWinner() {
        assertThat(result.isWinner(players.getPlayers().get(0))).isFalse();
        assertThat(result.isWinner(players.getPlayers().get(1))).isTrue();
        assertThat(result.isWinner(players.getPlayers().get(2))).isFalse();
    }

    @Test
    void getDealerWin() {
        assertThat(result.getDealerWin()).isEqualTo(2);
    }

    @Test
    void getDealerLose() {
        assertThat(result.getDealerLose()).isEqualTo(1);
    }
}