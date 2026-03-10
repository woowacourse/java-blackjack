package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("딜러 카드 합이 17이고 플레이어 카드 합이 21이면 플레이어가 승리이다")
    void winTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.NINE),
                new Card(Figure.CLOVER, Number.TWO));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러의 카드 합이 21이고 플레이어 카드 합이 17이면 패배이다")
    void loseTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.NINE),
                new Card(Figure.CLOVER, Number.TWO));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 카드 합이 버스트가 아니고 같다면 무승부이다")
    void drawTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러가 버스트이고, 플레이어 카드 합이 17이면 승리이다")
    void isWinWhenDealerIsBurstTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.JACK),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("딜러 카드 합이 17이고 플레이어가 버스트라면 패배이다")
    void isLoseWhenPlayerIsBurstTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.SEVEN),
                new Card(Figure.CLOVER, Number.KING));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.JACK),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 버스트인 경우 패 판정")
    void isLoseWhenBothIsBurstTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.JACK),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.JACK),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어 카드 합이 21점이면 패배한다")
    void loseWhenOnlyDealerIsBlackjackTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.ACE));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.NINE),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("딜러와 플레이어 모두 블랙잭이면 무승부이다")
    void drawWhenDealerAndPlayerAreBlackjackTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.ACE));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.ACE));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("딜러가 21점이고 플레이어 블랙잭이면 블랙잭 승리이다")
    void winWhenOnlyPlayerIsBlackjackTest() {
        // given
        Dealer dealer = createDealerWithCars(
                new Card(Figure.CLOVER, Number.NINE),
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.TWO));
        Player player = createPlayerWithCars(
                new Card(Figure.CLOVER, Number.KING),
                new Card(Figure.CLOVER, Number.ACE));

        // when & then
        assertThat(GameResult.matchResult(player, dealer))
                .isEqualTo(GameResult.BLACKJACK);
    }

    private Dealer createDealerWithCars(Card... cards) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.receiveCard(card);
        }
        return dealer;
    }

    private Player createPlayerWithCars(Card... cards) {
        Player player = new Player("usher");
        for (Card card : cards) {
            player.receiveCard(card);
        }
        return player;
    }
}
