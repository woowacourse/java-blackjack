package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ResultTest {

    @ParameterizedTest
    @MethodSource("provideScoreAndResult")
    @DisplayName("점수에 따른 승무패를 계산한다.")
    void findResult(Result result, Cards myCards, Cards otherCards) {
        final Result actual = Result.findResult(myCards, otherCards);

        assertThat(actual).isEqualTo(result);
    }

    static Stream<Arguments> provideScoreAndResult() {
        return Stream.of(
                Arguments.of(Result.DRAW, makeCards(CardNumber.ACE, CardNumber.KING),
                        makeCards(CardNumber.ACE, CardNumber.KING)),
                Arguments.of(Result.DRAW, makeCards(CardNumber.JACK, CardNumber.KING, CardNumber.THREE),
                        makeCards(CardNumber.KING, CardNumber.FIVE, CardNumber.SEVEN)),
                Arguments.of(Result.WIN, makeCards(CardNumber.ACE, CardNumber.KING),
                        makeCards(CardNumber.KING, CardNumber.JACK)),
                Arguments.of(Result.WIN, makeCards(CardNumber.ACE, CardNumber.KING),
                        makeCards(CardNumber.KING, CardNumber.FIVE, CardNumber.SEVEN)),
                Arguments.of(Result.LOSE, makeCards(CardNumber.KING, CardNumber.JACK),
                        makeCards(CardNumber.ACE, CardNumber.KING)),
                Arguments.of(Result.LOSE, makeCards(CardNumber.KING, CardNumber.FIVE, CardNumber.SEVEN), makeCards(
                        CardNumber.ACE, CardNumber.KING))
        );
    }

    static Cards makeCards(CardNumber... cardNumber) {
        List<Card> cards = new ArrayList<>();
        for (CardNumber number : cardNumber) {
            cards.add(new Card(CardPattern.DIAMOND, number));
        }
        return new Cards(cards);
    }
}
