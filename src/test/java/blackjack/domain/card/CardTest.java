package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("Card 클래스는 카드 번호와 타입을 입력받으면 정상적으로 생성된다.")
    void create_card() {
        assertThatCode(() -> Card.of(CardNumber.ACE, CardType.SPADE)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드번호와 타입이 같으면 두 Card 객체는 동등하다.")
    void equal_card() {
        Card card1 = Card.of(CardNumber.ACE, CardType.SPADE);
        Card card2 = Card.of(CardNumber.ACE, CardType.SPADE);

        assertThat(card1).isSameAs(card2);
    }

    @Test
    @DisplayName("카드의 번호를 반환한다.")
    void get_card_number() {
        Card card = Card.of(CardNumber.TEN, CardType.SPADE);
        assertThat(card.getCardNumber().getNumber()).isEqualTo("10");
    }

    @Test
    @DisplayName("카드가 가진 점수를 반환한다")
    void get_value() {
        Card card = Card.of(CardNumber.TEN, CardType.SPADE);
        assertThat(card.getCardNumber().getValue()).isEqualTo(10);
    }

    @Test
    @DisplayName("카드의 종류를 반환한다")
    void get_type() {
        Card card = Card.of(CardNumber.TEN, CardType.SPADE);
        assertThat(card.getType().getType()).isEqualTo("스페이드");
    }
}
