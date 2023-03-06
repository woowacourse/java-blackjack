import domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.from(Name.from("pobi"));
    }

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest() {
        player.receive(Card.of(CardShape.SPADE, CardRank.ACE));

        assertThat(player).extracting("cards")
                .asList()
                .hasSize(1);
    }

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.THREE));

        assertThat(player.calculateScore())
                .isEqualTo(14);
    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.THREE));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));

        assertThat(player.calculateScore())
                .isEqualTo(14);
    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 11로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));

        assertThat(player.calculateScore())
                .isEqualTo(12);
    }

    @DisplayName("카드의 합이 21이 넘으면 버스트 된다.")
    @Test
    void isBustedSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.JACK));
        player.receive(Card.of(CardShape.HEART, CardRank.TEN));
        player.receive(Card.of(CardShape.HEART, CardRank.QUEEN));

        assertThat(player.isBusted()).isTrue();
    }

    @DisplayName("카드의 합이 21이 넘지 않으면 버스트 되지 않는다.")
    @Test
    void isNotBustedSuccessTest() {
        player.receive(Card.of(CardShape.CLUB, CardRank.ACE));
        player.receive(Card.of(CardShape.HEART, CardRank.ACE));
        player.receive(Card.of(CardShape.SPADE, CardRank.ACE));

        assertThat(player.isBusted()).isFalse();
    }
}
