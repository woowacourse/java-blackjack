package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어 카드 뽑기")
    void drawCard() {
        Player player = Player.from("pobi");
        Card card = new Card(Suit.CLOVER, Rank.ACE);

        player.receiveCard(card);

        assertThat(player.getCardHand()).containsExactly(card);
    }
}
