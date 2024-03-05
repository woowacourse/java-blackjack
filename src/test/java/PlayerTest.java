import domain.Card;
import domain.Name;
import domain.Player;
import domain.Rank;
import domain.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 잘 생성된다.")
    void playerConstructSuccessTest() {
        Assertions.assertThatNoException()
                .isThrownBy(() -> new Player(new Name("이름")));
    }

    @Test
    @DisplayName("플레이어가 카드를 잘 받는다.")
    void playerReceiveCardTest() {
        Player player = new Player(new Name("이름"));
        Assertions.assertThatNoException()
                .isThrownBy(() -> player.receiveCard(new Card(Shape.HEART, Rank.ACE)));
    }

    @Test
    @DisplayName("플레이어 카드의 합을 계산한다.")
    void player() {
        Player player = new Player(new Name("이름"));
        player.receiveCard(new Card(Shape.HEART, Rank.SEVEN));
        player.receiveCard(new Card(Shape.HEART, Rank.JACK));

        int result = player.calculateTotalScore();

        Assertions.assertThat(result).isEqualTo(17);
    }

}
