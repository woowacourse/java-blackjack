package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CardsTest {

    @DisplayName("카드 일급 컬렉션 생성 테스트")
    @Test
    void Should_Success_When_Create() {
        assertDoesNotThrow(() -> new Cards());
    }

    @DisplayName("Cards에 카드 한장을 추가할 수 있다.")
    @Test
    void Should_Success_When_AddCard() {
        Cards cards = new Cards();
        Card card = new Card(CardNumber.ACE, CardSymbol.HEARTS);
        cards.add(card);
        assertThat(cards.getCards()).containsExactly(card);
    }
}
