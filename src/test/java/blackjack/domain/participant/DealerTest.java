package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러가 덱에 있는 카드 한장을 뽑아서 플레이어에게 전달")
    void deal() {
        Card card1 = new Card(Suit.CLOVER, Rank.ACE);
        Card card2 = new Card(Suit.DIAMOND, Rank.KING);
        Deck deck = new Deck(Arrays.asList(card1, card2));
        Dealer dealer = new Dealer(Hand.createEmptyHand(), deck);
        Player player = Player.from("플레이어");

        dealer.deal(player);
        assertThat(player.getCards()).containsExactly(card1);
        dealer.deal(player);
        assertThat(player.getCards()).containsExactly(card1, card2);
    }
}