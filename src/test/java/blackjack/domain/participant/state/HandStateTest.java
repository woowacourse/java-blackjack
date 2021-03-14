package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HandStateTest {
    private HandState handState;

    @BeforeEach
    void setUp() {
        handState = new InitialState();
    }

    @Test
    @DisplayName("Hand에 추가된 카드들의 합을 확인")
    void calculateScoreTest() {
        handState = handState.add(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
        handState = handState.add(new Card(CardLetter.SEVEN, CardSuit.CLOVER));
        assertThat(handState.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        handState = handState.add(new Card(CardLetter.JACK, CardSuit.CLOVER));
        handState = handState.add(new Card(CardLetter.FIVE, CardSuit.CLOVER));
        handState = handState.add(new Card(CardLetter.ACE, CardSuit.CLOVER));
        assertThat(handState.calculateScore()).isEqualTo(16);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12", "ACE,ACE,ACE,JACK,QUEEN:23"}, delimiter = ':')
    @DisplayName("여러개의 에이스 카드가 추가되었을 때 알맞은 합 구하기")
    void calculateScoreWithAceTest(final String cards, final int expectedScore) {
        final String[] cardNumbers = cards.split(",");
        for (final String number : cardNumbers) {
            final CardLetter cardLetter = CardLetter.valueOf(number);
            handState = handState.add(new Card(cardLetter, CardSuit.CLOVER));
        }
        assertThat(handState.calculateScore()).isEqualTo(expectedScore);
    }
}
