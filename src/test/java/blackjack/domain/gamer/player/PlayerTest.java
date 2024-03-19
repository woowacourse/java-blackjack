package blackjack.domain.gamer.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static blackjack.domain.card.CardScore.ACE;
import static blackjack.domain.card.CardScore.QUEEN;
import static blackjack.domain.card.CardSuit.CLUB;
import static blackjack.domain.card.CardSuit.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("플레이어")
public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("의 이름이 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(name, new Deck(cards -> cards)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("가 처음 받은 카드 두장을 반환한다.")
    void deal() {
        // given
        Player player = new Player("lemone", new Deck(cards -> cards));

        // when
        player.deal();

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("는 카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void hit() {
        // given
        Player player = new Player("lemone", new Deck(cards -> cards));

        // when
        player.hit();

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("는 블랙잭일 경우 더 이상 진행할 수 없다.")
    void canContinueBlackjack() {
        // given
        Player player = new Player("seyang", new Deck(cards ->
                List.of(new Card(HEART, ACE), new Card(CLUB, QUEEN))));

        // when
        player.deal();

        // then
        assertThat(player.canContinue()).isFalse();
    }
}
