package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    private static final List<Card> overDrawPointCards = List.of(
            Card.from(Shape.CLOVER, Denomination.ACE),
            Card.from(Shape.HEART, Denomination.KING));
    private static final List<Card> underDrawPointCards = List.of(
            Card.from(Shape.CLOVER, Denomination.TWO),
            Card.from(Shape.HEART, Denomination.EIGHT));

    @Test
    void 딜러의_카드가_16_이하의_점수라면_드로우_합니다() {
        Dealer dealer = new Dealer();
        underDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isTrue();
    }

    @Test
    void 딜러의_카드가_17_이상의_점수라면_스테이_합니다() {
        Dealer dealer = new Dealer();
        overDrawPointCards.forEach(dealer::drawCard);

        assertThat(dealer.isDrawable())
                .isFalse();
    }

}
