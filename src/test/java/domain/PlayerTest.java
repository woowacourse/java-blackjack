package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
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
        Card card = new Card(Shape.HEART, Rank.ACE);
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
        player.addCard(new Card(Shape.HEART, Rank.ACE));
        player.addCard(new Card(Shape.HEART, Rank.KING));

        //when
        List<Card> shownCard = player.getShownCard();

        //then
        assertThat(shownCard).hasSize(2);
    }

    private static Stream<Arguments> cardsArguments() {
        return Stream.of(Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.KING),
                                new Card(Shape.HEART, Rank.QUEEN),
                                new Card(Shape.HEART, Rank.ACE)), 21),
                Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.ACE),
                                new Card(Shape.HEART, Rank.QUEEN)), 21),
                Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.ACE),
                                new Card(Shape.HEART, Rank.QUEEN),
                                new Card(Shape.HEART, Rank.ACE)), 12),
                Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.ACE),
                                new Card(Shape.SPADE, Rank.ACE)), 12),
                Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.ACE),
                                new Card(Shape.SPADE, Rank.EIGHT),
                                new Card(Shape.SPADE, Rank.ACE)), 20),
                Arguments.arguments(
                        List.of(new Card(Shape.HEART, Rank.ACE),
                                new Card(Shape.SPADE, Rank.ACE),
                                new Card(Shape.CLUB, Rank.ACE),
                                new Card(Shape.SPADE, Rank.TEN)), 13));
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

    /***
     * 플레이어 21 이하면 다시 입력받기 -> boolean true/ false
     * 딜러 16이하면 다시 뽑기
     */
    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 21 이하일 경우, true를 반환한다")
    void should_return_true_when_can_pick() {
        //given
        List<Card> cards = List.of(new Card(Shape.HEART, Rank.KING),
                new Card(Shape.HEART, Rank.NINE),
                new Card(Shape.HEART, Rank.TWO));
        Participant player = new Player("a");
        for (Card card : cards) {
            player.addCard(card);
        }

        // when
        boolean canPick = player.canPick();

        // then
        assertThat(canPick).isEqualTo(true);
    }

    @Test
    @DisplayName("현재 카드의 합이 카드를 뽑을 수 있는 조건인 22 이상 경우, false를 반환한다")
    void should_return_false_when_cannot_pick() {
        //given
        List<Card> cards = List.of(new Card(Shape.HEART, Rank.KING),
                new Card(Shape.HEART, Rank.QUEEN),
                new Card(Shape.HEART, Rank.TWO));
        Participant player = new Player("a");
        for (Card card : cards) {
            player.addCard(card);
        }

        //when
        boolean canPick = player.canPick();

        //then
        assertThat(canPick).isFalse();
    }
}
