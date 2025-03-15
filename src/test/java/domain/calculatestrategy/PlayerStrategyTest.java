package domain.calculatestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Betting;
import domain.gamer.Nickname;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerStrategyTest {

    @DisplayName("플레이어의 카드 합을 계산한다.")
    @Test
    void 플레이어의_카드_합을_계산한다() {

        // given
        final Player player = new Player(new Nickname("체체"), new Betting(1000));
        player.hit(new Card(Rank.ACE, Shape.SPADE));
        player.hit(new Card(Rank.ACE, Shape.HEART));
        player.hit(new Card(Rank.ACE, Shape.CLOVER));
        player.hit(new Card(Rank.ACE, Shape.DIAMOND));

        // when
        final int sumOfRank = player.getSumOfRank();

        // then
        assertThat(sumOfRank).isEqualTo(14);
    }

}
