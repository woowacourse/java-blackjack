package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import domain.enums.Rank;
import domain.enums.Suit;
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
        assertThat(card.suitString()).isEqualTo("다이아");
        assertThat(card.rankString()).isEqualTo("A");
        assertThat(card.rank()).isEqualTo(Rank.ACE);
        assertThat(card.rankScore()).isEqualTo(1);
    }

    @DisplayName("hashCode(), equals()를 구현한 카드 객체의 동등성 비교 테스트")
    @Test
    void 카드_객체_동등성_비교_테스트() {
        //given
        Card firstCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card secondCard = new Card(Rank.ACE, Suit.DIAMOND);
        Card thirdCard = new Card(Rank.ACE, Suit.HEART);

        //when
        //then
        assertThat(firstCard).isEqualTo(secondCard);
        assertThat(firstCard).isNotEqualTo(thirdCard);
    }
}
