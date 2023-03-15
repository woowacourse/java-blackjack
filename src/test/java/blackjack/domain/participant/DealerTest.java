package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Symbol;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    private static final List<Card> overDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.ACE),
            new Card(Shape.HEART, Symbol.KING));
    private static final List<Card> underDrawPointCards = List.of(
            new Card(Shape.CLOVER, Symbol.TWO),
            new Card(Shape.HEART, Symbol.EIGHT));

    @Test
    void 딜러의_카드가_16_이하의_점수라면_드로우_합니다() {
        final Dealer dealer = new Dealer();
        underDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isTrue();
    }

    @Test
    void 딜러의_카드가_17_이상의_점수라면_스테이_합니다() {
        final Dealer dealer = new Dealer();
        overDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isFalse();
    }
}
