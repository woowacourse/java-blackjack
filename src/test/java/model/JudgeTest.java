package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.Cards;
import model.gameresult.GameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    @DisplayName("플레이어가 21을 초과하면 패배한다.")
    @Test
    void playerBusts_ShouldLose() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.EIGHT, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        Cards playerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));
        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 21을 초과하면 플레이어가 승리한다.")
    @Test
    void dealerBusts_PlayerShouldWin() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));
        Cards playerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.EIGHT, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어의 카드 합이 딜러보다 높으면 승리한다.")
    @Test
    void playerHasHigherScore_ShouldWin() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.SEVEN, CardShape.SPADE)
        )));
        Cards playerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));

        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러의 카드 합이 플레이어보다 높으면 패배한다.")
    @Test
    void dealerHasHigherScore_ShouldLose() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));
        Cards playerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.SEVEN, CardShape.SPADE)
        )));

        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("플레이어와 딜러의 카드 합이 같으면 무승부이다.")
    @Test
    void sameScore_ShouldBeDraw() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));
        Cards playerCards = new Cards(new ArrayList<>(List.of(
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.EIGHT, CardShape.SPADE)
        )));

        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.DRAW);
    }
}