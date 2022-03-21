package blackjack.domain.participant;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.FIVE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.SEVEN;
import static blackjack.domain.card.CardNumber.SIX;
import static blackjack.domain.card.CardNumber.TEN;
import static blackjack.domain.card.CardSymbol.CLUB;
import static blackjack.domain.card.CardSymbol.DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @ParameterizedTest
    @DisplayName("딜러가 추가로 카드를 뽑아야하는지 확인한다.")
    @MethodSource("provideDeck")
    void canDrawCard(Deck deck, boolean expected) {
        // give
        final Dealer dealer = new Dealer();
        dealer.initCards(deck);

        // when
        final boolean actual = dealer.isDrawable();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDeck() {
        return Stream.of(
                Arguments.of(Deck.from(() -> List.of(Card.of(DIAMOND, TEN), Card.of(DIAMOND, SEVEN))), false),
                Arguments.of(Deck.from(() -> List.of(Card.of(DIAMOND, ACE), Card.of(DIAMOND, SIX))), false),
                Arguments.of(Deck.from(() -> List.of(Card.of(DIAMOND, TEN), Card.of(DIAMOND, SIX))), true),
                Arguments.of(Deck.from(() -> List.of(Card.of(DIAMOND, ACE), Card.of(DIAMOND, FIVE))), true)
        );
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        final Card firstCard = Card.of(CLUB, KING);
        final Deck deck = Deck.from(() -> List.of(firstCard, Card.of(DIAMOND, QUEEN)));

        final Dealer dealer = new Dealer();
        dealer.initCards(deck);

        // when
        final Card actual = dealer.openFirstCard();

        // then
        assertThat(actual).isEqualTo(firstCard);
    }
}