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
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        player.addCard(new Card(Rank.ACE, Suit.SPADE));
        player.addCard(new Card(Rank.KING, Suit.HEART));

        assertThat(player.canDraw()).isFalse();
    }

    @Test
    void bust인_플레이어는_카드를_더_뽑을_수_없다() {
        Player player = new Player(new Name("pobi"), new BettingMoney(1000L));

        player.addCard(new Card(Rank.KING, Suit.SPADE));
        player.addCard(new Card(Rank.QUEEN, Suit.HEART));
        player.addCard(new Card(Rank.TWO, Suit.CLUB));

        assertThat(player.canDraw()).isFalse();
    }
}