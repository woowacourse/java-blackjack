package blackjack.domain.participant;

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
    @DisplayName("딜러가 카드를 뽑아야하는지 확인한다.")
    @MethodSource("provideCardFactory")
    void canDrawCard(Deck deck, boolean expected) {
        // give
        final Dealer dealer = new Dealer();
        dealer.initCards(deck);

        // when
        final boolean actual = dealer.canDrawCard();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCardFactory() {
        return Stream.of(
                Arguments.of(Deck.createBy(List.of(Card.of(DIAMOND, TEN), Card.of(DIAMOND, SEVEN))), false),
                Arguments.of(Deck.createBy(List.of(Card.of(DIAMOND, TEN), Card.of(DIAMOND, SIX))), true)
        );
    }

    @Test
    @DisplayName("처음 받은 카드 중에 한 장의 카드를 공개한다.")
    void openCard() {
        // give
        final Card firstCard = Card.of(CLUB, KING);
        final Card secondCard = Card.of(DIAMOND, QUEEN);

        final Deck deck = Deck.createBy(List.of(secondCard, firstCard));

        final Dealer dealer = new Dealer();
        dealer.initCards(deck);

        // when
        final Card actual = dealer.openFirstCard();

        // then
        assertThat(actual).isEqualTo(firstCard);
    }
}