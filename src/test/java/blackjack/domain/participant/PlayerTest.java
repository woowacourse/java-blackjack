package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
    }

    @Test
    void 플레이어는_카드를_받을_수_있다() {
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));

        assertThat(player).extracting("cardHand")
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(1);
    }

    @Test
    void 플레이어_카드의_총_점수를_계산할_수_있다() {
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        player.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final int result = player.calculateScore();

        assertThat(result).isEqualTo(16);
    }
}
