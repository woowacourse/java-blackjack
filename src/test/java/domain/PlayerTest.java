package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setPlayer() {
        player = new Player("kiara");
    }

    @DisplayName("카드 점수가 21보다 작으면 카드를 받을 수 있다")
    @Test
    void hit_WhenScoreUnder21() {
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.QUEEN, Suits.HEART));
        assertThat(player.isHittable()).isTrue();
    }

    @DisplayName("카드 점수가 21 이상이면 카드를 받을 수 없다")
    @Test
    void stay_WhenScoreOver21() {
        player.hit(new Card(Denomination.JACK, Suits.HEART));
        player.hit(new Card(Denomination.FIVE, Suits.HEART));
        player.hit(new Card(Denomination.SIX, Suits.HEART));
        assertThat(player.isHittable()).isFalse();
    }

    @DisplayName("본인의 이름인지 확인한다")
    @Test
    void isRightName_true() {
        assertThat(player.isRightName("kiara")).isTrue();
    }

    @DisplayName("본인의 이름이 아닌지 확인한다")
    @Test
    void isRightName_false() {
        assertThat(player.isRightName("hongo")).isFalse();
    }
}
