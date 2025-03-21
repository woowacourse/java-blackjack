package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    void should_add_card() {
        // given
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Player player = new Player("a");

        // then
        player.addCard(cardOfHeartAce);

        // when
        List<Card> cards = player.getCards();
        assertAll(
                () -> assertThat(cards).hasSize(1),
                () -> assertThat(cards).contains(cardOfHeartAce)
        );
    }

    @Test
    @DisplayName("블랙잭 시작 후 받은 모두에게 보여줄 카드들을 가져온다.")
    void should_return_cards_to_show_when_blackjack_started() {
        //given
        Participant player = new Player("amy");
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
        player.addCard(cardOfHeartAce);
        player.addCard(cardOfHeartKing);

        //when
        List<Card> shownCard = player.getFirstShownCard();

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

    @Test
    @DisplayName("현재 플레이어가 카드를 받을 수 있는지 여부를 반환한다")
    void should_return_true_when_can_pick() {
        //given
        Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
        Card cardOfHeartNine = new Card(Shape.HEART, Rank.NINE);
        Card cardOfHeartTwo = new Card(Shape.HEART, Rank.TWO);
        Participant player = new Player("a");
        player.addCard(cardOfHeartKing);
        player.addCard(cardOfHeartNine);
        player.addCard(cardOfHeartTwo);

        // when
        boolean canPick = player.canPick();

        // then
        assertThat(canPick).isEqualTo(true);
    }

    @Test
    @DisplayName("현재 플레이어가 카드를 받을 수 있는지 여부를 반환한다")
    void should_return_false_when_can_not_pick() {
        //given
        Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
        Card cardOfHeartQueen = new Card(Shape.HEART, Rank.QUEEN);
        Card cardOfHeartTwo = new Card(Shape.HEART, Rank.TWO);
        Participant player = new Player("a");
        player.addCard(cardOfHeartKing);
        player.addCard(cardOfHeartQueen);
        player.addCard(cardOfHeartTwo);

        //when
        boolean canPick = player.canPick();

        //then
        assertThat(canPick).isEqualTo(false);
    }

    @Test
    @DisplayName("블랙잭인 경우를 반환한다.")
    void should_return_true_when_is_black_jack() {
        // given
        Card cardOfHeartAce = new Card(Shape.HEART, Rank.ACE);
        Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
        Participant player = new Player("a");
        player.addCard(cardOfHeartAce);
        player.addCard(cardOfHeartKing);

        // when
        boolean isBlackJack = player.isBlackJack();

        // then
        assertThat(isBlackJack).isEqualTo(true);
    }

    @Test
    @DisplayName("블랙잭인 경우를 반환한다.")
    void should_return_false_when_is_not_black_jack() {
        // given
        Card cardOfHeartKing = new Card(Shape.HEART, Rank.KING);
        Card cardOfHeartQueen = new Card(Shape.HEART, Rank.QUEEN);
        Participant player = new Player("a");
        player.addCard(cardOfHeartKing);
        player.addCard(cardOfHeartQueen);

        // when
        boolean isBlackJack = player.isBlackJack();

        // then
        assertThat(isBlackJack).isEqualTo(false);
    }
}
