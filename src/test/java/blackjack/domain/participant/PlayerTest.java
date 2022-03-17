package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomDeckGenerator;

class PlayerTest {

    @DisplayName("플레이어 이름은 공백이 될 수 없다.")
    @ParameterizedTest(name = "[{index}] 플레이어명 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    void playerNameBlackExceptionTest(final String name) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        assertThatThrownBy(() -> Player.readyToPlay(name, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 이름은 공백이 될 수 없습니다.");
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

}
