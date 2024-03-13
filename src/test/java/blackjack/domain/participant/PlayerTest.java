package blackjack.domain.participant;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.FOUR;
import static blackjack.domain.card.Value.JACK;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.TestDeck;
import blackjack.domain.game.Score;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {
    private static final String TEST_PLAYER_NAME = "testPlayer";
    private static final int TEST_PLAYER_BET_AMOUNT = 10000;

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "      ", "\n"})
    @DisplayName("공백 이름으로는 플레이어를 생성하면 예외가 발생한다.")
    void throwsExceptionWhenNameIsBlankTest(String blankName) {
        assertThatThrownBy(() -> Player.from(blankName, TEST_PLAYER_BET_AMOUNT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    @DisplayName("배팅 금액이 음수면 예외가 발생한다.")
    void throwsExceptionWhenNameIsBlankTest(int negativeBetAmount) {
        assertThatThrownBy(() -> Player.from(TEST_PLAYER_NAME, negativeBetAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("원 미만일 수 없습니다.");
    }

    @Test
    @DisplayName("덱으로 부터 카드 한장을 받아올 수 있다.")
    void drawCardTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new TestDeck(cards);

        Player player = Player.from("pedro", TEST_PLAYER_BET_AMOUNT);
        player.draw(deck);

        List<Card> playerCards = player.getCards();
        assertThat(playerCards).hasSize(1);
    }

    @Test
    @DisplayName("자신의 점수를 계산할 수 있다.")
    void calculateScoreTest() {
        List<Card> cards = List.of(new Card(DIAMOND, TWO), new Card(DIAMOND, THREE), new Card(DIAMOND, FOUR));
        Deck deck = new TestDeck(cards);

        Player player = Player.from("pedro", TEST_PLAYER_BET_AMOUNT);
        for (int i = 0; i < cards.size(); i++) {
            player.draw(deck);
        }

        Score score = player.getScore();

        Score expected = Score.of(9);
        assertThat(score).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("cardsAndBustStatus")
    @DisplayName("자신의 버스트 여부를 판단할 수 있다.")
    void checkBustTest(List<Card> cards, boolean expected) {
        Deck deck = new TestDeck(cards);

        Player player = Player.from("pedro", TEST_PLAYER_BET_AMOUNT);
        for (int i = 0; i < cards.size(); i++) {
            player.draw(deck);
        }

        boolean isBusted = player.isBusted();

        assertThat(isBusted).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndBustStatus() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, ACE)), false),
                Arguments.arguments(List.of(new Card(DIAMOND, JACK), new Card(DIAMOND, QUEEN), new Card(DIAMOND, TWO)), true)
        );
    }
}
