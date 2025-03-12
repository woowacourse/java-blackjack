package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("딜러만 버스트일 경우 승리이다")
    @Test
    void test2() {
        //given
        Cards playerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        Player player = new Player("이름", playerCards);

        Cards dealerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND),
                        new Card(CardNumberType.JACK, CardType.HEART)
                )
        );

        //when
        GameResultStatus gameResultStatus = player.calculateResultStatus(dealerCards);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("플레이어만 버스트일 경우 패배이다")
    @Test
    void test3() {
        //given
        Cards playerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND),
                        new Card(CardNumberType.JACK, CardType.HEART)

                )
        );

        Player player = new Player("이름", playerCards);

        Cards dealerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        //when
        GameResultStatus gameResultStatus = player.calculateResultStatus(dealerCards);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어의 합이 더 높다면 승리이다")
    @Test
    void test4() {
        //given
        Cards playerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.ACE, CardType.DIAMOND)
                )
        );

        Player player = new Player("이름", playerCards);

        Cards dealerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        //when
        GameResultStatus gameResultStatus = player.calculateResultStatus(dealerCards);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.WIN);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 딜러의 합이 더 높다면 패배이다")
    @Test
    void test5() {
        //given
        Cards playerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        Player player = new Player("이름", playerCards);

        Cards dealerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.ACE, CardType.DIAMOND)
                )
        );

        //when
        GameResultStatus gameResultStatus = player.calculateResultStatus(dealerCards);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.LOSE);
    }

    @DisplayName("딜러와 플레이어가 모두 버스트가 아니고, 플레이어와 딜러의 합이 같다면 무승부이다")
    @Test
    void test6() {
        //given
        Cards playerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        Player player = new Player("이름", playerCards);

        Cards dealerCards = new Cards(
                List.of(
                        new Card(CardNumberType.JACK, CardType.CLOVER),
                        new Card(CardNumberType.JACK, CardType.DIAMOND)
                )
        );

        //when
        GameResultStatus gameResultStatus = player.calculateResultStatus(dealerCards);

        //then
        assertThat(gameResultStatus).isEqualTo(GameResultStatus.DRAW);
    }

}
