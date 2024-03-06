package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.strategy.SettedDecksGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("합계가 17 이상이 될 때 까지 카드를 뽑는다.")
    @Test
    void drawTest() {
        // given
        Dealer dealer = new Dealer();
        Card card1 = new Card(Symbol.DIAMOND, Rank.THREE);
        Card card2 = new Card(Symbol.HEART, Rank.THREE);
        Card card3 = new Card(Symbol.CLOVER, Rank.TEN);
        Card card4 = new Card(Symbol.SPADE, Rank.FOUR);
        Card card5 = new Card(Symbol.HEART, Rank.TWO);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        int expectedSize = 4;

        // when
        dealer.hit(decks);

        // then
        assertThat(dealer.getHand()).hasSize(expectedSize);
    }
}
