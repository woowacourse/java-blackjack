package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("카드")
class CardTest {
    @Test
    @DisplayName("는 에이스일 수 있다.")
    void cardNumTest() {
        // given
        Card card = new Card(CardSuit.CLUB, CardScore.ACE);

        // when & then
        assertThat(card.isAce()).isTrue();
    }
}
