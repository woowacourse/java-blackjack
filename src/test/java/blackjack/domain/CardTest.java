package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class CardTest {

    @Test
    void 카드의_점수를_반환한다() {
        final Card card = new Card(Rank.THREE, Shape.DIAMOND);

        assertThat(card.getScore()).isEqualTo(3);
    }

    @Test
    void 카드의_정보를_반환한다() {
        final Card card = new Card(Rank.THREE, Shape.DIAMOND);

        assertThat(card.getLetter()).isEqualTo("3다이아몬드");
    }
}
