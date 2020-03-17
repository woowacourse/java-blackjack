package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
    private static Stream<Arguments> generateStandardDealer() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        new Card(Suit.DIAMOND, Symbol.SIX),
                        new Card(Suit.HEART, Symbol.KING)
                ), true),
                Arguments.of(Arrays.asList(
                        new Card(Suit.CLUB, Symbol.JACK),
                        new Card(Suit.SPADE, Symbol.SEVEN)
                ), false)
        );
    }

    @Test
    @DisplayName("딜러의 이름이 \"딜러\"인지 확인")
    void checkDealerName() {
        assertThat(new Dealer().getName()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("딜러가 카드를 하나만 표시하는지 확인")
    void displayDealerCardInfo() {
        Card card1 = new Card(Suit.SPADE, Symbol.JACK);
        Card card2 = new Card(Suit.DIAMOND, Symbol.THREE);
        List<Card> cards = Arrays.asList(card1, card2);
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(cards);
        assertThat(dealer.getInitialCards()).containsExactly(card1);
    }

    @ParameterizedTest
    @DisplayName("딜러의 카드 합이 16 이하인지 확인")
    @MethodSource("generateStandardDealer")
    void scoreUnderSixteenTest(List<Card> userCards, boolean expected) {
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(userCards);
        assertThat(dealer.isUnderThreshold()).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 카드 합이 21을 초과하면 BUSTED로 표시되는지 확인")
    void totalScoreOver21Busted() {
        Dealer dealer = new Dealer();
        dealer.receiveInitialCards(new ArrayList<>(Arrays.asList(
                new Card(Suit.CLUB, Symbol.JACK),
                new Card(Suit.SPADE, Symbol.SIX))));
        dealer.receiveCard(new Card(Suit.DIAMOND, Symbol.QUEEN));
        assertThat(dealer.isBusted()).isTrue();
    }
}
