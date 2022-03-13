package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;

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

    @Test
    @DisplayName("플레이어의 카드 합계 계산 테스트")
    void calculateScore() {
        Player player = new Player("rookie");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.calculateFinalScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 11로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceEleven() {
        Player player = new Player("rookie");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));

        assertThat(player.calculateFinalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 1로 되는 경우 합계 계산 테스트")
    void calculateScoreWithAceOne() {
        Player player = new Player("rookie");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.calculateFinalScore()).isEqualTo(19);
    }

    @Test
    @DisplayName("플레이어의 카드에 Ace가 여러개인 경우 계산 테스트")
    void calculateScoreWithAceCountThree() {
        Player player = new Player("rookie");
        player.receiveCard(new Card(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(new Card(Symbol.HEART, Denomination.ACE));
        player.receiveCard(new Card(Symbol.DIAMOND, Denomination.ACE));
        player.receiveCard(new Card(Symbol.SPADE, Denomination.EIGHT));

        assertThat(player.calculateFinalScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("중복된 카드를 받는 경우 예외 발생 테스트")
    void receiveDuplicatedCard() {
        Card card1 = new Card(Symbol.CLOVER, Denomination.ACE);
        Card card2 = new Card(Symbol.CLOVER, Denomination.ACE);
        Participant participant = new Player("rookie");
        participant.receiveCard(card1);

        assertThatThrownBy(() -> participant.receiveCard(card2))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복된 카드는 받을 수 없습니다.");
    }
}
