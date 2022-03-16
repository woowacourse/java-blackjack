package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    @DisplayName("Player 생성 테스트")
    void createValidPlayer() {
        assertThat(new Player("rookie", "1000")).isNotNull();
    }

    @ParameterizedTest(name = "플레이어의 이름 공백인 경우 검증 테스트")
    @ValueSource(strings = {" ", ""})
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value, "1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'인 경우 검증 테스트")
    void checkProhibitName() {
        assertThatThrownBy(() -> new Player("딜러", "1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @ParameterizedTest(name = "플레이어 금액이 자연수가 아닌 경우 검증 테스트")
    @ValueSource(strings = {"", "abc", "-1", "0", "12.23", " "})
    void checkValidMoney(String value) {
        assertThatThrownBy(() -> new Player("rookie", value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 자연수여야 합니다.");
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalsePlayerNextTurn() {
        Player player = new Player("kei", "1000");
        player.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Suit.HEART, Denomination.JACK));
        player.receiveCard(Card.from(Suit.SPADE, Denomination.TWO));

        assertThat(player.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 테스트")
    void hasTruePlayerNextTurn() {
        Player player = new Player("kei", "1000");
        player.receiveCard(Card.from(Suit.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Suit.HEART, Denomination.JACK));

        assertThat(player.hasNextTurn()).isTrue();
    }
}
