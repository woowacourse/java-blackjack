package blackjack.domain.carddeck;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardPatternTest {

    @Test
    @DisplayName("카드 문양별 출력형태 테스트")
    void testCardPatternPrintFormat() {
        assertThat(CardPattern.CLOVER.getPattern()).isEqualTo("클로버");
        assertThat(CardPattern.DIAMOND.getPattern()).isEqualTo("다이아몬드");
        assertThat(CardPattern.SPADE.getPattern()).isEqualTo("스페이드");
        assertThat(CardPattern.HEART.getPattern()).isEqualTo("하트");
    }

}
