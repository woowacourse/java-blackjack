package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void 딜러는_카드를_가질_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);

        // when & then
        assertThatCode(() -> new Dealer(hand))
                .doesNotThrowAnyException();
    }

    @Test
    void 딜러의_모든_카드를_가져올_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2);

        // when & then
        assertThat(dealer.getCards()).isEqualTo(expect);
    }

    @Test
    void 딜러는_카드를_더_가져올_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);
        Card newCard = new Card(CardSuit.SPADE, CardRank.KING);
        Dealer dealer = new Dealer(hand);

        List<Card> expect = List.of(card1, card2, newCard);

        // when
        dealer.takeCard(newCard);

        // then
        assertThat(dealer.getCards()).isEqualTo(expect);
    }

    @Test
    void 딜러는_첫번째_카드를_공개할_수_있다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.FIVE);
        Hand hand = new Hand();
        hand.takeCard(card1);
        hand.takeCard(card2);
        Dealer dealer = new Dealer(hand);

        // when
        List<Card> cards = dealer.getCards();
        Card revealedCard = cards.getFirst();

        // then
        assertThat(revealedCard).isEqualTo(card1);
    }

    @Test
    void 딜러는_모든_카드_합이_16이하일_때만_카드를_더_뽑을_수_있다() {
        // given
        Hand hand = new Hand();
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.SIX);
        hand.takeCard(card1);
        hand.takeCard(card2);
        Dealer dealer = new Dealer(hand);

        // when
        boolean actual = dealer.ableToTakeMoreCards();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    void 카드_합이_한계를_넘으면_제한됨을_나타낸다() {
        // given
        Hand hand = new Hand();
        Card card1 = new Card(CardSuit.CLUB, CardRank.TEN);
        Card card2 = new Card(CardSuit.DIAMOND, CardRank.TEN);
        Card card3 = new Card(CardSuit.HEART, CardRank.TEN);
        hand.takeCard(card1);
        hand.takeCard(card2);
        hand.takeCard(card3);
        Dealer dealer = new Dealer(hand);

        // when
        boolean overLimit = dealer.isOverLimit(21);

        // then
        assertThat(overLimit).isTrue();
    }

    @Test
    void 딜러는_챌린저가_아니다() {
        Dealer dealer = new Dealer(new Hand());
        assertThat(dealer.isChallenger()).isFalse();
    }

    @Test
    void 딜러는_카드를_더_뽑을_수_없다() {
        Dealer dealer = new Dealer(new Hand());
        assertThat(dealer.canDecideToTakeMoreCard()).isFalse();
    }

    @Test
    void 딜러는_이름이_없다() {
        Dealer dealer = new Dealer(new Hand());
        assertThat(dealer.doesHaveName()).isFalse();
    }

    @Test
    void 딜러는_첫번째_카드만_공개해야_한다() {
        Dealer dealer = new Dealer(new Hand());
        assertThat(dealer.shouldRevealSingleCard()).isTrue();
    }
}
