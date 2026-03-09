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
    Participant dealer;

    @BeforeEach
    void setUp() {
        List<Card> testCards = new ArrayList<>();
        testCards.add(new Card(Rank.EIGHT, Suit.HEART));
        testCards.add(new Card(Rank.EIGHT, Suit.CLOVER));
        dealer = new Dealer();
    }

    @Test
    void 딜러는_카드의_합이_16_이하인_경우_카드를_한_장_받는다() {
        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isTrue();
    }
}
