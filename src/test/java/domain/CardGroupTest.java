package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardGroupTest {

    @Nested
    class CardGroupConstructor {
        @Test
        void 카드_그룹을_생성한다() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND_ACE),
                    new Card(CardType.HEART_8));

            //when
            final CardGroup cardGroup = new CardGroup(cards);

            //then
            assertThat(cardGroup).isInstanceOf(CardGroup.class);
        }

        @Test
        void 카드를_한_장_넣는다() {
            final Card card = new Card(CardType.HEART_ACE);

            final CardGroup cardGroup = new CardGroup();
            cardGroup.addCard(card);

            assertThat(cardGroup.countCards()).isEqualTo(1);
        }

        @Test
        void 카드를_두_장_넣는다() {
            final Card card = new Card(CardType.HEART_ACE);
            final Card card2 = new Card(CardType.HEART_ACE);

            final CardGroup cardGroup = new CardGroup();
            cardGroup.addCard(card);
            cardGroup.addCard(card2);

            assertThat(cardGroup.countCards()).isEqualTo(2);
        }

        @DisplayName("다이아몬드2 + 하트3 = 5점이다")
        @Test
        void 카드_점수를_계산한다1() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND_2),
                    new Card(CardType.HEART_3));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(5);
        }

        @DisplayName("다이아3 + 하트4 = 7점")
        @Test
        void 카드_점수를_계산한다2() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND_3),
                    new Card(CardType.HEART_4));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(7);
        }

        @DisplayName("다이아J + 하트A = 21점")
        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND_J),
                    new Card(CardType.HEART_ACE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(21);
        }

        @DisplayName("다이아J 하트J 하트A = 21점이다")
        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다2() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND_J),
                    new Card(CardType.HEART_J),
                    new Card(CardType.HEART_ACE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(21);
        }

        @DisplayName("다이아J 클로버A 하트A = 12점이다")
        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다3() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND_J),
                    new Card(CardType.CLOVER_ACE),
                    new Card(CardType.HEART_ACE));

            //whenR
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(12);
        }
    }
}
