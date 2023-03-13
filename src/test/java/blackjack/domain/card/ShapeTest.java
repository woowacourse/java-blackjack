package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class ShapeTest {

    @Test
    void 심볼을_반환한다() {
        assertThat(Shape.DIAMOND.getSymbol()).isEqualTo("다이아몬드");
    }
}
