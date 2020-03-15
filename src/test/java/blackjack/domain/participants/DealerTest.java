package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static blackjack.domain.participants.HandTest.CARDS_21_ACE_AS_ONE;
import static blackjack.domain.participants.HandTest.CARDS_8;
import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    public static final int INITIAL_CARD_COUNT = 2;

    @DisplayName("dealer 생성자 테스트")
    @Test
    void dealerValidConstructorTest() {
        assertThat(new Dealer()).isNotNull();
        assertThat(new Dealer().getName()).isEqualTo("딜러");
    }

    @DisplayName("drawMoreCard()가 카드패 합이 16 이하일때만 동작하는지 테스트")
    @ParameterizedTest
    @MethodSource("drawMoreCardArguments")
    void drawMoreTest(List<Card> cards, boolean expected) {
        Dealer dealer = new Dealer();
        for (Card card : cards) {
            dealer.draw(card);
        }
        dealer.drawMoreCard(new Deck());
        int willNotBeZeroIfDealerNeedsMoreCard = cards.size() - INITIAL_CARD_COUNT;
        boolean actual = dealer.addedCardCount() != willNotBeZeroIfDealerNeedsMoreCard;
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> drawMoreCardArguments() {
        return Stream.of(
            Arguments.of(CARDS_8, true),
            Arguments.of(CARDS_21_ACE_AS_ONE, false)
        );
    }
}
