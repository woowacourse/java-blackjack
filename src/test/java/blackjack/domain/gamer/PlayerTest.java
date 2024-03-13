package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static blackjack.domain.card.Rank.ACE;
import static blackjack.domain.card.Rank.KING;
import static blackjack.domain.card.Rank.NINE;
import static blackjack.domain.card.Rank.TEN;
import static blackjack.domain.card.Suit.CLUB;
import static blackjack.domain.card.Suit.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("플레이어")
public class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    void validateEmptyTest(String name) {
        // given & when & then
        assertThatCode(() -> new Player(new Gamer(new Hand(List.of())), name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("공백이 아닌 플레이어를 입력해 주세요.");
    }

    @Test
    @DisplayName("처음 받은 카드 두장을 반환한다.")
    void gameDealTest() {
        // given
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");
        // when
        player.draw(List.of(new Card(ACE, CLUB), new Card(NINE, CLUB)));

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드를 뽑기로 결정했을 때 카드 한장을 반환한다.")
    void gameHitTest() {
        // given
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");

        // when
        player.draw(new Card(KING, DIAMOND));

        // then
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드 점수의 합이 21이고 카드가 2장일 경우 블랙잭이다.")
    void blackjackTest() {
        // given
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");

        // when
        player.draw(List.of(new Card(TEN, DIAMOND), new Card(ACE, CLUB)));

        // then
        assertThat(player.isBlackjack()).isEqualTo(true);
    }
}
