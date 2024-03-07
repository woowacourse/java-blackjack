package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름과 핸드를 가진 플레이어를 생성한다")
    @Test
    public void createSuccess() {
        assertThatCode(() -> new Player("마크"))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 자신이 가진 핸드를 계산한다")
    @Test
    public void calculate() {
        Player player = new Player("마크");

        int score = player.calculate();

        assertThat(score).isEqualTo(13);
    }

    @DisplayName("플레이어는 자신의 핸드에 카드를 추가할 수 있다")
    @Test
    public void putCard() {
        Player player = new Player("마크");
        Card card = CardFixture.heartJack();
        int score = player.calculate();

        player.putCard(card);
        int newScore = player.calculate();

        assertThat(score).isEqualTo(13);
        assertThat(newScore).isEqualTo(23);
    }
}
