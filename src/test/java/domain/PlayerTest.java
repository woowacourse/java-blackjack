package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("Player 는 핸드의 총합값을 반환할 수 있다")
    void test1() {
        Player player = new Player("모루");
        player.addCard(new Card(Denomination.TWO, Suit.CLUB));
        player.addCard(new Card(Denomination.FOUR, Suit.CLUB));
        player.addCard(new Card(Denomination.FIVE, Suit.DIAMOND));

        assertThat(player.getHandTotal()).isEqualTo(11);
    }
}
