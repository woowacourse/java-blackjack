package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @DisplayName("이름의 길이가 1 이상 5 이하라면 정상적으로 생성된다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "abcde"})
    void playerNameLengthSuccessTest(String name) {
        assertThatCode(() -> new Player(name, 0))
                .doesNotThrowAnyException();
    }

    @DisplayName("이름의 길이가 1 미만 또는 5 초과이면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef"})
    void playerNameLengthErrorTest(String name) {
        assertThatThrownBy(() -> new Player(name, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 1 이상 5 이하이어야 합니다.");
    }

    @DisplayName("초기 카드 2장을 받는다.")
    @Test
    void receiveInitCardsTest() {
        // given
        Player player = new Player("a", 0);

        // when
        player.receiveInitCards(List.of(Card.of(Suit.HEART, Rank.ACE), Card.of(Suit.HEART, Rank.JACK)));

        // then
        assertThat(player.cards()).hasSize(2);
    }

    @DisplayName("카드 1장을 받는다.")
    @Test
    void receiveCardTest() {
        // given
        Player player = new Player("a", 0);

        // when
        player.receiveCard(Card.of(Suit.HEART, Rank.ACE));

        // then
        assertThat(player.cards()).hasSize(1);
    }

    @DisplayName("가지고 있는 패의 총 합이 21을 초과하면 Bust 된다.")
    @Test
    void cardValueSumTest() {
        // given
        Player player = new Player("a", 0);
        player.receiveCard(Card.of(Suit.HEART, Rank.KING));
        player.receiveCard(Card.of(Suit.HEART, Rank.JACK));
        player.receiveCard(Card.of(Suit.HEART, Rank.QUEEN));

        // when & then
        assertThat(player.isBust()).isTrue();
    }
}
