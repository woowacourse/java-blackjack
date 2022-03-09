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

    @DisplayName("플레이어의 총 점수가 21점 이하인 경우 hit가 가능하다.")
    @Test
    void 플레이어_게임_지속_가능() {
        String name = "mat";
        List<Card> cards = List.of(Card.of(Denomination.ACE, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND));
        Player player = new Player(name, cards);

        assertThat(player.isPlaying()).isTrue();
    }

    @DisplayName("플레이어의 총 점수가 21점을 초과하는 경우 hit가 불가능하다.")
    @Test
    void 플레이어_게임_지속_불가능() {
        String name = "mat";
        List<Card> cards = List.of(Card.of(Denomination.JACK, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND),
                Card.of(Denomination.KING, Suit.HEART));
        Player player = new Player(name, cards);

        assertThat(player.isPlaying()).isFalse();
    }

    @DisplayName("카드를 받아서 합칠 수 있다.")
    @Test
    void 카드_합침() {
        String name = "mat";
        List<Card> cards = List.of(Card.of(Denomination.JACK, Suit.CLOVER), Card.of(Denomination.KING, Suit.DIAMOND),
                Card.of(Denomination.KING, Suit.HEART));
        Player player = new Player(name, cards);
        Card card = Card.of(Denomination.FIVE, Suit.SPADE);

        player.combine(card);

        assertThat(player.getCards().size()).isEqualTo(4);
    }
}
