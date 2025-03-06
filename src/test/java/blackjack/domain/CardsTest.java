package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardsTest {

    @Test
    @DisplayName("카드 포인트의 합을 구할 수 있다.")
    void canCalculateSum() {
        //given
        Cards cards = Cards.initialize();
        cards.add(List.of(new Card(CardShape.CLOVER, CardValue.QUEEN), new Card(CardShape.CLOVER, CardValue.TWO)));

        //when
        int actualSum = cards.calculateSum();

        //then
        assertThat(actualSum).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스가 소프트 밸류로 계산되어 포함된 합계가 버스트가 아닐 경우 에이스가 포함된다면 소프트 벨류로 계산한다.")
    void calculateSumWhenNotBust() {
        //given
        Cards cards = Cards.initialize();
        cards.add(List.of(new Card(CardShape.DIAMOND, CardValue.ACE), new Card(CardShape.CLOVER, CardValue.NINE)));

        //when
        int actualSum = cards.calculateSum();

        //then
        assertThat(actualSum).isEqualTo(GameRule.SOFT_ACE.getValue() + CardValue.NINE.getPoint());
    }

    @Test
    @DisplayName("에이스가 소프트 밸류로 계산되어 포함된 합계가 버스트인 경우 에이스가 포함된다면 1로 계산한다.")
    void calculateSumWhenBust() {
        //given
        Cards cards = Cards.initialize();
        cards.add(List.of(new Card(CardShape.DIAMOND, CardValue.ACE), new Card(CardShape.CLOVER, CardValue.QUEEN),
                new Card(CardShape.CLOVER, CardValue.QUEEN)));

        //when
        int actualSum = cards.calculateSum();

        //then
        assertThat(actualSum).isEqualTo(
                CardValue.ACE.getPoint() + CardValue.QUEEN.getPoint() + CardValue.QUEEN.getPoint());
    }
}