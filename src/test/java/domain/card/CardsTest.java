package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardsTest {
    @Test
    @DisplayName("카드 묶음 생성 테스트")
    void createCards() {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(Suit.CLUBS, Denomination.FIVE));
        cards.addCard(Card.of(Suit.HEARTS, Denomination.SEVEN));

        // when
        List<Card> rawCards = cards.getCards();

        // then
        assertThat(rawCards).contains(Card.of(Suit.CLUBS, Denomination.FIVE));
    }

    @ParameterizedTest(name = "{0}{1} + {2}{3} = {4}")
    @CsvSource(value = {
            "SPADES,ACE,CLUBS,ACE,12",
            "SPADES,ACE,CLUBS,TEN,21",
            "SPADES,ACE,CLUBS,NINE,20"})
    @DisplayName("점수 계산 테스트")
    void calculateScore(Suit suit, Denomination denomination,
                        Suit suit2, Denomination denomination2,
                        int expected) {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(suit, denomination));
        cards.addCard(Card.of(suit2, denomination2));

        // when
        int actual = cards.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}{1} + {2}{3} + {4}{5} = {6}")
    @CsvSource(value = {
            "SPADES,ACE,CLUBS,ACE,SPADES,TEN,false",
            "SPADES,TWO,CLUBS,TEN,SPADES,TEN,true",
            "SPADES,ACE,CLUBS,ACE,SPADES,ACE,false"})
    @DisplayName("버스트 여부 테스트")
    void isBust(Suit suit, Denomination denomination,
                Suit suit2, Denomination denomination2,
                Suit suit3, Denomination denomination3,
                boolean expected) {
        // given
        Cards cards = new Cards();
        cards.addCard(Card.of(suit, denomination));
        cards.addCard(Card.of(suit2, denomination2));
        cards.addCard(Card.of(suit3, denomination3));

        // when
        boolean actual = cards.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("블랙잭 여부 테스트")
    void isBlackjack() {
        // given
        Card aClubs = Card.of(Suit.CLUBS, Denomination.ACE);
        Card tenHearts = Card.of(Suit.HEARTS, Denomination.TEN);
        Card tenDiamonds = Card.of(Suit.DIAMONDS, Denomination.TEN);

        Cards blackjackCards = new Cards();
        blackjackCards.addCard(aClubs);
        blackjackCards.addCard(tenHearts);

        Cards notBlackjackCards = new Cards();
        notBlackjackCards.addCard(tenHearts);
        notBlackjackCards.addCard(tenDiamonds);
        notBlackjackCards.addCard(aClubs);

        // when
        boolean shouldBeBlackjack = blackjackCards.isBlackJack();
        boolean shouldNotBeBlackjack = notBlackjackCards.isBlackJack();

        // then
        assertAll(
                () -> assertThat(shouldBeBlackjack).isTrue(),
                () -> assertThat(shouldNotBeBlackjack).isFalse()
        );
    }
}
