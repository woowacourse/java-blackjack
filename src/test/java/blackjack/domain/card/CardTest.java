package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 값과 카드 문양을 통해 카드를 생성한다.")
    void create_with_cardValue_and_cardSymbol() {
        Assertions.assertThatCode(() -> new Card(CardValue.TWO, CardSymbol.DIAMOND))
                  .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드가 에이스이면 참을 반환한다.")
    void true_if_card_is_Ace() {
        final var sut = new Card(CardValue.ACE, CardSymbol.DIAMOND);

        final var result = sut.isAce();

        org.junit.jupiter.api.Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("카드가 에이스가 아니면 거짓을 반환한다.")
    void false_if_card_is_not_Ace() {
        final var sut = new Card(CardValue.TWO, CardSymbol.DIAMOND);

        final var result = sut.isAce();

        org.junit.jupiter.api.Assertions.assertFalse(result);
    }
}
