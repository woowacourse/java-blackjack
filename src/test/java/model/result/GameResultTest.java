package model.result;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.cards.Cards;
import model.cards.DealerCards;
import model.cards.PlayerCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이라면 비긴다.")
    @Test
    void test6() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.SPADE),
                new Card(CardNumber.ACE_ELEVEN, CardShape.SPADE)
        )));

        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.ACE_ELEVEN, CardShape.HEART)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 특별 승리한다.")
    @Test
    void test7() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));

        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.ACE_ELEVEN, CardShape.HEART)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.BLACKJACK_WIN);
    }

    @DisplayName("플레이어가 블랙잭이 아니고 딜러가 블랙잭이라면 패배한다.")
    @Test
    void test8() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.TEN, CardShape.SPADE),
                new Card(CardNumber.ACE_ELEVEN, CardShape.SPADE)
        )));

        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.HEART),
                new Card(CardNumber.TWO, CardShape.HEART)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드 숫자를 합쳐 21을 초과한다면 패배한다.")
    @Test
    void test1() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.EIGHT, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));
        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합이 21을 초과한다면 이긴다.")
    @Test
    void test2() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));
        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.EIGHT, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합보다 크다면 이긴다.")
    @Test
    void test3() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.SEVEN, CardShape.SPADE)
        )));
        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합과 작다면 패배한다")
    @Test
    void test4() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));
        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.SEVEN, CardShape.SPADE)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("카드 숫자를 합쳐 21을 초과하지 않으면서 딜러의 카드 숫자의 합과 같다면 비긴다.")
    @Test
    void test5() {
        Cards dealerCards = new DealerCards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));
        Cards playerCards = new PlayerCards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));

        assertThat(GameResult.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.DRAW);
    }

    @DisplayName("입력과 반대되는 GameResult를 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "WIN, LOSE",
            "LOSE, WIN",
            "DRAW, DRAW"
    })
    void oppositeGameResultTest(final GameResult input, final GameResult expected) {
        assertThat(GameResult.getOppositeResult(input)).isEqualTo(expected);
    }
}
