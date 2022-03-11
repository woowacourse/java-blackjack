package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("이름으로 null을 받았을 경우 오류")
    void createPlayerNullNameFail() {
        assertThatThrownBy(() -> {
            new Player(null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름으로 Empty 값을 받았을 경우 오류")
    void createPlayerEmptyNameFail() {
        assertThatThrownBy(() -> {
            new Player("");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이름에 공백만 들어올 경우 오류")
    void createPlayerOnlyBlankNameFail() {
        assertThatThrownBy(() -> {
            new Player(" ");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 초기 카드 2장을 받는다")
    void receiveInitCard() {
        Player player = new Player("president");

        player.receiveInitCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 카드를 받는다")
    void receiveCard() {
        Player player = new Player("president");

        player.receiveInitCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.HEART, Denomination.THREE)));

        player.receiveCard(new Card(Suit.DIAMOND, Denomination.TWO));

        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
