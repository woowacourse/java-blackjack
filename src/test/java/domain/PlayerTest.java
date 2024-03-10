package domain;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import domain.player.Name;
import domain.player.Participant;
import domain.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class PlayerTest {
    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum() {
        final Player player = new Participant(new Name("지쳐버린종이"));;

        player.hit(new Card(Denomination.FIVE, Symbol.CLOVER));
        player.hit(new Card(Denomination.FIVE, Symbol.CLOVER));
        player.hit(new Card(Denomination.ACE, Symbol.CLOVER));

        Assertions.assertThat(player.calculateScore()).isEqualTo(21);
    }

    @Test
    @DisplayName("플레이어는 자신이 갖는 카드 합계를 계산할 수 있다")
    void sum2() {
        final Player player = new Participant(new Name("지쳐버린종이"));;

        player.hit(new Card(Denomination.KING, Symbol.CLOVER));
        player.hit(new Card(Denomination.KING, Symbol.CLOVER));
        player.hit(new Card(Denomination.ACE, Symbol.CLOVER));

        Assertions.assertThat(player.calculateScore()).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    @DisplayName("플레이어의 버스트 여부를 반환한다.")
    void alive(final List<Card> cards, final boolean expected) {
        final Player player = new Participant(new Name("지쳐버린종이"));

        player.hit(cards.get(0));
        player.hit(cards.get(1));
        player.hit(cards.get(2));

        Assertions.assertThat(player.isNotBust()).isEqualTo(expected);
    }


    public static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Denomination.KING, Symbol.CLOVER), new Card(Denomination.KING, Symbol.HEART),
                                new Card(Denomination.KING, Symbol.SPADE)), false),
                Arguments.of(
                        List.of(new Card(Denomination.FIVE, Symbol.CLOVER), new Card(Denomination.FOUR, Symbol.HEART),
                                new Card(Denomination.THREE, Symbol.SPADE)), true)
        );
    }
}
