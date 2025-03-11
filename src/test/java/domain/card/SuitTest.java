package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("모양 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SuitTest {

    @Test
    void 모든_모양을_반환한다() {
        List<Suit> expected = List.of(Suit.DIAMOND, Suit.SPADE, Suit.CLOVER, Suit.HEART);

        assertThat(Suit.getAllSuits()).isEqualTo(expected);
    }
}
