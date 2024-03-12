package domain.player;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ParticipantTest {
    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum() {
        final Participant participant = new Player(new Name("지쳐버린종"));;

        participant.hit(new Card(Rank.FIVE, Suit.CLUBS));
        participant.hit(new Card(Rank.FIVE, Suit.CLUBS));
        participant.hit(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(participant.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum2() {
        final Participant participant = new Player(new Name("지쳐버린종"));;

        participant.hit(new Card(Rank.KING, Suit.CLUBS));
        participant.hit(new Card(Rank.KING, Suit.CLUBS));
        participant.hit(new Card(Rank.ACE, Suit.CLUBS));

        Assertions.assertThat(participant.calculateScore()).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    @DisplayName("플레이어의 버스트 여부를 반환한다.")
    void alive(final List<Card> cards, final boolean expected) {
        final Participant participant = new Player(new Name("지쳐버린종"));

        participant.hit(cards.get(0));
        participant.hit(cards.get(1));
        participant.hit(cards.get(2));

        Assertions.assertThat(participant.isNotBust()).isEqualTo(expected);
    }


    public static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Rank.KING, Suit.CLUBS), new Card(Rank.KING, Suit.HEARTS),
                                new Card(Rank.KING, Suit.SPADES)), false),
                Arguments.of(
                        List.of(new Card(Rank.FIVE, Suit.CLUBS), new Card(Rank.FOUR, Suit.HEARTS),
                                new Card(Rank.THREE, Suit.SPADES)), true)
        );
    }
}
