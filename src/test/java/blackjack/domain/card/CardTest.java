package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Card 클래스는 카드 번호와 타입을 입력받으면 정상적으로 생성된다.")
    void create_card() {
        assertThatCode(() -> Card.of(CardNumber.ACE, Suit.SPADE)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드의 번호를 반환한다.")
    void get_card_number() {
        Card card = Card.of(CardNumber.TEN, Suit.SPADE);
        assertThat(card.getCardNumber()).isEqualTo(CardNumber.TEN);
    }

    @Test
    @DisplayName("카드는 캐싱하여 사용한다.")
    void use_cache_card() {
        Card card1 = Card.of(CardNumber.TEN, Suit.SPADE);
        Card card2 = Card.of(CardNumber.TEN, Suit.SPADE);
        assertThat(card1 == card2).isTrue();
    }

    @Test
    @DisplayName("카드가 가진 점수를 반환한다")
    void get_value() {
        Card card = Card.of(CardNumber.TEN, Suit.SPADE);
        assertThat(card.getCardNumber()).isEqualTo(CardNumber.TEN);
    }

    @Test
    @DisplayName("카드의 종류를 반환한다")
    void get_Suit() {
        Card card = Card.of(CardNumber.TEN, Suit.SPADE);
        assertThat(card.getSuit()).isEqualTo(Suit.SPADE);
    }

    @Test
    @DisplayName("equals, hashCode, toString 테스트")
    void equals() {
        CardNumber ace = CardNumber.ACE;
        CardNumber two = CardNumber.TWO;
        Suit heart = Suit.HEART;
        Card o1 = Card.of(ace, heart);
        Card o2 = Card.of(ace, heart);
        Card o3 = Card.of(two, heart);
        Object o = new Object();

        assertThat(o1.equals(o2)).isTrue();
        assertThat(o1.equals(o3)).isFalse();
        assertThat(o1.equals(o1)).isTrue();
        assertThat(o1.equals(o)).isFalse();
        assertThat(o1.hashCode() == o2.hashCode()).isTrue();
        assertThat(o1.toString()).isEqualTo(o2.toString());
    }
}
