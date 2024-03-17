package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.fixture.CardFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("이름과 핸드를 가진 플레이어를 생성한다")
    @Test
    void createSuccess() {
        assertThatCode(() -> new Player("마크", new Betting(1000)))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 자신이 가진 핸드를 계산한다")
    @Test
    void calculate() {
        Player player = new Player("마크", new Betting(1000));

        int score = player.calculate();

        assertThat(score).isEqualTo(0);
    }

    @DisplayName("플레이어는 자신의 핸드에 카드를 추가할 수 있다")
    @Test
    void putCard() {
        Player player = new Player("마크", new Betting(1000));

        player.putCard(CardFixture.heartJack());
        int score = player.calculate();

        assertThat(score).isEqualTo(10);
    }

    @DisplayName("플레이어의 카드 점수 합계가 20 이하이면 hit할 수 있다")
    @Test
    void testHit1() {
        Player player = new Player("마크", new Betting(1000));

        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());

        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("플레이어의 카드 점수 합계가 21 이상이면 hit할 수 없다")
    @Test
    void testHit2() {
        Player player = new Player("마크", new Betting(1000));

        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.heartJack());

        assertThat(player.canHit()).isFalse();
    }

    @DisplayName("플레이어의 카드가 두 장이고 21점 이라면 블랙잭이다")
    @Test
    void testIsBlackJack1() {
        Player player = new Player("마크", new Betting(1000));

        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.cloverAce());

        assertThat(player.isBlackjack()).isTrue();
    }

    @DisplayName("플레이어의 카드가 두 장이고 21점이 아니라면 블랙잭이 아니다")
    @Test
    void testIsBlackJack2() {
        Player player = new Player("마크", new Betting(1000));

        player.putCard(CardFixture.heartJack());
        player.putCard(CardFixture.cloverAce());
        player.putCard(CardFixture.diamond3());

        assertThat(player.isBlackjack()).isFalse();
    }
}
