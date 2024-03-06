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
        Card card1 = new Card(Symbol.DIAMOND, Rank.THREE);
        Card card2 = new Card(Symbol.HEART, Rank.THREE);
        Card card3 = new Card(Symbol.CLOVER, Rank.TEN);
        Card card4 = new Card(Symbol.SPADE, Rank.FOUR);
        Card card5 = new Card(Symbol.HEART, Rank.TWO);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4, card5);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Dealer dealer = new Dealer(decks);

        int expectedSize = 4;

        // when
        dealer.hit(decks);

        // then
        assertThat(dealer.getHand()).hasSize(expectedSize);
    }

    @DisplayName("카드의 총 합계를 구한다.")
    @Test
    void calculateScoreTest() {
        // given
        Card card1 = new Card(Symbol.DIAMOND, Rank.THREE);
        Card card2 = new Card(Symbol.HEART, Rank.THREE);
        Card card3 = new Card(Symbol.CLOVER, Rank.TEN);
        Card card4 = new Card(Symbol.SPADE, Rank.FOUR);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3, card4);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Dealer dealer = new Dealer(decks);
        dealer.hit(decks);

        int expectedScore = 17;

        // when
        int result = dealer.calculateTotalScore();

        // then
        assertThat(result).isEqualTo(expectedScore);
    }

    @DisplayName("딜러가 버스트인지 확인한다.")
    @Test
    void isBustTest() {
        // given
        Card card1 = new Card(Symbol.HEART, Rank.NINE);
        Card card2 = new Card(Symbol.SPADE, Rank.QUEEN);
        Card card3 = new Card(Symbol.SPADE, Rank.THREE);

        SettedDecksGenerator settedDecksGenerator = new SettedDecksGenerator(card1, card2, card3);
        Decks decks = Decks.createByStrategy(settedDecksGenerator);

        Dealer dealer = new Dealer(decks);
        dealer.hit(decks);
        dealer.hit(decks);
        dealer.hit(decks);

        // when
        boolean bust = dealer.isBust();

        // then
        assertThat(bust).isTrue();
    }
}
