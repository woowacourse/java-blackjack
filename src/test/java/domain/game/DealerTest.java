package domain.game;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.Pattern;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    void 딜러는_하나의_카드를_공개한다() {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.ACE));

        //when
        Card card = dealer.openSingleCard();

        //then
        Card expected = new Card(Pattern.SPADE, CardNumber.TEN);
        assertThat(card).isEqualTo(expected);
    }

    private static Stream<Arguments> provideCard() {
        return Stream.of(
                Arguments.of(List.of(new Card(Pattern.SPADE, CardNumber.TEN),
                        new Card(Pattern.SPADE, CardNumber.SEVEN)), false),
                Arguments.of(List.of(new Card(Pattern.SPADE, CardNumber.TEN),
                        new Card(Pattern.SPADE, CardNumber.SIX)), true));
    }

    @ParameterizedTest
    @MethodSource("provideCard")
    void 딜러는_16_보다_작은_카드값을_가지는지_판정한다(List<Card> cards, boolean expected) {
        //given
        Dealer dealer = new Dealer();
        List<Card> dealerCards = dealer.getCards();
        dealerCards.addAll(cards);

        //when
        boolean actual = dealer.isUnderDrawBound();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 딜러와_플레이어들_간의_베팅_결과를_계산한다() {
        //given
        Dealer dealer = new Dealer();
        Chip playerChip = new Chip(1000);

        //when
        dealer.calculateDealerProfit(playerChip);

        //then
        Assertions.assertThat(dealer.getTotalProfit()).isEqualTo(-1000);
    }
}
