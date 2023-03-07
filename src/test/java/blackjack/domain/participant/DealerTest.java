package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    @DisplayName("딜러는 받은 카드를 자신의 패에 추가한다.")
    void addCardInHand() {
        Dealer dealer = new Dealer();

        dealer.addCard(Card.of(Suit.CLOVER, Denomination.ACE));
        dealer.addCard(Card.of(Suit.DIAMOND, Denomination.TEN));

        assertThat(dealer.getHand().getCount()).isEqualTo(2);
    }

    @ParameterizedTest
    @MethodSource("generateHand")
    @DisplayName("참여자는 자신의 카드 점수를 계산한다.")
    void calculateHand(List<Card> Hand, int expected) {
        Dealer dealer = new Dealer();
        for (Card card : Hand) {
            dealer.addCard(card);
        }

        Score score = dealer.calculateScore();
        Score expectedScore = new Score(expected);

        assertThat(score).isEqualTo(expectedScore);
    }

    static Stream<Arguments> generateHand() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.CLOVER, Denomination.TWO)), 9),
                Arguments.of(List.of(Card.of(Suit.HEART, Denomination.NINE), Card.of(Suit.HEART, Denomination.TWO)), 11),
                Arguments.of(List.of(Card.of(Suit.DIAMOND, Denomination.TEN), Card.of(Suit.SPADE, Denomination.TEN)), 20),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.THREE), Card.of(Suit.CLOVER, Denomination.TWO)), 5),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.SEVEN), Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.DIAMOND, Denomination.SEVEN)), 21),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.TEN), Card.of(Suit.SPADE, Denomination.ACE), Card.of(Suit.DIAMOND, Denomination.SEVEN)), 18)
        );
    }

    @Test
    @DisplayName("딜러는 처음 받은 2장 중 첫장의 카드를 공개한다")
    void getFirstCard() {
        Dealer dealer = new Dealer();
        Card firstCard = Card.of(Suit.CLOVER, Denomination.ACE);
        Card secondCard = Card.of(Suit.DIAMOND, Denomination.TEN);
        dealer.addCard(firstCard);
        dealer.addCard(secondCard);

        Card dealerFirstCard = dealer.getFirst();

        assertThat(dealerFirstCard).isSameAs(firstCard);
    }

    @ParameterizedTest
    @MethodSource("generateHandAndFlag")
    @DisplayName("카드를 더 받을 수 있는 여부를 반환한다")
    void getParticipantIntention(List<Card> Hand, boolean expectedFlag) {
        Dealer dealer = new Dealer();
        for (Card card : Hand) {
            dealer.addCard(card);
        }

        assertThat(dealer.canReceive()).isEqualTo(expectedFlag);
    }

    static Stream<Arguments> generateHandAndFlag() {
        return Stream.of(
                Arguments.of(List.of(Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.CLOVER, Denomination.TWO)), true),
                Arguments.of(List.of(Card.of(Suit.HEART, Denomination.NINE), Card.of(Suit.HEART, Denomination.TWO)), true),
                Arguments.of(List.of(Card.of(Suit.DIAMOND, Denomination.TEN), Card.of(Suit.SPADE, Denomination.TEN)), false),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.THREE), Card.of(Suit.CLOVER, Denomination.TWO), Card.of(Suit.SPADE, Denomination.TWO), Card.of(Suit.HEART, Denomination.TWO)), false),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.SEVEN), Card.of(Suit.SPADE, Denomination.SEVEN), Card.of(Suit.DIAMOND, Denomination.SEVEN)), false),
                Arguments.of(List.of(Card.of(Suit.CLOVER, Denomination.TEN), Card.of(Suit.SPADE, Denomination.TEN), Card.of(Suit.DIAMOND, Denomination.SEVEN)), false)
        );
    }
}
