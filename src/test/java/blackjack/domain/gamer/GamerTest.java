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
import blackjack.fixture.DeckFixture;

class GamerTest {

    @ParameterizedTest
    @CsvSource({
        "ACE,TWO,false",
        "ACE,ACE,false",
        "ACE,JACK,false",
    })
    @DisplayName("21이하인 경우 버스트되지 않는다")
    void isBustTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Gamer gamer = new Player("Pobi");
        gamer.initialize(DeckFixture.deckOf(cardNumber1, cardNumber2));
        assertThat(gamer.isBust()).isEqualTo(expected);
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
        Gamer gamer = new Player("Pobi");
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2, cardNumber3);
        gamer.initialize(deck);
        gamer.drawCard(deck);
        assertThat(gamer.isBust()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,ACE,12",
        "ACE,TWO,13",
        "ACE,KING,21",
    })
    @DisplayName("버스트되지 않으면 Ace는 11로 계산한다")
    void getSumOfCardsTest1(CardNumber cardNumber1, CardNumber cardNumber2, int expected) {
        Gamer gamer = new Dealer();
        gamer.initialize(DeckFixture.deckOf(cardNumber1, cardNumber2));
        assertThat(gamer.getSumOfCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,ACE,ACE,13",
        "ACE,ACE,KING,12",
        "ACE,QUEEN,KING,21",
    })
    @DisplayName("버스트될 것 같으면 Ace는 1로 계산한다")
    void getSumOfCardsTest2(CardNumber cardNumber1, CardNumber cardNumber2, CardNumber cardNumber3, int expected) {
        Gamer gamer = new Dealer();
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2, cardNumber3);
        gamer.initialize(deck);
        gamer.drawCard(deck);

        assertThat(gamer.getSumOfCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "ACE,KING,true",
        "ACE,TWO,false",
        "ACE,ACE,false",
    })
    @DisplayName("블랙잭 여부를 확인한다")
    void isBlackjackTest(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Gamer gamer = new Player("Pobi");
        gamer.initialize(DeckFixture.deckOf(cardNumber1, cardNumber2));
        assertThat(gamer .isBlackjack()).isEqualTo(expected);
    }

    // TODO Fixture 생성 후 진행
    @Test
    @DisplayName("")
    void getFinalResultTest() {
        Gamer dealer = new Dealer();
        Player a = new Player("aa");
        Player b = new Player("bb");
        Player c = new Player("cc");

        Deck dealerDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.SIX);
        Deck aDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.JACK);
        Deck bDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.TWO);
        Deck cDeck = DeckFixture.deckOf(CardNumber.ACE, CardNumber.THREE);

        dealer.drawCard(dealerDeck);
        dealer.drawCard(dealerDeck);
        a.drawCard(aDeck);
        a.drawCard(aDeck);
        b.drawCard(bDeck);
        b.drawCard(bDeck);
        c.drawCard(cDeck);
        c.drawCard(cDeck);

        List<Gamer> players = List.of(a, b, c);
        Map<RoundResult, Integer> finalResult = dealer.getFinalResult(players);
        assertThat(finalResult.getOrDefault(RoundResult.WIN, 0)).isEqualTo(2);
        assertThat(finalResult.getOrDefault(RoundResult.LOSE, 0)).isEqualTo(1);
        assertThat(finalResult.getOrDefault(RoundResult.TIE, 0)).isEqualTo(0);
    }
}
