package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import static domain.card.CardFixture.cardOf;
import static domain.card.CardFixture.cardsOf15;
import static domain.card.CardFixture.cardsOf20;
import static domain.card.CardFixture.cardsOf22;
import static domain.card.CardFixture.cardsOfBlackjack;
import static domain.card.CardFixture.cardsOfSoft12;
import static domain.card.CardFixture.cardsOfSoft15;
import static domain.card.CardFixture.cardsOfSoft20;
import static domain.card.Rank.ACE;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @DisplayName("패에 카드를 추가할 수 있다.")
    @Test
    void add() {
        Hand hand = new Hand();
        hand.add(cardOf(ACE));

        Card actual = hand.peek();

        assertThat(actual).isEqualTo(cardOf(ACE));
    }

    static Stream<Arguments> hasAce() {
        return Stream.of(
                Arguments.of(cardsOfBlackjack(), true),
                Arguments.of(cardsOfSoft12(), true),
                Arguments.of(cardsOfSoft15(), true),
                Arguments.of(cardsOfSoft20(), true),
                Arguments.of(cardsOf15(), false),
                Arguments.of(cardsOf20(), false),
                Arguments.of(cardsOf22(), false)
        );
    }

    @DisplayName("패의 에이스 유무를 알 수 있다.")
    @MethodSource
    @ParameterizedTest
    void hasAce(List<Card> cards, boolean expected) {
        Hand hand = new Hand();
        cards.forEach(hand::add);
        assertThat(hand.hasAce()).isEqualTo(expected);
    }

    static Stream<Arguments> score() {
        return Stream.of(
                Arguments.of(cardsOfBlackjack(), 21),
                Arguments.of(cardsOfSoft12(), 12),
                Arguments.of(cardsOfSoft15(), 15),
                Arguments.of(cardsOfSoft20(), 20),
                Arguments.of(cardsOf15(), 15),
                Arguments.of(cardsOf20(), 20),
                Arguments.of(cardsOf22(), 22),
                Arguments.of(cardsOf22(), 22)
        );
    }

    @DisplayName("패의 점수를 계산할 수 있다.")
    @MethodSource
    @ParameterizedTest
    void score(List<Card> cards, int expected) {
        Hand hand = new Hand();
        cards.forEach(hand::add);
        assertThat(hand.score()).isEqualTo(expected);
    }
}