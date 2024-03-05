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
    @DisplayName("플레이어 카드가 21이 넘지 않는다.")
    void isNotOverTrueTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.ACE));
        player.receiveCard(new Card(Shape.HEART, Rank.NINE));

        Assertions.assertThat(player.isNotOver()).isTrue();
    }

    @Test
    @DisplayName("플레이어 카드가 21이 넘는다.")
    void isNotOverFalseTest() {
        Player player = new Player(new Name("이름"));

        player.receiveCard(new Card(Shape.HEART, Rank.NINE));
        player.receiveCard(new Card(Shape.SPADE, Rank.NINE));
        player.receiveCard(new Card(Shape.DIAMOND, Rank.FOUR));

        Assertions.assertThat(player.isNotOver()).isFalse();
    }
}
