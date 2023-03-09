package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 16이하일 때 true 반환")
    void judgmentBelow16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.FOUR, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.decideHit();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 17이상일 때 false 반환")
    void judgmentOver16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.NINE, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.decideHit();

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러는 소프트 17을 적용한다.(Ace는 11로 게산)")
    void soft17() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"), new Hand());
        List<Card> cards = List.of(
            new Card(CardNumber.ACE, CardSuit.SPADE),
            new Card(CardNumber.SIX, CardSuit.HEART)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        int expect = cards.stream()
            .mapToInt(card -> card.getCardNumber().getValue())
            .sum() + 10;

        //when
        int result = dealer.calculateDealerCardNumber();

        //then
        assertThat(result).isEqualTo(expect);
    }
}