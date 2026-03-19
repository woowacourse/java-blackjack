package domain.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Rank;
import domain.card.Suit;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어가 카드를 받으면 카드 개수가 1 증가한다")
    void drawCardByPlayer() {
        Player player = new Player("pobi", 1000);
        Cards cards = new Cards(new Random(1));

        player.drawCard(cards);

        assertEquals(1, player.getCardList().size());
    }

    @Test
    @DisplayName("플레이어 점수가 21점이면 더 이상 HIT 할 수 없다")
    void cannotHitWhenScoreIsTwentyOne() {
        final Player player = new Player("pobi", 1000);
        player.addCardForTest(new Card(Suit.SPADE, Rank.KING));
        player.addCardForTest(new Card(Suit.HEART, Rank.ACE));

        assertFalse(player.canHit());
    }

    @Test
    @DisplayName("플레이어 점수가 21점 미만이면 HIT 할 수 있다")
    void canHitWhenScoreIsLessThanTwentyOne() {
        final Player player = new Player("pobi", 1000);
        player.addCardForTest(new Card(Suit.SPADE, Rank.TEN));
        player.addCardForTest(new Card(Suit.HEART, Rank.NINE));

        assertTrue(player.canHit());
    }
}
