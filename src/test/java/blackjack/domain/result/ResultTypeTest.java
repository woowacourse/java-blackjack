package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTypeTest {

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 승, 플레이어 패")
    @Test
    void judgeGameResultTest_1() {
        // given
        Gamer gamer = new Gamer("pobi", Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.NINE, Shape.CLUBS)
        ));

        // when
        Map<Player, ResultType> result = ResultType.judgeGameResult(gamer, dealer);

        // then
        assertThat(result.get(gamer)).isSameAs(ResultType.LOSE);
        assertThat(result.get(dealer)).isSameAs(ResultType.WIN);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 패, 플레이어 승")
    @Test
    void judgeGameResultTest_2() {
        // given
        Gamer gamer = new Gamer("pobi", Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.KING, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.NINE, Shape.CLUBS)
        ));

        // when
        Map<Player, ResultType> result = ResultType.judgeGameResult(gamer, dealer);

        // then
        assertThat(result.get(gamer)).isSameAs(ResultType.WIN);
        assertThat(result.get(dealer)).isSameAs(ResultType.LOSE);
    }

    @DisplayName("두 플레이어의 게임 결과 테스트 - 딜러 무, 플레이어 무")
    @Test
    void judgeGameResultTest_3() {
        // given
        Gamer gamer = new Gamer("pobi", Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.KING, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.KING, Shape.CLUBS)
        ));

        // when
        Map<Player, ResultType> result = ResultType.judgeGameResult(gamer, dealer);

        // then
        assertThat(result.get(gamer)).isSameAs(ResultType.DRAW);
        assertThat(result.get(dealer)).isSameAs(ResultType.DRAW);
    }
}