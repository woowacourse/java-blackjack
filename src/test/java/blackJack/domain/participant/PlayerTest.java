package blackJack.domain.participant;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
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
        assertThat(new Player("rookie")).isNotNull();
    }

    @ParameterizedTest(name = "플레이어의 이름 공백인 경우 검증 테스트")
    @ValueSource(strings = {" ", ""})
    void checkPlayerName(String value) {
        assertThatThrownBy(() -> new Player(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 '딜러'인 경우 검증 테스트")
    void checkProhibitName() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalsePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Symbol.HEART, Denomination.JACK));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.TWO));

        assertThat(player.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 테스트")
    void hasTruePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(new Card(Symbol.SPADE, Denomination.JACK));
        player.receiveCard(new Card(Symbol.HEART, Denomination.JACK));

        assertThat(player.hasNextTurn()).isTrue();
    }
}
