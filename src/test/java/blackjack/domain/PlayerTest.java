package blackjack.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerTest {
    private final CardPocket cardPocketUnderDrawPoint = generateUnderDrawPoint();
    private final CardPocket cardPocketOverDrawPoint = generateOverDrawPoint();


    @Test
    void 플레이어_카드의_점수가_21미만이면_드로우_할수있다() {
        final Player player = new Player(cardPocketUnderDrawPoint);
        assertThat(player.isDrawable())
                .isTrue();
    }

    @Test
    void 플레이어_카드_점수가_21이상이면_드로우_할수없다() {
        final Player player = new Player(cardPocketOverDrawPoint);
        assertThat(player.isDrawable())
                .isFalse();
    }

    private CardPocket generateUnderDrawPoint() {
        final List<Card> cardsUnderDrawPoint = List.of(
                new Card(Shape.CLOVER, Symbol.TWO),
                new Card(Shape.HEART, Symbol.EIGHT));
        return new CardPocket(cardsUnderDrawPoint);
    }

    private CardPocket generateOverDrawPoint() {
        final List<Card> cardsOverDrawPoint = List.of(
                new Card(Shape.CLOVER, Symbol.ACE),
                new Card(Shape.HEART, Symbol.KING));
        return new CardPocket(cardsOverDrawPoint);
    }
}
