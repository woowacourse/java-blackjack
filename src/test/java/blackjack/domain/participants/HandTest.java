package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public class HandTest {
    public static final List<Card> CARDS_8 = Arrays.asList(
        Card.from(Suit.SPADE, Rank.TWO),
        Card.from(Suit.CLUB, Rank.TWO),
        Card.from(Suit.SPADE, Rank.TWO),
        Card.from(Suit.SPADE, Rank.TWO)
    );
    public static final List<Card> CARDS_21_ACE_AS_ONE = Arrays.asList(
        Card.from(Suit.SPADE, Rank.JACK),
        Card.from(Suit.CLUB, Rank.JACK),
        Card.from(Suit.SPADE, Rank.ACE)
    );
    public static final List<Card> CARDS_21_BLACKJACK = Arrays.asList(
        Card.from(Suit.SPADE, Rank.ACE),
        Card.from(Suit.SPADE, Rank.KING)
    );
    public static final List<Card> CARDS_22_BUSTED = Arrays.asList(
        Card.from(Suit.SPADE, Rank.TWO),
        Card.from(Suit.SPADE, Rank.KING),
        Card.from(Suit.SPADE, Rank.KING)
    );

    @DisplayName("calculate() 메서드를 통한 합계 테스트")
    @ParameterizedTest(name = "{0}")
    @MethodSource("cards")
    void calculateTest(String message, List<Card> cards, int expected) {
        Hand hand = new Hand();
        cards.forEach(hand::add);
        assertThat(hand.calculate()).isEqualTo(expected);
    }

    static Stream<Arguments> cards() {
        return Stream.of(
            Arguments.of("정상적인 합 계산",
                CARDS_8, 8
            ),
            Arguments.of("ACE가 1로 계산되는 경우",
                CARDS_21_ACE_AS_ONE, 21
            ),
            Arguments.of("ACE가 11로 계산되는 경우",
                CARDS_21_BLACKJACK, 21
            ),
            Arguments.of("Busted의 경우",
                CARDS_22_BUSTED, 22
            )
        );
    }
}
