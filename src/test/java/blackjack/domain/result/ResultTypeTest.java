package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {
    private static Cards blackJackCards;
    private static Cards nonBlackJack21Cards;
    private static Cards normalCards_point8;
    private static Cards normalCards_point20;
    private static Cards bustCards;

    @BeforeAll
    static void init() {
        Card aceHeart = new Card(CardNumber.ACE, CardFigure.HEART);
        Card jackDiamond = new Card(CardNumber.JACK, CardFigure.DIAMOND);
        Card jackHeart = new Card(CardNumber.JACK, CardFigure.HEART);
        Card queenDiamond = new Card(CardNumber.QUEEN, CardFigure.DIAMOND);
        Card twoHeart = new Card(CardNumber.TWO, CardFigure.HEART);
        Card threeDiamond = new Card(CardNumber.THREE, CardFigure.DIAMOND);
        Card fiveDiamond = new Card(CardNumber.FIVE, CardFigure.DIAMOND);
        Card eightHeart = new Card(CardNumber.EIGHT, CardFigure.HEART);

        blackJackCards = new Cards (Arrays.asList(aceHeart, jackDiamond));
        nonBlackJack21Cards = new Cards(Arrays.asList(threeDiamond, eightHeart, jackHeart));
        normalCards_point8 = new Cards(Arrays.asList(twoHeart, fiveDiamond));
        normalCards_point20 = new Cards(Arrays.asList(jackHeart, queenDiamond));
        bustCards = new Cards(Arrays.asList(queenDiamond, jackDiamond, jackHeart));
    }

    @DisplayName("플레이어 BlackJack 확인: 플레이어가 BlackJack이고, 딜러가 BlackJack이 아닌 경우")
    @Test
    void test1() {
        ResultType resultType = ResultType.computeResult(blackJackCards, normalCards_point8);
        assertThat(resultType).isEqualTo(ResultType.BLACK_JACK);
    }

    @DisplayName("플레이어 승 - 플레이어/딜러 모두 21미만")
    @Test
    void computeResult_playerWin() {
        ResultType resultType = ResultType.computeResult(normalCards_point20, normalCards_point8);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 승 - 플레이어 21초과X, 딜러 21초과")
    @Test
    void computeResult_playerWin_dealerExceed_21() {
        ResultType resultType = ResultType.computeResult(normalCards_point20, bustCards);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 승 - 플레이어 BlackJack이 아닌 21, 딜러 21미만")
    @Test
    void computeResult_playerWin_notBlackJack() {
        ResultType resultType = ResultType.computeResult(nonBlackJack21Cards, normalCards_point20);
        assertThat(resultType).isEqualTo(ResultType.WIN);
    }

    @DisplayName("플레이어 패 - 플레이어/딜러 모두 21미만")
    @Test
    void computeResult_playerLose() {
        ResultType resultType = ResultType.computeResult(normalCards_point8, normalCards_point20);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 무승부 - 같은 점수")
    @Test
    void computeResult_playerDraw() {
        ResultType resultType = ResultType.computeResult(normalCards_point20, normalCards_point20);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어 무승부 - 둘 다 블랙잭")
    @Test
    void computeResult_playerBlackJackDraw() {
        ResultType resultType = ResultType.computeResult(blackJackCards, blackJackCards);
        assertThat(resultType).isEqualTo(ResultType.DRAW);
    }

    @DisplayName("플레이어 패 - 플레이어 21초과, 딜러 21초과X")
    @Test
    void computeResult_playerLose_playerExceed_21() {
        ResultType resultType = ResultType.computeResult(bustCards, normalCards_point20);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 패 - 플레이어 21초과, 딜러 21초과")
    @Test
    void computeResult_playerLose_playerAndDealerExceed_21() {
        ResultType resultType = ResultType.computeResult(bustCards, bustCards);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }

    @DisplayName("플레이어 패 - 플레이어 BlackJack이 아닌 21, 딜러 BlackJack")
    @Test
    void computeResult_playerLose_dealerBlackJack() {
        ResultType resultType = ResultType.computeResult(nonBlackJack21Cards, blackJackCards);
        assertThat(resultType).isEqualTo(ResultType.LOSE);
    }


    @DisplayName("승/패/무승부의 적절한 반대 값을 찾는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN,LOSE", "DRAW,DRAW", "LOSE,WIN"})
    void reverseTest(ResultType input, ResultType expected) {
        ResultType actual = ResultType.opposite(input);
        assertThat(actual).isEqualTo(expected);
    }
}
