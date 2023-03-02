package blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {
    private static List<Card> cards;

    private CardPocket cardPocket;

    @BeforeEach
    void setCardPocket() {
        cards = List.of(new Card(Shape.CLOVER, Symbol.TWO), new Card(Shape.HEART, Symbol.EIGHT));
        cardPocket = new CardPocket(cards);
    }

    @Test
    void 딜러의_카드가_16_이하의_점수라면_드로우_합니다() {
        final Dealer dealer = new Dealer(cardPocket);

        assertThat(dealer.isDrawable())
                .isTrue();
    }

    @Test
    void 딜러의_카드가_17_이상의_점수라면_스테이_합니다() {
        cardPocket.addCard(new Card(Shape.CLOVER, Symbol.TEN));

        final Dealer dealer = new Dealer(cardPocket);

        assertThat(dealer.isDrawable())
                .isFalse();
    }

}
