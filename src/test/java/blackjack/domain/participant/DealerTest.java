package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("카드가 16이하면 카드를 더 뽑을 수 있다.")
    @Test
    void testPlayerCanDrawCard() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SIX));

        Dealer dealer = new Dealer(cardHand);

        // when
        boolean canHit = dealer.canHit();

        // then
        assertThat(canHit).isTrue();
    }

    @DisplayName("카드가 16이 초과한다면 카드를 더 뽑을 수 없다.")
    @Test
    void testPlayerCanDrawCard_false() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.JACK));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer dealer = new Dealer(cardHand);

        // when
        boolean canHit = dealer.canHit();

        // then
        assertThat(canHit).isFalse();
    }

    @DisplayName("딜러는 받은 카드 중 1장의 카드만을 보여준다.")
    @Test
    void test_showStartCards() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.CLUB, CardRank.NINE));
        cardHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN));

        Dealer score = new Dealer(cardHand);

        // when
        List<Card> startCards = score.showStartCards();

        // then
        assertThat(startCards).hasSize(1);
    }

}
