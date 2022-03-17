package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.card.generator.RandomDeckGenerator;
import blackjack.domain.participant.state.HitState;
import blackjack.domain.participant.state.PlayState;
import blackjack.domain.participant.state.finished.BlackjackState;
import blackjack.domain.participant.state.finished.BustState;
import blackjack.domain.participant.state.finished.StandState;

class PlayerTest {

    @DisplayName("플레이어 이름은 공백이 될 수 없다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    void playerNameBlackExceptionTest(final String name) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        assertThatThrownBy(() -> Player.readyToPlay(name, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 공백이 될 수 없습니다.");
    }

    @DisplayName("플레이어는 베팅 당시의 금액을 지니고 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 베팅 금액 : \"{0}\"")
    @ValueSource(ints = {1000, 100000, 200300})
    void playerNameBlackExceptionTest(final int expectedBettingAmount) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        final Player player = Player.readyToPlay("playerName", deck);
        player.betAmount(expectedBettingAmount);

        final int actualBettingAmount = player.getBettingAmount();
        assertThat(actualBettingAmount).isEqualTo(expectedBettingAmount);
    }

    @DisplayName("동일한 이름인지 비교할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 플레이어 이름 : \"{0}\"")
    @ValueSource(strings = {"poby", "if", "sun"})
    void playerEqualsNameTest(final String expectedPlayerName) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        final Player player = Player.readyToPlay(expectedPlayerName, deck);

        assertThat(player.equalsName(expectedPlayerName)).isTrue();
    }

    @DisplayName("베팅하지 않은 상태에서 게임을 시작할 수 없다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckBlackjackStateTest")
    void mustBeWaitStateIfNotBetYetTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);
        final Player player = Player.readyToPlay("name", deck);

        assertThatThrownBy(() -> player.drawCard(deck, true))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("베팅을 하지 않았습니다.");
    }

    @DisplayName("처음에 받은 카드의 합이 21이면 Blackjack 상태여야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckBlackjackStateTest")
    void checkBlackjackStateTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);

        final PlayState actualState = player.getState();
        assertThat(actualState).isInstanceOf(BlackjackState.class);
    }

    @DisplayName("턴이 종료된 상태에서는 카드를 뽑을 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckBlackjackStateTest")
    void checkFinishedStateCannotDrawCardTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);

        assertThatThrownBy(() -> player.drawCard(deck, true))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("턴이 이미 종료되었습니다.");
    }

    @DisplayName("턴이 종료된 상태에서는 베팅을 할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckBlackjackStateTest")
    void checkFinishedStateCannotBetTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);

        assertThatThrownBy(() -> player.betAmount(1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 베팅이 완료되었습니다.");
    }

    @DisplayName("카드의 합이 21을 넘으면 Bust 상태여야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckBustStateTest")
    void checkBustStateTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        if (player.isPossibleToDrawCard()) {
            player.drawCard(deck, true);
        }

        final PlayState actualState = player.getState();
        assertThat(actualState).isInstanceOf(BustState.class);
    }

    @DisplayName("블랙잭이 아닌 상태에서 카드의 합이 21이 되면 Stand 상태여야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckStandWithInNotBlackTest")
    void checkStandStateWithInNotBlackTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        if (player.isPossibleToDrawCard()) {
            player.drawCard(deck, true);
        }

        final PlayState actualState = player.getState();
        assertThat(actualState).isInstanceOf(StandState.class);
    }

    @DisplayName("카드를 뽑지 않을 경우 Stand 상태여야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckStandWithInNotBlackTest")
    void checkStandStateTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        player.drawCard(deck, false);

        final PlayState actualState = player.getState();
        assertThat(actualState).isInstanceOf(StandState.class);
    }

    @DisplayName("베팅 이후로 턴을 끝내지 않은 상태에서는 Hit 상태여야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckHitStateTest")
    void checkHitStateTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        player.drawCard(deck, true);

        final PlayState actualState = player.getState();
        assertThat(actualState).isInstanceOf(HitState.class);
    }

    @DisplayName("베팅을 한 이후로 다시 베팅을 할 수 없어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.PlayerTestProvider#provideForCheckHitStateTest")
    void checkHitStateCannotBetTest(final List<Card> initializedCard) {
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(initializedCard);
        final Deck deck = Deck.generate(manualDeckGenerator);

        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        player.drawCard(deck, true);

        assertThatThrownBy(() -> player.betAmount(1000))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 베팅이 완료되었습니다.");
    }

}
