package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.RandomCardShuffler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("점수가 16 이하이면 카드를 받을 수 있다")
    void canReceiveCard_returnsTrue_whenScoreIsEqualToSixteen() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SIX));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("점수가 17 이상이면 카드를 받을 수 없다")
    void canReceiveCard_returnsFalse_whenScoreIsEqualToSeventeen() {
        // given
        Dealer dealer = new Dealer(new Deck(new RandomCardShuffler()));
        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        assertThat(result).isFalse();
    }
}
