package blackjack.domain;

import static blackjack.domain.card.Shape.DIAMOND;
import static blackjack.domain.card.Value.ACE;
import static blackjack.domain.card.Value.FOUR;
import static blackjack.domain.card.Value.KING;
import static blackjack.domain.card.Value.QUEEN;
import static blackjack.domain.card.Value.THREE;
import static blackjack.domain.card.Value.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {
    @Test
    @DisplayName("딜러가 카드 한 장을 뽑는다.")
    void drawCardTest() {
        Deck deck = Deck.from(List.of(
                new Card(DIAMOND, KING), new Card(DIAMOND, TWO), new Card(DIAMOND, FOUR)
        ));
        Dealer dealer = new Dealer();

        dealer.draw(deck);
        Score score = dealer.getScore();
        Score expected = new Score(10);

        assertThat(score).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("cardsAndScore")
    @DisplayName("자신의 현재 점수가 17점 이상이 될 때까지 추가로 카드를 받는다.")
    void drawCardsUntilScoreBelow17Test(List<Card> cards, int expectedValue) {
        Deck deck = Deck.from(cards);
        Dealer dealer = new Dealer();

        dealer.drawUntilExceedMinimum(deck);
        Score score = dealer.getScore();
        Score expected = new Score(expectedValue);

        assertThat(score).isEqualTo(expected);
    }

    static Stream<Arguments> cardsAndScore() {
        return Stream.of(
                Arguments.arguments(List.of(
                        new Card(DIAMOND, KING), new Card(DIAMOND, TWO), new Card(DIAMOND, FOUR),
                        new Card(DIAMOND, ACE), new Card(DIAMOND, THREE)), 17),
                Arguments.arguments(List.of(
                        new Card(DIAMOND, KING), new Card(DIAMOND, ACE),
                        new Card(DIAMOND, QUEEN)), 21)
        );
    }
}
