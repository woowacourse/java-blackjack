package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름과 카드 리스트가 주어지면 정상적으로 생성된다.")
    @Test
    void 플레이어_생성_정상() {
        String name = "mat";
        List<Card> cards = List.of(Card.of(Denomination.ACE, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND));

        assertDoesNotThrow(() -> new Player(name, cards));
    }

    @DisplayName("플레이어가 가진 카드의 총점을 구한다.")
    @Test
    void 플레이어_카드_총점_11() {
        String name = "mat";
        List<Card> cards = List.of(Card.of(Denomination.ACE, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND));
        Player player = new Player(name, cards);

        assertThat(player.getTotalScore()).isEqualTo(11);
    }

    @DisplayName("플레이어가 가진 카드의 총점을 구한다.")
    @Test
    void 플레이어_카드_총점_20() {
        String name = "sudal";
        List<Card> cards = List.of(Card.of(Denomination.JACK, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND));
        Player player = new Player(name, cards);

        assertThat(player.getTotalScore()).isEqualTo(20);
    }
}
