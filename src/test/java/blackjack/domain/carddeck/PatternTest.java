package blackjack.domain.carddeck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PatternTest {

    @Test
    @DisplayName("카드 문양별 출력형태 테스트")
    void testCardPatternPrintFormat() {
        assertThat(Pattern.CLOVER.getPattern()).isEqualTo("클로버");
        assertThat(Pattern.DIAMOND.getPattern()).isEqualTo("다이아몬드");
        assertThat(Pattern.SPADE.getPattern()).isEqualTo("스페이드");
        assertThat(Pattern.HEART.getPattern()).isEqualTo("하트");
    }

}
