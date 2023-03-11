package blackjack.model.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드 생성 테스트 - 성공")
    void generate_card() {
        //given
        Card card = Card.of(CardSuit.DIAMOND, CardNumber.EIGHT);
        //then
        Assertions.assertThat(card).isSameAs(Card.of(CardSuit.DIAMOND, CardNumber.EIGHT));
    }
}
