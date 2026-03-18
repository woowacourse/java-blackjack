package domain;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import org.junit.jupiter.api.Test;

import static domain.game.BlackjackRule.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 딜러의_카드의_합이_16_이하인_경우_딜러는_카드를_한_장_받는다() {
        Participant dealer = new Dealer(DEALER_NAME);

        Cards cards = CardFixture.fifteenCards();
        cards.cards().forEach(dealer::receive);

        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isTrue();
    }

    @Test
    void 딜러의_카드_합이_17_이상이면_카드를_받지_않는다() {
        Participant dealer = new Dealer(DEALER_NAME);

        Cards cards = CardFixture.seventeenCards();
        cards.cards().forEach(dealer::receive);

        boolean canDraw = dealer.canDraw();

        assertThat(canDraw).isFalse();
    }
}
