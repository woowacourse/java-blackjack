package domain.player;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어는 카드의 합이 21미만일 때만 히트할 수 있다")
    @Test
    void canHit() {
        final Player player = new Player(new Name("종이"));
        player.hit(new Card(Rank.TEN, Suit.CLUBS));
        player.hit(new Card(Rank.TEN, Suit.CLUBS));
        Assertions.assertThat(player.canHit()).isTrue();
    }
    @DisplayName("플레이어는 카드의 합이 21이상이면 히트할 수 없다")
    @Test
    void canNotHit() {
        final Player player = new Player(new Name("종이"));
        player.hit(new Card(Rank.TEN, Suit.CLUBS));
        player.hit(new Card(Rank.ACE, Suit.CLUBS));
        Assertions.assertThat(player.canHit()).isFalse();
    }
}
