package domain.card;

import static domain.card.Number.ACE;
import static domain.card.Number.FIVE;
import static domain.card.Number.JACK;
import static domain.card.Number.QUEEN;
import static domain.card.Number.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("핸드 카드 추가 테스트")
    void addCardTest() {
        //given
        Hand hand = new Hand(new ArrayList<>());
        Card card = new Card(DIAMOND, TWO);

        //when
        hand.addCard(card);

        //then
        assertThat(hand.getHand().getFirst()).isEqualTo(card);
    }

    @ParameterizedTest
    @DisplayName("카드 합계 테스트")
    @MethodSource("provideCardDeckForSum")
    void sumTest(Hand hand, int expectedSum){
        AssertionsForClassTypes.assertThat(hand.calculateSum()).isEqualTo(expectedSum);
    }

    private static Stream<Arguments> provideCardDeckForSum(){
        return Stream.of(
                Arguments.of(new Hand(List.of(new Card(DIAMOND, ACE))), 11,
                Arguments.of(new Hand(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE)))), 12,
                Arguments.of(new Hand(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE)))), 13,
                Arguments.of(new Hand(List.of(new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE), new Card(CLOVER, ACE)))), 14,
                Arguments.of(new Hand(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE)))), 21,
                Arguments.of(new Hand(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, ACE), new Card(HEART, ACE)))), 12,
                Arguments.of(new Hand(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, QUEEN), new Card(HEART, ACE)))), 21,
                Arguments.of(new Hand(List.of(new Card(SPADE, QUEEN), new Card(DIAMOND, FIVE), new Card(DIAMOND, ACE), new Card(SPADE, ACE), new Card(HEART, ACE), new Card(CLOVER, ACE)))), 19,
                Arguments.of(new Hand(List.of(new Card(SPADE, JACK), new Card(DIAMOND, JACK)))), 20
        ));
    }
}
