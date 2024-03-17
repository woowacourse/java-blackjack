package blackjack.view;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputSuitTest {

    @DisplayName("무늬를 입력받으면 출력용 한글 무늬를 문자열로 반환한다")
    @Test
    public void convertSuitToString() {
        assertAll(
                () -> assertThat(OutputSuit.convertSuitToString(Suit.HEART)).isEqualTo("하트"),
                () -> assertThat(OutputSuit.convertSuitToString(Suit.CLOVER)).isEqualTo("클로버"),
                () -> assertThat(OutputSuit.convertSuitToString(Suit.DIAMOND)).isEqualTo("다이아몬드"),
                () -> assertThat(OutputSuit.convertSuitToString(Suit.SPADE)).isEqualTo("스페이드")
        );
    }
}
