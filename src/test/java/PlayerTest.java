import domain.Card;
import domain.CardRank;
import domain.Player;
import domain.PlayerName;
import domain.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new PlayerName("pobi"));
    }

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest() {
        player.receive(new Card(CardShape.SPADE, CardRank.ACE));

        Assertions.assertThat(player).extracting("cards")
                .asList()
                .hasSize(1);
    }

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest() {

        player.receive(new Card(CardShape.CLUB, CardRank.ACE));
        player.receive(new Card(CardShape.HEART, CardRank.THREE));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(14);

    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce() {

        player.receive(new Card(CardShape.CLUB, CardRank.ACE));
        player.receive(new Card(CardShape.HEART, CardRank.THREE));
        player.receive(new Card(CardShape.HEART, CardRank.TEN));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(14);

    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted() {

        player.receive(new Card(CardShape.CLUB, CardRank.ACE));
        player.receive(new Card(CardShape.HEART, CardRank.ACE));
        player.receive(new Card(CardShape.HEART, CardRank.TEN));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(12);

    }

    @DisplayName("카드의 합이 21이 넘으면 버스트 된다.")
    @Test
    void isBustedSuccessTest() {

        player.receive(new Card(CardShape.CLUB, CardRank.JACK));
        player.receive(new Card(CardShape.HEART, CardRank.TEN));
        player.receive(new Card(CardShape.HEART, CardRank.QUEEN));

        Assertions.assertThat(player.isBusted()).isTrue();
    }

    @DisplayName("카드의 합이 21이 넘지 않으면 버스트 되지 않는다.")
    @Test
    void isNotBustedSuccessTest() {

        player.receive(new Card(CardShape.CLUB, CardRank.ACE));
        player.receive(new Card(CardShape.HEART, CardRank.ACE));
        player.receive(new Card(CardShape.SPADE, CardRank.ACE));

        Assertions.assertThat(player.isBusted()).isFalse();
    }

}
