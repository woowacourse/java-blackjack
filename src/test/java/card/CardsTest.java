package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @DisplayName("해당 라운드에서 카드 점수의 최솟값이 21을 넘는지 확인한다.")
    @Test
    void countAceCard() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(cards.countRoundScore()).isEqualTo(2);
    }

    @DisplayName("Result로 낼 수 있는 가장 큰 결과 값을 return 하는지 확인한다.")
    @Test
    void getMaxCardScoreResult() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN),
                new Card(CardNumber.EIGHT, CardPattern.SPADE_PATTERN)));

        Assertions.assertThat(cards.countMaxScore()).isEqualTo(20);
    }
}