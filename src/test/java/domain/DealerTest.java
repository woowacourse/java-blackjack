package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    Dealer underThreshold = Dealer.from(new Hand(new ArrayList<>(List.of(
            Card.of(CardNumber.EIGHT, CardShape.CLOVER),
            Card.of(CardNumber.SEVEN, CardShape.CLOVER)))));

    Dealer overThreshold = Dealer.from(new Hand(new ArrayList<>(List.of(
            Card.of(CardNumber.EIGHT, CardShape.CLOVER),
            Card.of(CardNumber.J, CardShape.CLOVER)))));

    @Test
    @DisplayName("딜러 카드 16이하 여부 확인 테스트")
    void 딜러_16이하_여부_확인() {
        assertThat(underThreshold.checkThreshold()).isTrue();
        assertThat(overThreshold.checkThreshold()).isFalse();
    }

    @Test
    @DisplayName("카드 추가 확인 테스트")
    void 카드_추가_확인() {
        underThreshold.addHandCard(Card.of(CardNumber.FIVE, CardShape.DIAMOND));
        overThreshold.addHandCard(Card.of(CardNumber.FOUR, CardShape.DIAMOND));
        assertThat(underThreshold.getHandCards().size()).isEqualTo(3);
        assertThat(overThreshold.getHandCards().size()).isEqualTo(3);
    }
}