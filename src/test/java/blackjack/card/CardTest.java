package blackjack.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CardTest {

    @Test
    @DisplayName("카드가 에이스인지 확인할 수 있다")
    void canCheckIsAceCard() {
        // given
        Card aceCard = CardFixture.createCard(CardSuit.SPADE, CardRank.ACE);
        Card normalCard = CardFixture.createCard(CardSuit.HEART, CardRank.EIGHT);

        // when
        // then
        assertAll(() -> {
                    assertThat(aceCard.isAce()).isTrue();
                    assertThat(aceCard.isNotAce()).isFalse();

                    assertThat(normalCard.isAce()).isFalse();
                    assertThat(normalCard.isNotAce()).isTrue();
                }
        );
    }
}
