package blackjack.domain.gamer;

import blackjack.domain.card.CardNumber;
import blackjack.domain.deck.Deck;
import blackjack.fixture.DeckFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest
    @CsvSource({
            "JACK,QUEEN,KING,false",
            "TWO,JACK,KING,false",
            "NINE,JACK,KING,false",
    })
    @DisplayName("버스트되었을 경우 카드를 추가로 지급받을 수 없다")
    void canReceiveAdditionalCards1(CardNumber cardNumber1, CardNumber cardNumber2,
                                    CardNumber cardNumber3, boolean expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2, cardNumber3);
        Player player = new Player("Pobi", deck.draw(), deck.draw(), deck.draw());

        // when
        boolean actual = player.canReceiveAdditionalCards();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "ACE,ACE,true",
            "ACE,JACK,true",
            "JACK,KING,true",
    })
    @DisplayName("버스트되지 않았을 경우 카드를 추가로 지급받을 수 있다")
    void canReceiveAdditionalCards2(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        Deck deck = DeckFixture.deckOf(cardNumber1, cardNumber2);
        Player player = new Player("Pobi", deck.draw(), deck.draw());

        // when
        boolean actual = player.canReceiveAdditionalCards();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
