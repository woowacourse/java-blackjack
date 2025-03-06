package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.CardRank;
import blackjack.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드를 가진다.")
    @Test
    void test1() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);

        // when & then
        assertThatCode(() -> new Dealer(hand))
                .doesNotThrowAnyException();
    }

    @DisplayName("딜러의 모든 카드를 가져온다.")
    @Test
    void test2() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(dealer.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("딜러는 카드를 가져올 수 있다.")
    @Test
    void test3() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        dealer.takeCard(newCard);

        // then
        assertThat(dealer.getAllCards()).isEqualTo(expect);
    }

    @DisplayName("딜러는 첫번째 카드를 보여줄 수 있다.")
    @Test
    void test4() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = Hand.of(card1, card2);
        Dealer dealer = new Dealer(hand);

        // when
        Card revealedCard = dealer.revealFirstCard();

        // then
        assertThat(revealedCard).isEqualTo(card1);
    }
}
