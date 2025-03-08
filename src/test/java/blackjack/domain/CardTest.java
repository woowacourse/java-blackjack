package blackjack.domain;

import blackjack.common.CardRank;
import blackjack.common.CardSuit;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @DisplayName("객체 값 검증 테스트")
    @Test
    void test1() {
        // given
        Card card = new Card(CardSuit.HEART, CardRank.EIGHT);

        // when & then
        assertThat(card.getRankValues()).contains(8);
    }

    @DisplayName("ACE의 경우에는 1과 11의 값을 가질 수 있다.")
    @Test
    void test3() {
        // given
        Card ace = new Card(CardSuit.CLUB, CardRank.ACE);

        // when & then
        assertThat(ace.getRankValues()).containsExactlyInAnyOrder(1, 11);
    }

    @DisplayName("값과 타입으로 동일한 객체인지 확인한다")
    @Test
    void test2() {
        // given
        Card card = new Card(CardSuit.HEART, CardRank.TWO);
        Card comparedCard = new Card(CardSuit.HEART, CardRank.TWO);

        // when
        boolean actual = card.equals(comparedCard);

        // then
        assertThat(actual).isTrue();
    }
}
