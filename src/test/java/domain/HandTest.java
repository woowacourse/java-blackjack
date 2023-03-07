package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class HandTest extends AbstractTestFixture {

    @Test
    @DisplayName("카드를 추가할 수 있다")
    void test_add_card() {
        var card = createCard(Letter.ACE);
        var hand = new Hand();
        hand.add(card);

        assertThat(hand.cards()).containsExactly(card);
    }

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"8,K,18", "10,5,15", "A,A,12"})
    void test_score(String letter1, String letter2, int score) {
        var hand = new Hand(createCards(letter1, letter2));

        assertThat(hand.score()).isEqualTo(score);
    }

    static Stream<Arguments> test_score_with_a() {
        return Stream.of(
                Arguments.of(21, new String[] {"J", "10", "A"}),
                Arguments.of(21, new String[] {"7", "3", "A"}),
                Arguments.of(13, new String[] {"A", "A", "A"}),
                Arguments.of(21, new String[] {"A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A"})
        );
    }

    @ParameterizedTest(name = "A는 플레이어에게 유리하게 계산한다")
    @MethodSource
    void test_score_with_a(int score, String[] letters) {
        var hand = new Hand(createCards(letters));

        assertThat(hand.score()).isEqualTo(score);
    }

    @ParameterizedTest(name = "패의 점수가 같은지 알 수 있다.")
    @CsvSource({"K,K,true", "K,A,false"})
    void test_score_same(String letter, String otherLetter, boolean isScoreSame) {
        var hand = new Hand(createCards(letter));
        var otherHand = new Hand(createCards(otherLetter));

        assertThat(hand.hasSameScoreWith(otherHand)).isEqualTo(isScoreSame);
    }

    @ParameterizedTest(name = "패의 점수가 더 큰지 알 수 있다.")
    @CsvSource({"K,K,false", "K,A,false", "A,K,true"})
    void test_score_greater(String letter, String otherLetter, boolean isScoreSame) {
        var hand = new Hand(createCards(letter));
        var otherHand = new Hand(createCards(otherLetter));

        assertThat(hand.hasScoreGreaterThan(otherHand)).isEqualTo(isScoreSame);
    }

    static Stream<Arguments> test_win_lose_draw() {
        return Stream.of(
                Arguments.of(new String[] {"K", "K"}, new String[] {"9"}, Result.WIN),
                Arguments.of(new String[] {"K", "K"}, new String[] {"A", "J"}, Result.LOSE),
                Arguments.of(new String[] {"A"}, new String[] {"9", "2"}, Result.DRAW)
        );
    }

    @ParameterizedTest(name = "패와 패를 비교해 점수가 큰 패를 알아낼 수 있다")
    @MethodSource
    void test_win_lose_draw(String[] letters, String[] otherLetters, Result result) {
        var hand = new Hand(createCards(letters));
        var otherHand = new Hand(createCards(otherLetters));

        assertThat(hand.compareWith(otherHand)).isEqualTo(result);
    }
}
