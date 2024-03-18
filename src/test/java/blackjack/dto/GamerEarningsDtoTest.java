package blackjack.dto;

import static blackjack.dto.GamerEarningsDto.fromDealerAndPlayers;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Money;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerBetAmounts;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerEarningsDtoTest {

    PlayerBetAmounts playerBetAmounts;

    @BeforeEach
    void setUp() {
        Player player1 = new Player("p1", List.of(
                new Card(Shape.SPADE, Number.ACE),
                new Card(Shape.HEART, Number.TEN)
        ));
        Money player1BetAmount = new Money(15000L);

        Player player2 = new Player("p2", List.of(
                new Card(Shape.DIAMOND, Number.KING),
                new Card(Shape.CLOVER, Number.QUEEN)
        ));
        Money player2BetAmount = new Money(20000L);

        playerBetAmounts = new PlayerBetAmounts(Map.of(
                player1, player1BetAmount,
                player2, player2BetAmount
        ));
    }

    @Test
    @DisplayName("딜러-플레이어 순으로 순서가 보장되어야 한다.")
    void fromDealerAndPlayers_OrderTest() {
        Dealer dealer = new Dealer(List.of(
                new Card(Shape.SPADE, Number.NINE),
                new Card(Shape.HEART, Number.KING),
                new Card(Shape.DIAMOND, Number.TWO)
        ));

        Map<String, Long> expected = Map.of(
                "딜러", 20000L,
                "p1", 22500L,
                "p2", -20000L
        );

        assertThat(fromDealerAndPlayers(dealer, playerBetAmounts).nameEarningsMap()).isEqualTo(expected);
    }
}
