package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void should_CreateCard() {
        assertThatCode(() -> new Card(Suit.SPADE, Denomination.ACE))
                .doesNotThrowAnyException();
    }

    @DisplayName("카드의 끗수가 에이스이면 참을 반환한다.")
    @Test
    void should_ReturnTrue_When_SuitIsAce() {
        assertThat(new Card(Suit.SPADE, Denomination.ACE).isACE())
                .isTrue();
    }

    @DisplayName("카드의 끗수가 에이스가 아니면 거짓을 반환한다.")
    @Test
    void should_ReturnFalse_When_SuitIsNotAce() {
        assertThat(new Card(Suit.SPADE, Denomination.TEN).isACE())
                .isFalse();
    }
}
