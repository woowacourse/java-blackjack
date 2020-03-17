package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Figure;
import blackjack.domain.card.Type;
import blackjack.domain.participant.Name;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.result.ResultType.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTypeTest {
    private static final Cards bustCards = new Cards();
    private static final Cards blackjackCards = new Cards();
    private static final Cards scoreCards_21 = new Cards();
    private static final Cards scoreCards_20 = new Cards();
    private static final Cards scoreCards_10 = new Cards();

    @BeforeAll
    static void init() {
        bustCards.add(Card.of(Type.JACK, Figure.SPADE));
        bustCards.add(Card.of(Type.TEN, Figure.HEART));
        bustCards.add(Card.of(Type.THREE, Figure.HEART));

        blackjackCards.add(Card.of(Type.ACE, Figure.SPADE));
        blackjackCards.add(Card.of(Type.TEN, Figure.HEART));

        scoreCards_21.add(Card.of(Type.JACK, Figure.HEART));
        scoreCards_21.add(Card.of(Type.QUEEN, Figure.CLOVER));
        scoreCards_21.add(Card.of(Type.ACE, Figure.DIAMOND));

        scoreCards_20.add(Card.of(Type.JACK, Figure.HEART));
        scoreCards_20.add(Card.of(Type.QUEEN, Figure.CLOVER));

        scoreCards_10.add(Card.of(Type.THREE, Figure.HEART));
        scoreCards_10.add(Card.of(Type.SEVEN, Figure.SPADE));
    }

    @DisplayName("플레이어 BlackJack 확인: 플레이어가 BlackJack이고, 딜러가 BlackJack이 아닌 경우")
    @Test
    void test1() {
        Cards playerCards = blackjackCards;
        Cards dealerCards = scoreCards_10;
        ResultType actual = ResultType.findResult(playerCards, dealerCards);

        assertThat(actual).isEqualTo(BLACKJACK);
    }

    @DisplayName("플레이어 DRAW 확인1 : 둘 다 블랙잭인 경우")
    @Test
    void name() {
        Cards playerCards = blackjackCards;
        Cards dealerCards = blackjackCards;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);

        assertThat(actual).isEqualTo(DRAW);
    }

    @DisplayName("플레이어 DRAW 확인2 : 둘 다 21미만인 경우")
    @Test
    void name2() {
        Cards playerCards = scoreCards_20;
        Cards dealerCards = scoreCards_20;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);

        assertThat(actual).isEqualTo(DRAW);
    }

    @DisplayName("플레이어 LOSE 확인 1: 둘 다 Bust일 때")
    @Test
    void name3() {
        Cards playerCards = bustCards;
        Cards dealerCards = bustCards;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);

        assertThat(actual).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 LOSE 확인 2: Player만 Bust일 때")
    @Test
    void name4() {
        Cards playerCards = bustCards;
        Cards dealerCards = scoreCards_20;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);

        assertThat(actual).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 LOSE 확인 3: 둘 다 Bust가 아니고, 딜러 카드합이 21에 더 가까울 때")
    @Test
    void name5() {
        Cards playerCards = scoreCards_10;
        Cards dealerCards = scoreCards_20;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);
        assertThat(actual).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 LOSE 확인 4: 딜러가 블랙잭이고, 플레이어 카드 합이 21이지만 블랙잭이 아닐 때")
    @Test
    void name6() {
        Cards playerCards = scoreCards_21;
        Cards dealerCards = blackjackCards;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);
        assertThat(actual).isEqualTo(LOSE);
    }

    @DisplayName("플레이어 WIN 확인1: 플레이어가 21이지만 블랙잭이 아니고, 딜러가 21보다 작을 때")
    @Test
    void name7() {
        Cards playerCards = scoreCards_21;
        Cards dealerCards = scoreCards_10;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);
        assertThat(actual).isEqualTo(WIN);
    }

    @DisplayName("플레이어 WIN 확인2: 플레이어가 버스트가 아니고, 딜러가 버스트 일 때")
    @Test
    void name8() {
        Cards playerCards = scoreCards_10;
        Cards dealerCards = bustCards;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);
        assertThat(actual).isEqualTo(WIN);
    }

    @DisplayName("플레이어 WIN 확인3: 플레이어와 딜러 모두 버스트가 아니고, 플레이어의 카드 합이 더 클 때")
    @Test
    void name9() {
        Cards playerCards = scoreCards_20;
        Cards dealerCards = scoreCards_10;

        ResultType actual = ResultType.findResult(playerCards, dealerCards);
        assertThat(actual).isEqualTo(WIN);
    }

    @DisplayName("승/패/무 reverse 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, LOSE", "DRAW, DRAW", "LOSE, WIN"})
    void test2(ResultType resultType, ResultType expectedType) {
        ResultType actualType = resultType.reverse();
        assertThat(actualType).isEqualTo(expectedType);
    }

    @DisplayName("전달받은 플레이어 결과 리스트에서 해당 ResultType 개수 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, 2", "DRAW, 2", "LOSE, 1"})
    void test3(ResultType resultType, int expectedCount) {
        List<PlayerResult> playersResult = Arrays.asList(
                new PlayerResult(new Name("포비"), WIN),
                new PlayerResult(new Name("쪼밀리"), DRAW),
                new PlayerResult(new Name("타미"), LOSE),
                new PlayerResult(new Name("워니"), WIN),
                new PlayerResult(new Name("CU"), DRAW)
        );

        int actualCount = resultType.countSameResultType(playersResult);

        assertThat(actualCount).isEqualTo(expectedCount);
    }
}
