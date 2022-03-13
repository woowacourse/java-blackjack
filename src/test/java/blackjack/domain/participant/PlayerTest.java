package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.RandomDeckGenerator;

class PlayerTest {

    @DisplayName("플레이어명은 공백이 될 수 없습니다.")
    @ParameterizedTest(name = "[{index}] 플레이어명 : \"{0}\"")
    @ValueSource(strings = {"", " "})
    void playerNameBlackExceptionTest(final String name) {
        final Deck deck = Deck.generate(new RandomDeckGenerator());
        assertThatThrownBy(() -> Player.readyToPlay(name, deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어명은 공백이 될 수 없습니다.");
    }

}