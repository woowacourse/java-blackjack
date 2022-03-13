package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.result.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.HEART, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.TWO));

        assertThat(player.hasNextTurn()).isFalse();
    }

    @Test
    @DisplayName("플레이어의 카드 추가 분배가 가능한 경우 테스트")
    void hasTruePlayerNextTurn() {
        Player player = new Player("kei");
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.HEART, Denomination.JACK));

        assertThat(player.hasNextTurn()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 승리하는 경우 승부 결과 반환 테스트")
    void getMatchResultPlayerWin() {
        Player player = new Player("rookie");
        player.receiveCard(Card.from(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(Card.from(Symbol.CLOVER, Denomination.SEVEN));

        assertThat(player.getMatchResult(dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 무승부인 경우 승부 결과 반환 테스트")
    void getMatchResultPlayerDraw() {
        Player player = new Player("rookie");
        player.receiveCard(Card.from(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.getMatchResult(dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 패배하는 경우 승부 결과 반환 테스트")
    void getMatchResultPlayerLose() {
        Player player = new Player("rookie");
        player.receiveCard(Card.from(Symbol.CLOVER, Denomination.ACE));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.SEVEN));

        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.receiveCard(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.getMatchResult(dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트로 플레이어가 패배하는 경우 승부 결과 반환 테스트")
    void getMatchResultAllBurst() {
        Player player = new Player("rookie");
        player.receiveCard(Card.from(Symbol.CLOVER, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.SPADE, Denomination.TWO));

        Dealer dealer = new Dealer();
        dealer.receiveCard(Card.from(Symbol.HEART, Denomination.JACK));
        dealer.receiveCard(Card.from(Symbol.DIAMOND, Denomination.JACK));
        player.receiveCard(Card.from(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.getMatchResult(dealer)).isEqualTo(MatchResult.LOSE);
    }
}
