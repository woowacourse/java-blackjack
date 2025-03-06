package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @DisplayName("합이 16이하인지 판단한다")
    @ParameterizedTest
    @MethodSource("createDrawCase")
    void test1(List<Card> inputCard, boolean expected) {
        //given
        Cards cards = Cards.of(inputCard);
        Dealer dealer = Dealer.of(cards);
        //when
        boolean actual = dealer.hasToDraw();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream createDrawCase() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(CardNumber.A, CardShape.CLOVER), new Card(CardNumber.TWO, CardShape.CLOVER)),
                        true),
                Arguments.of(List.of(new Card(CardNumber.SEVEN, CardShape.CLOVER),
                        new Card(CardNumber.KING, CardShape.CLOVER)), false)
        );
    }

    @DisplayName("플레이어들의 승패를 결정한다")
    @Test
    void test() {
        //given
        Cards cards1 = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.THREE, CardShape.CLOVER)
                )
        );
        Cards cards2 = Cards.of(
                List.of(
                        new Card(CardNumber.TEN, CardShape.CLOVER),
                        new Card(CardNumber.TWO, CardShape.CLOVER)
                )
        );
        Cards cards3 = Cards.of(
                List.of(
                        new Card(CardNumber.A, CardShape.CLOVER),
                        new Card(CardNumber.FOUR, CardShape.CLOVER)
                )
        );
        Dealer dealer = Dealer.of(cards1);
        Player player1 = Player.from("플레이어", cards2);
        Player player2 = Player.from("플레이어2", cards3);
        Map<Player, GameResult> expected = Map.of(
                player1, GameResult.LOSE,
                player2, GameResult.WIN
        );

        //when
        Map<Player, GameResult> actual = dealer.getGameResult(List.of(player1, player2));

        //then
        assertThat(actual).isEqualTo(expected);
    }
}