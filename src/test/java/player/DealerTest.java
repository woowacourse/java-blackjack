package player;

import card.Card;
import card.Deck;
import card.Number;
import card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class DealerTest {

    @Test
    @DisplayName("딜러는 17 이상이 될 때까지 카드를 계속 뽑는다.")
    void dealerDrawTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.SIX),
                new Card(Shape.CLOVER, Number.THREE),
                new Card(Shape.CLOVER, Number.FIVE)
        );
        Deck deck = new Deck(cards);
        // when
        dealer.drawCard(deck);
        boolean isDrawable1 = dealer.hasDrawableScore();

        dealer.drawCard(deck);
        boolean isDrawable2 = dealer.hasDrawableScore();

        dealer.drawCard(deck);
        boolean isDrawable3 = dealer.hasDrawableScore();
        // then
        assertAll(
                () -> assertThat(isDrawable1).isTrue(),
                () -> assertThat(isDrawable2).isTrue(),
                () -> assertThat(isDrawable3).isFalse()
        );
    }
}
