package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @Test
    void 딜러는_카드의_합이_16_이하인_경우_카드를_한_장_받는다(){
        Dealer dealer = createDealer(Rank.JACK, Rank.TWO);

        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isTrue();
    }

    @Test
    void 딜러는_카드의_합이_16을_초과하는_경우_카드를_받을_수_없다() {
        Dealer dealer = createDealer(Rank.TEN, Rank.JACK, Rank.QUEEN);

        boolean receivable = dealer.canDraw();

        assertThat(receivable).isFalse();
    }

    @Test
    void 딜러는_카드의_합이_16인_경우_카드를_한_장_받는다() {
        Dealer dealer = createDealer(Rank.TEN, Rank.SIX);

        boolean receivable = dealer.canDraw();

        assertThat(receivable).isTrue();
    }

    private Dealer createDealer(Rank... ranks) {
        Dealer dealer = new Dealer();
        for (Rank rank : ranks) {
            Card card = new Card(rank, Suit.CLOVER);
            dealer.receive(card);
        }

        return dealer;
    }
}