package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
class DealerTest {

    @Test
    void 딜러는_첫_공개때_한장의_카드만_보여준다() {
        //given
        Dealer dealer = new Dealer();
        Card twoSpade = new Card(Rank.TWO, Shape.SPADE);
        Card threeHeart = new Card(Rank.THREE, Shape.HEART);
        dealer.setUpCardDeck(twoSpade, threeHeart);

        //when
        var card = dealer.getOpenCard();

        //then
        assertThat(card).isEqualTo(twoSpade);
    }
}
