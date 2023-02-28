package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = {"gr ay", " luca", " ", "", "bada "})
    @DisplayName("참가자의 이름에 빈 문자열이 들어오는 경우 예외가 발생한다.")
    void createWithBlankName(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "가나다라마바", "123456"})
    @DisplayName("참가자 이름의 길이가 5보다 큰 경우 예외가 발생한다.")
    void createWithWrongRangeName(String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "gray", "luca", "hello"})
    @DisplayName("참가자 이름의 길이가 1~5 사이인 경우 정상적으로 생성된다.")
    void createPlayer(String name) {
        Player player = new Player(name);

        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("참가자는 받은 카드를 자신의 패에 추가한다.")
    void addCardInCards() {
        Player pobi = new Player("pobi");

        pobi.addCard(new Card(Symbol.CLOVER, Number.ACE));
        pobi.addCard(new Card(Symbol.DIAMOND, Number.TEN));

        assertThat(pobi.getCards().getCards().size()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("generateCards")
    @DisplayName("참가자는 자신의 카드 점수를 계산한다.")
    void calculateCards(List<Card> cards, int expectedScore) {
        Player pobi = new Player("pobi");
        for (Card card : cards) {
            pobi.addCard(card);
        }

        int score = pobi.calculateScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    static Stream<Arguments> generateCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.CLOVER, Number.TWO)), 9),
                Arguments.of(List.of(new Card(Symbol.HEART, Number.NINE), new Card(Symbol.HEART, Number.TWO)), 11),
                Arguments.of(List.of(new Card(Symbol.DIAMOND, Number.TEN), new Card(Symbol.SPADE, Number.TEN)), 20),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.THREE), new Card(Symbol.CLOVER, Number.TWO)), 5),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.SEVEN), new Card(Symbol.SPADE, Number.SEVEN), new Card(Symbol.DIAMOND, Number.SEVEN)), 21),
                Arguments.of(List.of(new Card(Symbol.CLOVER, Number.TEN), new Card(Symbol.SPADE, Number.ACE), new Card(Symbol.DIAMOND, Number.SEVEN)), 18)
        );
    }
}
