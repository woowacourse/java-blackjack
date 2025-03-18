package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.CardNumber;
import blackjack.fixture.BlackjackControllerFixture;
import blackjack.fixture.DeckFixture;

class DealerTest {

    private Dealer dealer = new Dealer();

    @ParameterizedTest
    @CsvSource({
        "ACE,TWO,true",
        "TWO,JACK,true",
        "SIX,JACK,true",
    })
    @DisplayName("딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받을 수 있다")
    void canReceiveAdditionalCardsTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        BlackjackControllerFixture.BlackjackControllerWith(
            DeckFixture.deckOf(cardNumber1, cardNumber2)
        ).drawStartingCards(dealer);

        // when
        boolean actual = dealer.canReceiveAdditionalCards();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "SEVEN,JACK,false",
        "ACE,JACK,false",
        "QUEEN,KING,false",
    })
    @DisplayName("딜러는 카드 숫자 합이 16 초과면 카드를 추가로 받을 수 없다")
    void canReceiveAdditionalCardsTest2(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        // given
        BlackjackControllerFixture.BlackjackControllerWith(
            DeckFixture.deckOf(cardNumber1, cardNumber2)
        ).drawStartingCards(dealer);

        // when
        boolean actual = dealer.canReceiveAdditionalCards();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
