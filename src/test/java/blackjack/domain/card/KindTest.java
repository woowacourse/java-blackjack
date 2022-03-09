package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KindTest {

    @DisplayName("카드 문양 테스트")
    @Test
    void constructorTest() {
        assertThat(Kind.SPADE.getSymbol()).isEqualTo("스페이드");
        assertThat(Kind.DIAMOND.getSymbol()).isEqualTo("다이아몬드");
        assertThat(Kind.CLOVER.getSymbol()).isEqualTo("클로버");
        assertThat(Kind.HEART.getSymbol()).isEqualTo("하트");
    }
}