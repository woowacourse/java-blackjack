package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 1승 1패")
    @Test
    void judgeGameResultWithGamers_1() {
        // given
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        ));

        Gamer gamer2 = new Gamer(new Name("jason"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findByPlayer(dealer))
                .contains(ResultType.WIN, ResultType.LOSE);
        assertThat(gameResult.findByPlayer(gamer1))
                .containsExactly(ResultType.WIN);
        assertThat(gameResult.findByPlayer(gamer2))
                .containsExactly(ResultType.LOSE);
    }

    @DisplayName("딜러와 플레이어들의 게임 결과 테스트 - 딜러 2무")
    @Test
    void judgeGameResultWithGamers_2() {
        // given
        Gamer gamer1 = new Gamer(new Name("pobi"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        Gamer gamer2 = new Gamer(new Name("jason"), Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        Dealer dealer = new Dealer(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SIX, Shape.CLUBS)
        ));

        // when
        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer1, gamer2));

        // then
        assertThat(gameResult.findByPlayer(dealer))
                .containsExactly(ResultType.DRAW, ResultType.DRAW);
        assertThat(gameResult.findByPlayer(gamer1))
                .containsExactly(ResultType.DRAW);
        assertThat(gameResult.findByPlayer(gamer2))
                .containsExactly(ResultType.DRAW);
    }
}
