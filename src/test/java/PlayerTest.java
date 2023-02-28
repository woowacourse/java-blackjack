import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 카드를 받을 수 있다.")
    @Test
    void receiveCardSuccessTest(){
        Player player = new Player(new PlayerName("pobi"));

        player.receiveCard(new Card(Shape.SPADE, Number.A));

        Assertions.assertThat(player).extracting("cards")
                .asList()
                .hasSize(1);
    }

    @DisplayName("플레이어가 받은 카드의 점수를 확인할 수 있다.")
    @Test
    void calculateScoreSuccessTest(){
        Player player = new Player(new PlayerName("pobi"));

        player.receiveCard(new Card(Shape.CLUB, Number.A));
        player.receiveCard(new Card(Shape.HEART, Number.THREE));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(14);

    }

    @DisplayName("21을 넘은 경우 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAce(){
        Player player = new Player(new PlayerName("pobi"));

        player.receiveCard(new Card(Shape.CLUB, Number.A));
        player.receiveCard(new Card(Shape.HEART, Number.THREE));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(14);

    }

    @DisplayName("21을 넘지 않는 경우 까지 ACE는 1로 계산한다.")
    @Test
    void calculateScoreSuccessTestWhenHasAceUntilNotBusted(){
        Player player = new Player(new PlayerName("pobi"));

        player.receiveCard(new Card(Shape.CLUB, Number.A));
        player.receiveCard(new Card(Shape.HEART, Number.A));
        player.receiveCard(new Card(Shape.HEART, Number.TEN));

        Assertions.assertThat(player.calculateScore())
                .isEqualTo(12);

    }

}
