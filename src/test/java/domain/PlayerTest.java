package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Test
    @DisplayName("카드를 받아 수중에 카드를 추가한다")
    void should_add_Card_card() {
        // given
        Card card = new Card(Shape.HEART, Rank.A);
        Player player = new Player("a");

        // then
        player.addCard(card);

        // when
        assertThat(player.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("모두에게 보여줄 플레이어 카드를 가져온다.")
    void should_return_public_able_cards() {
        //given
        Participant player = new Player("amy");
        player.addCard(new Card(Shape.HEART, Rank.A));
        player.addCard(new Card(Shape.HEART, Rank.ONE));
        //when
        List<Card> shownCard = player.getShownCard();

        //then
        assertThat(shownCard).hasSize(2);
    }


    private static Stream<Arguments> cardsArguments() {
        return Stream.of(
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.KING),
                                new Card(Shape.HEART, Rank.QUEEN),
                                new Card(Shape.HEART, Rank.ONE)),
                        21),
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.A),
                                new Card(Shape.HEART, Rank.QUEEN)),
                        21),
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.A),
                                new Card(Shape.HEART, Rank.QUEEN),
                                new Card(Shape.HEART, Rank.ONE)),
                        12),
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.A),
                                new Card(Shape.SPADE, Rank.A)),
                        12),
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.A),
                                new Card(Shape.SPADE, Rank.EIGHT),
                                new Card(Shape.SPADE, Rank.A)),
                        20),
                Arguments.arguments(List.of(new Card(Shape.HEART, Rank.A),
                                new Card(Shape.SPADE, Rank.A),
                                new Card(Shape.CLUB, Rank.A),
                                new Card(Shape.SPADE, Rank.TEN)),
                        13)
        );
    }

    @ParameterizedTest
    @DisplayName("가지고 있는 카드의 합계를 계산한다")
    @MethodSource("cardsArguments")
    void should_return_total_value_of_cards(List<Card> cards, int expected) {
        // given
        Participant player = new Player("a");
        for (Card card : cards) {
            player.addCard(card);
        }

        // when
        final long totalValue = player.getTotalValue();

        // then
        assertThat(totalValue).isEqualTo(expected);
    }
}
