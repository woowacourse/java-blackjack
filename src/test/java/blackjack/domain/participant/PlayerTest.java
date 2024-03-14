package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.PlayerFixture.플레이어;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void 플레이어_카드의_총_점수를_계산할_수_있다() {
        final Player player = 플레이어("pobi");
        player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
        player.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

        final int result = player.calculateScore();

        assertThat(result).isEqualTo(16);
    }

    @Nested
    @DisplayName("플레이어가 카드를 더 받아야 하는지 판별할 수 있다.")
    class PlayerCanReceiveCard {

        @Test
        void 플레이어의_상태가_블랙잭이라면_카드를_받을_수_없다() {
            final Player player = 플레이어("pobi");
            player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
            player.receiveCard(new Card(Suit.SPADE, Denomination.ACE));

            final boolean result = player.canReceiveCard();

            assertThat(result).isFalse();
        }

        @Test
        void 플레이어의_상태가_버스트라면_카드를_받을_수_없다() {
            final Player player = 플레이어("pobi");
            player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
            player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
            player.receiveCard(new Card(Suit.SPADE, Denomination.QUEEN));

            final boolean result = player.canReceiveCard();

            assertThat(result).isFalse();
        }

        @Test
        void 플레이어의_상태가_스테이라면_카드를_받을_수_없다() {
            final Player player = 플레이어("pobi");
            player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
            player.receiveCard(new Card(Suit.SPADE, Denomination.JACK));

            player.stay();
            final boolean result = player.canReceiveCard();

            assertThat(result).isFalse();
        }

        @Test
        void 플레이어_카드의_총_점수가_21보다_작고_스테이가_아니라면_카드를_받을_수_있다() {
            final Player player = 플레이어("pobi");
            player.receiveCard(new Card(Suit.DIAMOND, Denomination.KING));
            player.receiveCard(new Card(Suit.SPADE, Denomination.SIX));

            final boolean result = player.canReceiveCard();

            assertThat(result).isTrue();
        }
    }
}
