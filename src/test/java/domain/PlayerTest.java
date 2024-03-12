package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.dealCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum2() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @Test
    @DisplayName("합계 점수가 21을 초과하면 버스트")
    void bust() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.dealCard(new Card(Denomination.QUEEN, Suit.CLUBS));

        assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    @DisplayName("합계 점수가 21을 초과하면 버스트")
    void notBust() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.canHit()).isEqualTo(true);
    }

    @DisplayName("플레이어는 [딜러] 이름을 사용할 수 없다.")
    @Test
    void playerIsNotDealer() {
        final Name name = new Name("딜러");

        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
