package blackjack;

import blackjack.card.Card;
import blackjack.card.Deck;
import blackjack.card.Number;
import blackjack.card.Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private static final Card TWO_DIAMOND = new Card(Number.TWO, Shape.DIAMOND);
    private static final Card JACK_SPADE = new Card(Number.JACK, Shape.SPADE);
    private static final Card THREE_HEART = new Card(Number.THREE, Shape.HEART);
    private static final Card ACE_CLOVER = new Card(Number.ACE, Shape.CLOVER);
    private Player player;

    private static Stream<Arguments> provideCardsAndExpectedResult() {
        return Stream.of(
                Arguments.of(Arrays.asList(ACE_CLOVER, ACE_CLOVER), 12),
                Arguments.of(Arrays.asList(TWO_DIAMOND, JACK_SPADE, THREE_HEART, ACE_CLOVER), 16),
                Arguments.of(Arrays.asList(TWO_DIAMOND, JACK_SPADE, THREE_HEART, ACE_CLOVER, ACE_CLOVER), 17),
                Arguments.of(Arrays.asList(JACK_SPADE, JACK_SPADE, JACK_SPADE), 30)
        );
    }

    @BeforeEach
    void setUp() {
        player = new Player("wannte");
    }

    @Test
    @DisplayName("플레이어 카드 추가")
    void addCard() {
        Card card = Deck.draw();
        player.addCard(card);
        assertThat(player.getCards()).containsExactly(card);
    }

    @Test
    void calculateCardsTest() {
        player.addCard(TWO_DIAMOND);
        player.addCard(JACK_SPADE);
        player.addCard(THREE_HEART);

        assertThat(player.calculateCards()).isEqualTo(15);
    }

    @Test
    @DisplayName("ACE 개수 세기")
    void countAce() {
        player.addCard(ACE_CLOVER);
        player.addCard(ACE_CLOVER);
        player.addCard(ACE_CLOVER);
        assertThat(player.countAce()).isEqualTo(3);
    }

    @ParameterizedTest
    @MethodSource("provideCardsAndExpectedResult")
    void calculateResultTest(List<Card> cards, int expectedResult) {
        for (Card card : cards) {
            player.addCard(card);
        }

        assertThat(player.calculateResult()).isEqualTo(expectedResult);
    }
}