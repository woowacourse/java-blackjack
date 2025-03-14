package domain;

import domain.card.Card;
import domain.card.CardGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CardGroupTest {

    @Nested
    class CardGroupConstructor {
        private static Stream<Arguments> numberCardsTestArguments() {
            return Stream.of(Arguments.arguments(new Card(DIAMOND, TWO), new Card(HEART, THREE)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, TEN)), Arguments.arguments(new Card(DIAMOND, THREE), new Card(HEART, THREE)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, EIGHT)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, FIVE)), Arguments.arguments(new Card(DIAMOND, KING), new Card(HEART, FIVE)), Arguments.arguments(new Card(DIAMOND, JACK), new Card(HEART, QUEEN)));
        }

        private static Stream<Arguments> aceUpperScoreCardsTestArguments() {
            return Stream.of(Arguments.arguments(new Card(DIAMOND, JACK), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, THREE), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, FOUR), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, KING), new Card(HEART, ACE)), Arguments.arguments(new Card(DIAMOND, JACK), new Card(HEART, ACE)));
        }

        private static Stream<Arguments> aceLowerScoreCardsTestArguments() {
            return Stream.of(Arguments.arguments(new Card(DIAMOND, ACE), new Card(HEART, ACE)));
        }

        private static Stream<Arguments> aceComplexScoreCardsTestArguments() {
            return Stream.of(Arguments.arguments(new Card(DIAMOND, ACE), new Card(HEART, ACE), new Card(SPADE, ACE)));
        }

        @Test
        void 카드_그룹을_생성한다() {
            //given
            final List<Card> cards = List.of(new Card(DIAMOND, ACE), new Card(HEART, EIGHT));

            //when
            final CardGroup cardGroup = new CardGroup(cards);

            //then
            assertThat(cardGroup).isInstanceOf(CardGroup.class);
        }

        @Test
        void 카드를_한_장_넣는다() {
            final Card card = new Card(HEART, ACE);

            final CardGroup cardGroup = new CardGroup();
            cardGroup.addCard(card);

            assertThat(cardGroup.countCards()).isEqualTo(1);
        }

        @Test
        void 카드를_두_장_넣는다() {
            final Card card = new Card(HEART, ACE);
            final Card card2 = new Card(HEART, ACE);

            final CardGroup cardGroup = new CardGroup();
            cardGroup.addCard(card);
            cardGroup.addCard(card2);

            assertThat(cardGroup.countCards()).isEqualTo(2);
        }

        @DisplayName("A를 제외한 카드 점수를 계산한다")
        @ParameterizedTest
        @MethodSource("numberCardsTestArguments")
        void 카드_점수를_계산한다(Card card1, Card card2) {
            //given
            final List<Card> cards = List.of(card1, card2);

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();
            final int scoreSum = card1.getScore().getValue() + card2.getScore().getValue();

            //then
            assertThat(score).isEqualTo(scoreSum);
        }

        @DisplayName("A가 11점이 되는 테스트")
        @ParameterizedTest
        @MethodSource("aceUpperScoreCardsTestArguments")
        void 카드_점수를_계산한다2(Card card1, Card card2) {
            //given
            final List<Card> cards = List.of(card1, card2);

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();
            final int scoreSum = card1.getScore().getValue() + 11;

            //then
            assertThat(score).isEqualTo(scoreSum);
        }

        @DisplayName("A가 2장인 경우 12점")
        @ParameterizedTest
        @MethodSource("aceLowerScoreCardsTestArguments")
        void 카드_점수를_계산한다3(Card card1, Card card2) {
            //given
            final List<Card> cards = List.of(card1, card2);

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(12);
        }

        @DisplayName("A가 3장인 경우 13점")
        @ParameterizedTest
        @MethodSource("aceComplexScoreCardsTestArguments")
        void 카드_점수를_계산한다3(Card card1, Card card2, Card card3) {
            //given
            final List<Card> cards = List.of(card1, card2, card3);

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(13);
        }
    }
}
