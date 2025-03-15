package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.CardScore;
import java.util.List;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CardGroupTest {

    @Nested
    class CardGroupConstructor {
        @Test
        void 카드_그룹을_생성한다() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND, CardScore.ACE),
                    new Card(CardType.HEART, CardScore.EIGHT));

            //when
            final CardGroup cardGroup = new CardGroup(cards);

            //then
            assertThat(cardGroup).isInstanceOf(CardGroup.class);
        }



        @Test
        void 카드_점수를_계산한다1() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND, CardScore.TWO),
                    new Card(CardType.HEART, CardScore.THREE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(5);
        }

        @Test
        void 카드_점수를_계산한다2() {
            //given
            final List<Card> cards = List.of(new Card(CardType.DIAMOND, CardScore.THREE),
                    new Card(CardType.HEART, CardScore.FOUR));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(7);
        }

        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND, CardScore.JACK),
                    new Card(CardType.HEART, CardScore.ACE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(21);
        }

        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다2() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND, CardScore.JACK),
                    new Card(CardType.HEART, CardScore.JACK),
                    new Card(CardType.HEART, CardScore.ACE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(21);
        }

        @Test
        void 카드에_에이스가_포함되어_있을때_점수를_계산한다3() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND, CardScore.JACK),
                    new Card(CardType.CLOVER, CardScore.ACE),
                    new Card(CardType.HEART, CardScore.ACE));

            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final int score = cardGroup.calculateScore();

            //then
            assertThat(score).isEqualTo(12);
        }
        
        @Test
        void 블랙잭인지_판단_한다() {
            //given
            final List<Card> cards = List.of(
                    new Card(CardType.DIAMOND, CardScore.ACE),
                    new Card(CardType.HEART, CardScore.JACK)
            );
            
            //when
            final CardGroup cardGroup = new CardGroup(cards);
            final boolean result = cardGroup.isBlackjack();

            //then
            assertThat(result).isTrue();
        }
    }
}
