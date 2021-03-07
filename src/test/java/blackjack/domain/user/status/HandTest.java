package blackjack.domain.user.status;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.painting.Suit;
import blackjack.domain.card.painting.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setUp() {
        List<Card> cards = Arrays.asList(
                new Card(Suit.CLOVER, Symbol.JACK), new Card(Suit.DIAMOND, Symbol.FOUR),
                new Card(Suit.HEART, Symbol.ACE));
        hand = new Hand(new Cards(cards));
    }

    @DisplayName("손에 들고 있는 카드 목록을 구한다")
    @Test
    void testGetCards() {
        //given
        //when
        Cards cards = hand.getCards();
        //then
        List<Card> cardList = cards.stream()
                .collect(toList());
        assertThat(cardList).hasSize(3);
    }

}