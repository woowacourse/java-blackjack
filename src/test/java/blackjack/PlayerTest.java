package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    Deck deck = new Deck();

    @Test
    void 플레이어가_카드를_받는다() {
        //give
        Player player = new Player("모카");

        player.recieveCard(deck);

        assertThat(player.getCardCount()).isEqualTo(1);




    }



}
