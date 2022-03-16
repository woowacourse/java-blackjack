package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    @ParameterizedTest
    @MethodSource("bunchOfCards")
    @DisplayName("가지고 있는 카드합이 블랙잭인지 확인한다.")
    void checkBlackjack(List<Card> cards, boolean result) {
        Player player = new Participant("corinne", name -> true);
        for (Card card : cards) {
            player.addCard(card);
        }

        assertThat(player.isBlackjack()).isEqualTo(result);
    }

    private static Stream<Arguments> bunchOfCards() {
        return Stream.of(
                Arguments.of(List.of(new Card(Type.SPADE, Score.ACE),
                        new Card(Type.SPADE, Score.KING)), true),
                Arguments.of(List.of(new Card(Type.SPADE, Score.ACE),
                        new Card(Type.SPADE, Score.JACK)), true),
                Arguments.of(List.of(new Card(Type.SPADE, Score.ACE),
                        new Card(Type.SPADE, Score.QUEEN)), true),
                Arguments.of(List.of(new Card(Type.SPADE, Score.TEN),
                        new Card(Type.SPADE, Score.NINE),
                        new Card(Type.SPADE, Score.TWO)), false),
                Arguments.of(List.of(new Card(Type.SPADE, Score.TEN),
                        new Card(Type.SPADE, Score.NINE)), false)
        );
    }
}
