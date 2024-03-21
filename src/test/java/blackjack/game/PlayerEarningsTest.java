package blackjack.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.player.Dealer;
import blackjack.player.Hand;
import blackjack.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerEarningsTest {

    private final Hand dealerHand = new Hand(List.of(
            new Card(Shape.DIAMOND, Rank.TEN),
            new Card(Shape.HEART, Rank.TEN
            )));
    private final Dealer dealer = new Dealer(dealerHand);
    private final Hand playerHand = new Hand(List.of(
            new Card(Shape.CLOVER, Rank.TEN),
            new Card(Shape.DIAMOND, Rank.FIVE
            )));
    private final Player player = new Player("atto", playerHand);
    private final Money money = new Money(10000);
    private PlayerEarnings playerEarnings;

    @BeforeEach
    void beforeEach() {
        Map<Player, Money> playerBettingResult = new HashMap<>();
        playerBettingResult.put(player, money);
        playerEarnings = new PlayerEarnings(dealer, playerBettingResult);
    }

    @Test
    @DisplayName("플레이어로 배팅 결과를 올바르게 가져온다.")
    void getResultByNameTest() {
        assertThat(playerEarnings.getResultOf(player)).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 존재하지 않는 경우 예외를 발생시킨다.")
    void nameNotFoundTest() {
        assertThatThrownBy(() -> playerEarnings.getResultOf(new Player("pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어입니다.");
    }

    @Test
    @DisplayName("딜러의 수익을 구한다.")
    void getDealerResultTest() {
        assertThat(playerEarnings.getDealerResult()).isEqualTo(10000);
    }
}
