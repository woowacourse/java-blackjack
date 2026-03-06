package domain.participant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Rank;
import domain.Suit;
import domain.card.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Player("minseo"));
    }

    @Test
    void 플레이어의_합계가_제시된_값보다_낮으면_거짓을_반환한다(){
        Player player = new Player("minseo");
        player.add(new Card(Suit.CLUB, Rank.TEN));
        player.add(new Card(Suit.CLUB, Rank.ACE));

        boolean result = player.isPlayerWin(20);
        Assertions.assertEquals(result, true);
    }

    @Test
    void 플레이어의_합계가_제시된_값보다_높으면_참을_반환한다(){
        Player player = new Player("minseo");
        player.add(new Card(Suit.CLUB, Rank.NINE));
        player.add(new Card(Suit.CLUB, Rank.ACE));

        boolean result = player.isPlayerWin(21);
        Assertions.assertEquals(result, false);
    }
}
