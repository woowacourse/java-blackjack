package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantTest {
    @Test
    @DisplayName("Ace의 두 값 중 큰 값이 유리할 때는 큰 값을 쓴다")
    void sum() {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.init(new Card(Rank.ACE, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));

        Assertions.assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("Ace의 두 값 중 작은 값이 유리할 때는 작은 값을 쓴다")
    void sum2() {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.init(new Card(Rank.TEN, Suit.CLUBS), new Card(Rank.TEN, Suit.CLUBS));
        participant.add(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(participant.getScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("정해진 이름이 없다면 예외가 발생한다")
    void nameNotDetermined() {
        final Participant participant = new Dealer();

        assertThatCode(participant::getName).isInstanceOf(IllegalCallerException.class);
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    @DisplayName("참가자의 버스트 여부를 반환한다.")
    void alive(final List<Card> cards, final boolean expected) {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.init(cards.get(0), cards.get(1));
        participant.add(cards.get(2));

        Assertions.assertThat(participant.isBust()).isEqualTo(expected);
    }


    public static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.KING, Suit.HEARTS),
                                new Card(Rank.KING, Suit.SPADES)), true),
                Arguments.of(
                        List.of(new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.FOUR, Suit.HEARTS),
                                new Card(Rank.THREE, Suit.SPADES)), false)
        );
    }
}
