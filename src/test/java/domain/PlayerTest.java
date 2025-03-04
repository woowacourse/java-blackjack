package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    @Test
    @DisplayName("특정 카드를 뽑는지 확인합니다.")
    void addCardTest() {
        //given
        Card card = Card.CLOVER_EIGHT;
        Player player = new Player("코기");

        //when
        player.addCard(card);

        //then
        Assertions.assertTrue(player.getCards().contains(card));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("카드 숫자의 전체 합을 계산합니다.")
    void calculateCardScoreTest(List<Card> cards, int expected) {
        //given
        Player player = new Player("코기");
        cards.forEach(player::addCard);
        //when
        final int cardScore = player.calculateScore();

        //then
        Assertions.assertEquals(cardScore, expected);
    }

    public static Stream<Arguments> calculateCardScoreTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), 14),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), 21),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), 12),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), 23),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), 22)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        Player player = new Player("코기");
        cards.forEach(player::addCard);
        int playStandard = 21;
        // when
        boolean canGetMoreCard = player.canGetMoreCard(playStandard);
        // then
        assertThat(canGetMoreCard).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), true),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), false)
        );
    }
}