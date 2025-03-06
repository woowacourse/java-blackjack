package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    @DisplayName("두 장의 카드 숫자를 합쳐 21을 초과한다면 패배한다.")
    @Test
    void test1() {
        Judge judge = new Judge();

        Cards dealerCards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.EIGHT, CardShape.SPADE),
                new Card(CardNumber.NINE, CardShape.SPADE)
        )));

        Cards playerCards = new Cards(new HashSet<>(Set.of(
                new Card(CardNumber.KING, CardShape.SPADE),
                new Card(CardNumber.QUEEN, CardShape.SPADE),
                new Card(CardNumber.TWO, CardShape.SPADE)
        )));
        assertThat(judge.determineGameResult(dealerCards, playerCards)).isEqualTo(GameResult.LOSE);
    }
}
