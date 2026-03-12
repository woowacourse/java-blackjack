package team.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void hit하면_플레이어_핸드에_카드가_추가된다() {
        Player player = new Player("pobi");

        player.hit(Card.ACE_OF_HEARTS);

        assertThat(player.getCards())
                .containsExactly(Card.ACE_OF_HEARTS);
    }

    @Test
    void 킹과_에이스를_각각_1장씩_받은_플레이어의_점수는_21로_정상_계산된다() {
        Player player = new Player("pobi");

        player.hit(Card.ACE_OF_HEARTS);
        player.hit(Card.KING_OF_CLUBS);

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    void 사용자가_카드를_받지않은_경우_점수는_0으로_정상_계산된다() {
        Player player = new Player("pobi");
        assertThat(player.getScore()).isEqualTo(0);
    }
}
