package team.blackjack.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class DealerTest {

    @Test
    void hit하면_핸드에_카드가_추가된다() {
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.HEARTS, Rank.KING)
        );

        for (Card card : cards) {
            dealer.hit(card);
        }

        assertThat(dealer.getHand().getCards())
                .extracting(Card::getRank, Card::getSuit)
                .containsExactly(
                        tuple(Rank.ACE, Suit.HEARTS),
                        tuple(Rank.KING, Suit.HEARTS));
    }

    @Test
    void 킹과_에이스를_각각_1장씩_받은_딜러의_점수는_21로_정상_계산된다() {
        Dealer dealer = new Dealer();
        List<Card> cards = List.of(
                new Card(Suit.HEARTS, Rank.ACE),
                new Card(Suit.CLUBS, Rank.KING)
        );

        for (Card card : cards) {
            dealer.hit(card);
        }

        assertThat(dealer.getScore()).isEqualTo(21);
    }

    @Test
    void 딜러가_카드를_받지않은_경우_점수는_0으로_정상_계산된다() {
        Dealer dealer = new Dealer();
        assertThat(dealer.getScore()).isEqualTo(0);
    }
}
