package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.dealCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        Assertions.assertThat(player.score()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum2() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        Assertions.assertThat(player.score()).isEqualTo(21);
    }

    @Test
    @DisplayName("합계 점수가 21을 초과하면 버스트")
    void bust() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.dealCard(new Card(Denomination.QUEEN, Suit.CLUBS));

        Assertions.assertThat(player.isBust()).isEqualTo(true);
    }

    @Test
    @DisplayName("합계 점수가 21을 초과하면 버스트")
    void notBust() {
        final Player player = new Player(new Name("지쳐버린종이"));

        player.dealCard(new Card(Denomination.KING, Suit.CLUBS));
        player.dealCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.dealCard(new Card(Denomination.ACE, Suit.CLUBS));

        Assertions.assertThat(player.isNotBust()).isEqualTo(true);
    }
}
