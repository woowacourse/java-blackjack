package card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    private static final int ADDITIONAL_ACE_CARD_SCORE = CardNumber.ACE.getAdditionalScore();

    @DisplayName("해당 라운드에서 카드 점수의 최솟값이 21을 넘는지 확인한다.")
    @Test
    void countAceCard() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN)));

        Assertions.assertThat(cards.countAceCard()).isEqualTo(2);
    }

    @DisplayName("Result로 낼 수 있는 가장 큰 결과 값을 return 하는지 확인한다.")
    @Test
    void getMaxCardScoreResult() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.ACE, CardPattern.DIA_PATTERN),
                new Card(CardNumber.EIGHT, CardPattern.SPADE_PATTERN)));

        Assertions.assertThat(cards.countMaxScore()).isEqualTo(20);
    }

    @DisplayName("추가 ACE 점수가 더해질 때 Bust 일 경우 ACE가 1인 경우를 계산한 값을 return 한다.")
    @Test
    void notConvertToAdditionalAceCardScore() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.KING, CardPattern.DIA_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.DIA_PATTERN)));

        int matchScore = cards.countMatchScore();

        Assertions.assertThat(cards.countMaxScore()).isEqualTo(matchScore);
    }

    @DisplayName("추가 ACE 점수가 더해질 때 Bust가 아닐 경우 ACE가 11인 경우를 계산한 값을 return 한다.")
    @Test
    void convertToAdditionalAceCardScore() {
        Cards cards = new Cards(List.of(new Card(CardNumber.ACE, CardPattern.CLOVER_PATTERN),
                new Card(CardNumber.FOUR, CardPattern.DIA_PATTERN)));

        int matchScore = cards.countMatchScore();

        Assertions.assertThat(cards.countMaxScore()).isEqualTo(matchScore + ADDITIONAL_ACE_CARD_SCORE);
    }
}