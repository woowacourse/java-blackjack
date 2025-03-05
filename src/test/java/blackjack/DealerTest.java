package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("딜러 테스트")
class DealerTest {

    @DisplayName("카드를 한장 뽑는다.")
    @Test
    void drawCardTest() {
        // given
        Queue<Card> cards = new LinkedList<>();
        cards.add(new Card(Suit.SPADES, CardValue.ACE));
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);

        // when
        Card card = dealer.drawCard();

        // then
        assertThat(card)
                .isEqualTo(new Card(Suit.SPADES, CardValue.ACE));
    }

    @DisplayName("카드를 한장 받는다.")
    @Test
    void receiveHandTest() {
        // given
        Queue<Card> cards = new LinkedList<>();
        Card card = new Card(Suit.SPADES, CardValue.ACE);
        Deck deck = new Deck(cards);
        Dealer dealer = new Dealer(deck);

        // when
        dealer.receiveHand(card);

        // then
        assertThat(dealer.getHand())
                .contains(new Card(Suit.SPADES, CardValue.ACE));
    }

    @DisplayName("가진 패의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Card spadeFive = new Card(Suit.SPADES, CardValue.FIVE);
        Deck deck = new Deck(new LinkedList<>());
        Dealer dealer = new Dealer(deck);
        dealer.receiveHand(spadeTen);
        dealer.receiveHand(spadeFive);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(15);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Deck deck = new Deck(new LinkedList<>());
        Dealer dealer = new Dealer(deck);
        dealer.receiveHand(spadeTen);
        dealer.receiveHand(spadeAce);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTwo = new Card(Suit.SPADES, CardValue.TWO);
        Card spadeNine = new Card(Suit.SPADES, CardValue.NINE);
        Deck deck = new Deck(new LinkedList<>());
        Dealer dealer = new Dealer(deck);
        dealer.receiveHand(spadeAce);
        dealer.receiveHand(spadeTwo);
        dealer.receiveHand(spadeNine);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(12);
    }
}
