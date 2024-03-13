package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어를 생성한다")
    @Test
    public void create() {
        assertThatCode(() -> new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.ACE))))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 핸드가 블랙잭인지 반환한다")
    @Test
    public void isBlackJack() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.ACE)));

        assertThat(player.isBlackJack()).isTrue();
    }

    @DisplayName("플레이어의 핸드가 블랙잭이면 더 플레이 할 수 없다")
    @Test
    public void isPlayable() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.ACE)));

        assertThat(player.isPlayable()).isFalse();
    }

    @DisplayName("플레이어의 핸드가 블랙잭이 아니고 21점 이하면 더 플레이 가능하다")
    @Test
    public void isNotPlayable() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                        CardFixture.fromSuitCloverWith(Denomination.ACE)));

        assertThat(player.isPlayable()).isTrue();
    }

    @DisplayName("플레이어의 핸드가 21점 초과면 버스트다")
    @Test
    public void isBust() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.KING),
                        CardFixture.fromSuitCloverWith(Denomination.TWO)));

        assertThat(player.isBust()).isTrue();
    }

    @DisplayName("플레이어의 핸드가 21점 이하면 버스트가 아니다")
    @Test
    public void isNotBust() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.ACE)));

        assertThat(player.isBust()).isFalse();
    }

    @DisplayName("플레이어의 점수를 계산하여 반환한다")
    @Test
    public void calculate() {
        Player player = new Player("이상",
                Hand.of(CardFixture.fromSuitCloverWith(Denomination.JACK),
                        CardFixture.fromSuitCloverWith(Denomination.ACE)));

        assertThat(player.calculate()).isEqualTo(Score.from(21));
    }
}
