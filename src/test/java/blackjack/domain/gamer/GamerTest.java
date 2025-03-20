package blackjack.domain.gamer;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {

    @ParameterizedTest
    @CsvSource({
            "ACE,TWO,false",
            "ACE,ACE,false",
            "ACE,JACK,false",
    })
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void isBustTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Gamer gamer = new Player("Pobi", deck.draw(), deck.draw());

        // when
        boolean actual = gamer.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "FIVE,JACK,KING,true",
            "TWO,JACK,QUEEN,true",
            "JACK,QUEEN,KING,true",
    })
    @DisplayName("21초과인 경우 버스트된다")
    void isBustTest2(CardNumber cardNumber1, CardNumber cardNumber2,
                     CardNumber cardNumber3, boolean expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2, cardNumber3);
        Gamer gamer = new Player("Pobi", deck.draw(), deck.draw(), deck.draw());

        // when
        boolean actual = gamer.isBust();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,ACE,12",
            "ACE,TWO,13",
            "ACE,KING,21",
    })
    @DisplayName("버스트되지 않으면 Ace는 11로 계산한다")
    void getSumOfCardsTest1(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Gamer gamer = new Player("Pobi", deck.draw(), deck.draw());

        // when
        int sumOfCards = gamer.getSumOfCards();

        // then
        assertThat(sumOfCards).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,ACE,ACE,13",
            "ACE,ACE,KING,12",
            "ACE,QUEEN,KING,21",
    })
    @DisplayName("버스트될 것 같으면 Ace는 1로 계산한다")
    void getSumOfCardsTest2(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3, int expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2, cardNumber3);
        Gamer gamer = new Player("Pobi", deck.draw(), deck.draw(), deck.draw());

        // when
        int sumOfCards = gamer.getSumOfCards();

        // then
        assertThat(sumOfCards).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,KING,true",
            "ACE,TWO,false",
            "ACE,ACE,false",
    })
    @DisplayName("블랙잭 여부를 확인한다")
    void isBlackjackTest(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Gamer gamer = new Player("Pobi", deck.draw(), deck.draw());

        // when
        boolean actual = gamer.isBlackjack();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
