package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardSuit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand();
    }

    @Test
    @DisplayName("Hand가 잘 생성되었는지 확인")
    void constructorTest() {
        assertThatCode(() -> new Hand())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Hand에 새로운 카드가 추가되는지 확인")
    void addTest() {
        hand.add(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
        assertThat(hand.getCards()).hasSize(1);
    }

    @Test
    @DisplayName("Hand에 BUST_LIMIT을 넘어가도록 카드가 추가되면 isBust에 대해 참을 반환")
    void isBustTest() {
        hand.add(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
        hand.add(new Card(CardLetter.TEN, CardSuit.CLOVER));
        assertThat(hand.isBust()).isFalse();

        hand.add(new Card(CardLetter.QUEEN, CardSuit.CLOVER));
        assertThat(hand.isBust()).isTrue();
    }

    @Test
    @DisplayName("Hand에 추가된 카드들의 합을 확인")
    void calculateScoreTest() {
        hand.add(new Card(CardLetter.EIGHT, CardSuit.CLOVER));
        hand.add(new Card(CardLetter.SEVEN, CardSuit.CLOVER));
        assertThat(hand.calculateScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("에이스 카드가 하나있을 때 합 구하기")
    void calculateMyCardSumWhenAceIsOne() {
        hand.add(new Card(CardLetter.JACK, CardSuit.CLOVER));
        hand.add(new Card(CardLetter.FIVE, CardSuit.CLOVER));
        hand.add(new Card(CardLetter.ACE, CardSuit.CLOVER));
        Assertions.assertThat(hand.calculateScore()).isEqualTo(16);
    }

    @ParameterizedTest
    @CsvSource(value = {"ACE,ACE:12", "ACE,ACE,ACE:13", "ACE,ACE,TEN:12", "ACE,ACE,ACE,JACK,QUEEN:23"}, delimiter = ':')
    @DisplayName("여러개의 에이스 카드가 추가되었을 때 알맞은 합 구하기")
    void calculateScoreWithAceTest(final String cards, final int expectedScore) {
        final String[] cardNumbers = cards.split(",");
        for (final String number : cardNumbers) {
            final CardLetter cardLetter = CardLetter.valueOf(number);
            hand.add(new Card(cardLetter, CardSuit.CLOVER));
        }
        assertThat(hand.calculateScore()).isEqualTo(expectedScore);
    }
}

