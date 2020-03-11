package user;

import card.Card;
import card.Symbol;
import card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class HandsTest {
    @DisplayName("카드 점수를 정상적으로 계산하는지 확인")
    @ParameterizedTest
    @MethodSource("generateData")
    void score_GivenHands_SumScore(int score, List<Card> cards) {
        Hands hands = new Hands(cards);
        assertThat(hands.score()).isEqualTo(score);
    }

    static Stream<Arguments> generateData() {
        Card ace = new Card(Symbol.ACE, Type.CLUB);
        Card ten = new Card(Symbol.TEN, Type.SPADE);
        Card king = new Card(Symbol.KING, Type.HEART);
        Card four = new Card(Symbol.FOUR, Type.DIAMOND);

        return Stream.of(
                Arguments.of(21, new ArrayList<>(Arrays.asList(ace, king))),
                Arguments.of(15, new ArrayList<>(Arrays.asList(ace, four))),
                Arguments.of(15, new ArrayList<>(Arrays.asList(ace, four, ten))),
                Arguments.of(21, new ArrayList<>(Arrays.asList(ace, ten, king)))
        );
    }
}
