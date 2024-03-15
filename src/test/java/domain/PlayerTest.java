package domain;

import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.Hit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    @Test
    void sum() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.drawCard(new Card(Denomination.FIVE, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    @Test
    void sum2() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.score()).isEqualTo(21);
    }

    @DisplayName("합계 점수가 21을 초과하면 버스트")
    @Test
    void bust() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));

        assertThat(player.isBust()).isEqualTo(true);
    }

    @DisplayName("합계 점수가 21을 초과하면 버스트")
    @Test
    void bustState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));
        player.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Bust.class);
    }

    @DisplayName("합계 점수가 21이 넘지 않으면 히트")
    @Test
    void hitState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.JACK, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Hit.class);
    }

    @DisplayName("합계 점수가 21이면 블랙잭")
    @Test
    void blackjackState() {
        final Player player = new Player(new Name("지쳐버린종이"), new BetAmount(100));

        player.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        player.drawCard(new Card(Denomination.ACE, Suit.CLUBS));

        assertThat(player.state).isInstanceOf(Blackjack.class);
    }

    @DisplayName("플레이어는 [딜러] 이름을 사용할 수 없다.")
    @Test
    void playerIsNotDealer() {
        final Name name = new Name("딜러");

        assertThatThrownBy(() -> new Player(name, new BetAmount(100)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
