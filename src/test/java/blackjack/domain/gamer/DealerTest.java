package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;

class DealerTest {

    @ParameterizedTest
    @CsvSource({
        "ACE,TWO,true",
        "TWO,JACK,true",
        "SIX,JACK,true",
    })
    @DisplayName("딜러는 카드 숫자 합이 16 이하이면 카드를 추가로 받을 수 있다")
    void canReceiveAdditionalCardsTest1(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.CLOVER, cardNumber2);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
        "SEVEN,JACK,false",
        "ACE,JACK,false",
        "QUEEN,KING,false",
    })
    @DisplayName("딜러는 카드 숫자 합이 16 초과면 카드를 추가로 받을 수 없다")
    void canReceiveAdditionalCardsTest2(CardNumber cardNumber1, CardNumber cardNumber2, boolean expected) {
        Card card1 = new Card(CardType.CLOVER, cardNumber1);
        Card card2 = new Card(CardType.CLOVER, cardNumber2);
        Dealer dealer = new Dealer();
        dealer.addCard(card1);
        dealer.addCard(card2);

        assertThat(dealer.canReceiveAdditionalCards()).isEqualTo(expected);
    }
}
