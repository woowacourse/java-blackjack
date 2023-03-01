import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("요청한 카드 저장 확인 테스트")
    void shouldSuccessTakeCard() {
        Player player = new Player(new Name("seongha"));
        player.takeCard(new Card("10다이아몬드", 10));
        player.takeCard(new Card("3다이아몬드", 3));
        Assertions.assertThat(player.getCards().size()).isEqualTo(2);
    }
}
