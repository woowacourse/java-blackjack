package formatter;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.enums.Rank;
import domain.enums.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardFormatterTest {

    @DisplayName("카드를 출력용 한글 문자열로 변환한다.")
    @Test
    void 카드를_한글_문자열로_변환한다() {
        assertThat(CardFormatter.format(new Card(Rank.ACE, Suit.DIAMOND))).isEqualTo("A다이아");
        assertThat(CardFormatter.format(new Card(Rank.TEN, Suit.HEART))).isEqualTo("10하트");
        assertThat(CardFormatter.format(new Card(Rank.JACK, Suit.SPADE))).isEqualTo("J스페이드");
    }
}
