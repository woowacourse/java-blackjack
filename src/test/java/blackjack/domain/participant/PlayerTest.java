package blackjack.domain.participant;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Card;

class PlayerTest {

    @DisplayName("플레이어 이름은 공백이 될 수 없다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    void playerNameBlackExceptionTest(final String name) {
        assertThatThrownBy(() -> Player.readyToPlay(name, List.of(SPADE_ACE, SPADE_TEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 공백이 될 수 없습니다.");
    }

    @DisplayName("플레이어는 베팅 당시의 금액을 지니고 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 베팅 금액 : \"{0}\"")
    @ValueSource(ints = {1000, 100000, 200300})
    void playerNameBlackExceptionTest(final int expectedBettingAmount) {
        final Player player = Player.readyToPlay("playerName", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(expectedBettingAmount);

        final int actualBettingAmount = player.getBettingAmount();
        assertThat(actualBettingAmount).isEqualTo(expectedBettingAmount);
    }

    @DisplayName("플레이어는 베팅을 다시 할 수 없다.")
    @Test
    void betAgainExceptionTest() {
        final Player player = Player.readyToPlay("playerName", List.of(SPADE_ACE, SPADE_TEN));
        player.betAmount(1000);

        assertThatThrownBy(() -> player.betAmount(1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 베팅을 했습니다.");
    }

    @DisplayName("플레이어는 2장의 카드를 가진채 게임을 시작해야 한다.")
    @Test
    void readyToPlayTest() {
        final Player player = Player.readyToPlay("playerName", List.of(SPADE_ACE, SPADE_TWO));
        final List<Card> actualCards = player.getCards();
        assertThat(actualCards).isEqualTo(List.of(SPADE_ACE, SPADE_TWO));
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있어야 한다.")
    @Test
    void drawCardTest() {
        final Player player = Player.readyToPlay("playerName", List.of(SPADE_ACE, SPADE_TWO));
        player.drawCard(SPADE_THREE);

        final List<Card> actualCards = player.getCards();
        final List<Card> expectedCards = List.of(SPADE_ACE, SPADE_TWO, SPADE_THREE);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    @DisplayName("동일한 이름인지 비교할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"poby", "if", "sun"})
    void playerEqualsNameTest(final String expectedPlayerName) {
        final Player player = Player.readyToPlay(expectedPlayerName, List.of(SPADE_ACE, SPADE_TEN));
        assertThat(player.equalsName(expectedPlayerName)).isTrue();
    }

}
