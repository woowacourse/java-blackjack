package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 16이하일 때 true 반환")
    void judgmentBelow16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.FOUR, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.judgeDealerHit();

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("처음의 받은 2장의 카드의 합이 17이상일 때 false 반환")
    void judgmentOver16() {
        //given
        Dealer dealer = new Dealer(new ParticipantName("딜러"));
        List<Card> cards = List.of(
            new Card(CardNumber.QUEEN, CardSuit.CLUB),
            new Card(CardNumber.NINE, CardSuit.SPADE)
        );
        for (Card card : cards) {
            dealer.hit(card);
        }
        //when
        boolean result = dealer.judgeDealerHit();

        //then
        assertThat(result).isFalse();
    }
}