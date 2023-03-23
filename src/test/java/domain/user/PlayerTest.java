package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import domain.money.BettingAmount;
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
        player.hit(Card.of(Denomination.JACK, Suits.HEART));
        player.hit(Card.of(Denomination.QUEEN, Suits.HEART));
        assertThat(player.isHittable()).isTrue();
    }

    @DisplayName("카드 점수가 21 이상이면 카드를 받을 수 없다")
    @Test
    void stay_WhenScoreOver21() {
        player.hit(Card.of(Denomination.JACK, Suits.HEART));
        player.hit(Card.of(Denomination.FIVE, Suits.HEART));
        player.hit(Card.of(Denomination.SIX, Suits.HEART));
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

    @DisplayName("베팅금액을 설정한다")
    @Test
    void betting() {
        player.betting(10000);
        assertThat(player.getBettingAmount()).isEqualTo(BettingAmount.valueOf(10000));
    }
}
