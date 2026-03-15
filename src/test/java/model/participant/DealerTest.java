package model.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.card.Card;
import model.card.Rank;
import model.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {
    Participant dealer;

    @BeforeEach
    void setUp() {
        dealer = Dealer.create();
    }

    @Test
    void 가진_카드패가_없는데_오픈하면_예외를_발생한다() {
        assertThatThrownBy(() -> dealer.open())
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 첫_번째_오픈_때는_한_장의_카드만_오픈한다() {
        // given
        Card card1 = Card.of(Suit.SPADE, Rank.THREE);
        Card card2 = Card.of(Suit.SPADE, Rank.FOUR);
        dealer.receive(card1);
        dealer.receive(card2);

        // when
        List<Card> opened = dealer.open();

        // then
        assertThat(opened).containsExactly(card1);
    }

    @Test
    void 첫_번째_오픈이_아니면_모든_카드를_오픈한다() {
        // given
        Card card1 = Card.of(Suit.SPADE, Rank.THREE);
        Card card2 = Card.of(Suit.SPADE, Rank.FOUR);
        dealer.receive(card1);
        dealer.receive(card2);

        // when
        dealer.open();
        List<Card> opened = dealer.open();

        // then
        assertThat(opened).containsExactlyInAnyOrder(card1, card2);
    }
}
