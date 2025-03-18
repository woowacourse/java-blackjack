package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.RoundResult;
import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.fixture.BlackjackControllerFixture;
import blackjack.fixture.DeckFixture;

class GamerTest {

    private Gamer gamer = new Player("Pobi");
    ;

    @ParameterizedTest
    @CsvSource({
        "ACE,TWO,false",
        "ACE,ACE,false",
        "ACE,JACK,false",
    })
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void isBustTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        BlackjackControllerFixture.BlackjackControllerWith(
            DeckFixture.deckOf(cardNumber1, cardNumber2)
        ).drawStartingCards(gamer);

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
        BlackjackControllerFixture.BlackjackControllerWith(deck).drawStartingCards(gamer);
        gamer.drawCard(deck);

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
        BlackjackControllerFixture.BlackjackControllerWith(
            DeckFixture.deckOf(cardNumber1, cardNumber2)
        ).drawStartingCards(gamer);

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
        BlackjackControllerFixture.BlackjackControllerWith(deck).drawStartingCards(gamer);
        gamer.drawCard(deck);

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
        BlackjackControllerFixture.BlackjackControllerWith(
            DeckFixture.deckOf(cardNumber1, cardNumber2)
        ).drawStartingCards(gamer);

        // when
        boolean actual = gamer.isBlackjack();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("최종 승패를 계산한다")
    void getFinalResultTest() {
        // given
        Gamer dealer = new Dealer();
        Player a = new Player("aa");
        Player b = new Player("bb");
        Player c = new Player("cc");

        BlackjackControllerFixture.BlackjackControllerWith(DeckFixture.deckOf(CardNumber.ACE, CardNumber.SIX)).drawStartingCards(dealer);
        BlackjackControllerFixture.BlackjackControllerWith(DeckFixture.deckOf(CardNumber.ACE, CardNumber.JACK)).drawStartingCards(a);
        BlackjackControllerFixture.BlackjackControllerWith(DeckFixture.deckOf(CardNumber.ACE, CardNumber.TWO)).drawStartingCards(b);
        BlackjackControllerFixture.BlackjackControllerWith(DeckFixture.deckOf(CardNumber.ACE, CardNumber.THREE)).drawStartingCards(c);

        List<Gamer> players = List.of(a, b, c);

        // when
        Map<RoundResult, Integer> finalResult = dealer.getFinalResult(players);

        // then
        assertThat(finalResult.getOrDefault(RoundResult.WIN, 0)).isEqualTo(2);
        assertThat(finalResult.getOrDefault(RoundResult.LOSE, 0)).isEqualTo(1);
        assertThat(finalResult.getOrDefault(RoundResult.TIE, 0)).isEqualTo(0);
    }
}
