package player;

import card.Card;
import card.Deck;
import card.Number;
import card.Symbol;
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
                new Card(Symbol.HEART, Number.JACK),
                new Card(Symbol.DIAMOND, Number.SIX),
                new Card(Symbol.CLOVER, Number.THREE),
                new Card(Symbol.CLOVER, Number.FIVE)
        );
        Deck deck = new Deck(cards);
        // when
        dealer.drawCard(deck);
        // then
        assertThat(dealer.calculateScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("딜러는 플레이어의 점수를 받아 승패를 결정한다")
    void determineWinnerTest() {
        // given
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(new Card(Symbol.SPADE, Number.TEN), new Card(Symbol.HEART, Number.KING));
        Deck deck = new Deck(cards);
        dealer.drawCard(deck);
        // when, then
        assertAll(
                () -> assertThat(dealer.determineResult(19)).isTrue(),
                () -> assertThat(dealer.determineResult(21)).isFalse()
        );
    }
}
