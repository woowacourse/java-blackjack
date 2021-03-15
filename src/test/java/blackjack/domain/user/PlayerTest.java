package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("플레이어를 생성한다.")
    @Test
    void create() {
        Player player = new Player("papi", 500);
        assertThat(player).isEqualTo(new Player("papi", 800));
    }

    @DisplayName("버스트되지 않고 블랙잭이 아니면 카드를 더 받을 수 있다.")
    @Test
    void hit() {
        Player player = new Player("papi", 500);
        player.hit(new Card(Denomination.ACE, Suit.HEART));
        assertThat(player.canHit()).isTrue();
        player.hit(new Card(Denomination.QUEEN, Suit.DIAMOND));
        assertThat(player.canHit()).isFalse();
        player.hit(new Card(Denomination.JACK, Suit.CLOVER));
        player.hit(new Card(Denomination.EIGHT, Suit.SPADE));
        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("플레이어가 건 배팅금액을 알 수 있다.")
    @Test
    void getMoney() {
        Player player = new Player("papi", 500);
        assertThat(player.getMoney()).isEqualTo(new Money(500));
    }

}
