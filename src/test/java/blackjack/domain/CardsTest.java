package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
}