package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {
    private static final List<Card> overDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.ACE),
            new Card(Shape.HEART, Symbol.KING));
    private static final List<Card> underDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.TWO),
            new Card(Shape.HEART, Symbol.EIGHT));

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("pobi");
    }

    @Test
    void 플레이어_카드의_점수가_21미만이면_드로우_할수있다() {
        underDrawPointCards.forEach(player::drawCard);
        assertThat(player.isDrawable())
                .isTrue();
    }

    @Test
    void 플레이어_카드_점수가_21이상이면_드로우_할수없다() {
        overDrawPointCards.forEach(player::drawCard);
        assertThat(player.isDrawable())
                .isFalse();
    }

}
