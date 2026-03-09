package domain.card;

import domain.enums.Rank;
import domain.enums.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("카드 정상 생성 테스트")
    @Test
    void 카드_정상_생성_테스트() {
        //given
        Card card = new Card(Rank.ACE, Suit.DIAMOND);
        //when
        //then
        Assertions.assertThat(card.getSuitString()).isEqualTo("다이아");
        Assertions.assertThat(card.getRankString()).isEqualTo("A");
        Assertions.assertThat(card.getRank()).isEqualTo(Rank.ACE);
        Assertions.assertThat(card.getRankScore()).isEqualTo(1);
    }
}
