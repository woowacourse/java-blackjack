package blackjack.domain.carddeck;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {

    @Test
    @DisplayName("카드생성 테스트")
    void testCreateCard() {
        Card card = new Card(Pattern.CLOVER, Number.ACE);

        assertThat(card).isEqualTo(new Card(Pattern.CLOVER, Number.ACE));
        assertThat(card).isNotEqualTo(new Card(Pattern.HEART, Number.ACE));
        assertThat(card).isNotEqualTo(new Card(Pattern.CLOVER, Number.KING));
    }

    @Test
    @DisplayName("자신이 ACE 카드인지 확인한다.")
    void testIsAceCard() {
        Card card1 = new Card(Pattern.CLOVER, Number.ACE);
        Card card2 = new Card(Pattern.CLOVER, Number.TWO);

        assertThat(card1.isAce()).isTrue();
        assertThat(card2.isAce()).isFalse();
    }

    @Test
    @DisplayName("카드 번호의 이름 반환한다.")
    void getCardNumberNameTest() {
        Card card = new Card(Pattern.CLOVER, Number.ACE);

        String cardNumberName = card.getNumberName();

        assertThat(cardNumberName).isEqualTo("A");
    }

    @Test
    @DisplayName("카드 번호와 문양이 같으면 같은 객체로 인식한다.")
    void cardEqualsTest() {
        Card card = new Card(Pattern.CLOVER, Number.ACE);

        assertThat(card.equals(new Card(Pattern.CLOVER, Number.ACE))).isTrue();
        assertThat(card.equals(new Card(Pattern.SPADE, Number.ACE))).isFalse();
        assertThat(card.equals(new Card(Pattern.CLOVER, Number.NINE))).isFalse();
    }
}
