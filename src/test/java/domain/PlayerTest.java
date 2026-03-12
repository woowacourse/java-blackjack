package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void naturalBlackJack_상태인_플레이어는_카드를_더_뽑을_수_없다() {
        Player player = new Player("pobi", 1000);

        player.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        player.receiveCard(new Card(Rank.KING, Suit.HEART));
        player.markNaturalBlackJack();

        assertThat(player.canDraw()).isFalse();
    }

    @Test
    void bust인_플레이어는_카드를_더_뽑을_수_없다() {
        Player player = new Player("pobi", 1000);

        player.receiveCard(new Card(Rank.KING, Suit.SPADE));
        player.receiveCard(new Card(Rank.QUEEN, Suit.HEART));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUB));

        assertThat(player.canDraw()).isFalse();
    }
}