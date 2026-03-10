package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 딜러의_카드의_합이_16_이하인_경우_딜러는_카드를_한_장_받는다() {
        Participant dealer = new Dealer();

        List<Card> cards = List.of(
                new Card(Rank.TWO, Suit.HEART),
                new Card(Rank.SEVEN, Suit.CLOVER)
        );
        cards.forEach(dealer::receive);

        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isEqualTo(true);
    }

    @Test
    void 딜러의_카드_합이_17_이상이면_카드를_받지_않는다() {
        Participant dealer = new Dealer();

        List<Card> cards = List.of(
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.SEVEN, Suit.CLOVER)
        );
        cards.forEach(dealer::receive);

        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isEqualTo(false);
    }
}
