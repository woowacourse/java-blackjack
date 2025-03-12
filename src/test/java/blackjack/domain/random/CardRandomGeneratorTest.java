package blackjack.domain.random;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardRandomGeneratorTest {

    @DisplayName("카드를 랜덤으로 한장 뽑는다.")
    @Test
    void pickOneRandomCard() {
        // given
        final CardGenerator cardGenerator = new CardRandomGenerator();

        // when
        final Card card = cardGenerator.pickCard();

        // then
        assertThat(card).isNotNull();
    }
}
