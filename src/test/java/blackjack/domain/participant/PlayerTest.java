package blackjack.domain.participant;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Card;
import blackjack.domain.participant.state.StandState;

class PlayerTest {

    private static final String PLAYER_NAME = "name";
    private static final List<Card> DEFAULT_CARDS = List.of(SPADE_ACE, SPADE_TEN);

    @DisplayName("플레이어 이름은 공백이 될 수 없다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    void playerNameBlackExceptionTest(final String name) {
        assertThatThrownBy(() -> Player.readyToPlay(name, DEFAULT_CARDS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 공백이 될 수 없습니다.");
    }

    @DisplayName("플레이어는 베팅 당시의 금액을 지니고 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 베팅 금액 : \"{0}\"")
    @ValueSource(ints = {10, 100, 1000, 2000})
    void playerNameBlackExceptionTest(final int expectedBettingAmount) {
        final Player player = Player.readyToPlay(PLAYER_NAME, DEFAULT_CARDS);
        player.betAmount(expectedBettingAmount);

        final int actualBettingAmount = player.getBettingAmount();
        assertThat(actualBettingAmount).isEqualTo(expectedBettingAmount);
    }

    @DisplayName("플레이어는 베팅을 다시 할 수 없다.")
    @Test
    void betAgainExceptionTest() {
        final Player player = Player.readyToPlay(PLAYER_NAME, DEFAULT_CARDS);
        player.betAmount(1000);

        assertThatThrownBy(() -> player.betAmount(1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 베팅을 했습니다.");
    }

    @DisplayName("플레이어는 2장의 카드를 가진채 게임을 시작해야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForReadyToPlayTest")
    void provideForReadyToPlayTest(final List<Card> expectedCards) {
        final Player player = Player.readyToPlay(PLAYER_NAME, expectedCards);
        final List<Card> actualCards = player.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    @DisplayName("플레이어는 카드를 뽑을 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 초기 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForDrawCardTest")
    void drawCardTest(final List<Card> cards, final Card drewCard) {
        final Player player = Player.readyToPlay(PLAYER_NAME, cards);
        player.drawCard(drewCard);

        final List<Card> actualCards = player.getCards();
        final List<Card> expectedCards = concatCards(cards, drewCard);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private List<Card> concatCards(final List<Card> cards1, final Card card) {
        final List<Card> cards = new ArrayList<>(cards1);
        cards.add(card);
        return cards;
    }

    @DisplayName("플레이어는 카드를 뽑지 않고 게임을 마칠 수 있다.")
    @Test
    void stayTest() {
        final Player player = Player.readyToPlay(PLAYER_NAME, List.of(SPADE_ACE, SPADE_TWO));
        player.stay();

        assertThat(player.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("동일한 이름인지 비교할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"poby", "if", "sun"})
    void equalsNameTest(final String expectedPlayerName) {
        final Player player = Player.readyToPlay(expectedPlayerName, DEFAULT_CARDS);
        assertThat(player.equalsName(expectedPlayerName)).isTrue();
    }

}
