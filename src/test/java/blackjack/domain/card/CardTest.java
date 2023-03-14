package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CardTest {

    @DisplayName("카드를 생성한다.")
    @Test
    void should_createCard() {
        assertThatCode(() -> new Card(Symbol.SPADE, Number.ACE))
                .doesNotThrowAnyException();
    }

}
