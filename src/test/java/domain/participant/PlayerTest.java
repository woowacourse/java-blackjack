package domain.participant;

import domain.Rank;
import domain.Suit;
import domain.card.Card;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PlayerTest {
    @Test
    void 플레이어가_정상적으로_생성되어야_한다() {
        assertDoesNotThrow(() -> new Player("minseo"));
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_성공() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.Q));
        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBlackjack()).isEqualTo(true);
    }

    @Test
    void 블랙잭_여부를_확인할_수_있다_실패() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.TWO));
        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_성공() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.Q));
        player.addCard(new Card(Suit.CLUB, Rank.K));
        player.addCard(new Card(Suit.CLUB, Rank.J));
        Assertions.assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    void 버스트_여부를_확인할_수_있다_실패() {
        Player player = new Player("jeje");

        player.addCard(new Card(Suit.CLUB, Rank.ACE));
        Assertions.assertThat(player.isBust()).isEqualTo(false);
    }
}
