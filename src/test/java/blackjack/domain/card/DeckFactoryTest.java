package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckFactoryTest {

    @Test
    void 덱을_생성한다() {
        assertThatCode(() -> DeckFactory.createWithCount(1))
                .doesNotThrowAnyException();
    }
}
