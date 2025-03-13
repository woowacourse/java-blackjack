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
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer(blackjackResultEvaluator);
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
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer(blackjackResultEvaluator);
        List<Card> dealerCards = dealer.getCards();
        dealerCards.addAll(cards);

        //when
        boolean actual = dealer.isUnderDrawBound();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 딜러와_플레이어들_간의_게임_결과를_판정한다() {
        //given
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer(blackjackResultEvaluator);
        List<Card> dealerCards = dealer.getCards();
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.TEN));
        dealerCards.add(new Card(Pattern.SPADE, CardNumber.SEVEN));

        //when
        Player player = new Player("pobi", 1000);
        List<Card> playerCards = player.getCards();
        playerCards.add(new Card(Pattern.DIAMOND, CardNumber.KING));
        playerCards.add(new Card(Pattern.DIAMOND, CardNumber.ACE));

        Player player2 = new Player("brown", 1000);
        List<Card> playerCards2 = player2.getCards();
        playerCards2.add(new Card(Pattern.DIAMOND, CardNumber.QUEEN));
        playerCards2.add(new Card(Pattern.DIAMOND, CardNumber.EIGHT));

        Player player3 = new Player("flint", 1000);
        List<Card> playerCards3 = player3.getCards();
        playerCards3.add(new Card(Pattern.DIAMOND, CardNumber.JACK));
        playerCards3.add(new Card(Pattern.DIAMOND, CardNumber.SEVEN));

        Player player4 = new Player("font", 1000);
        List<Card> playerCards4 = player4.getCards();
        playerCards4.add(new Card(Pattern.DIAMOND, CardNumber.TEN));
        playerCards4.add(new Card(Pattern.DIAMOND, CardNumber.SIX));

        List<Player> players = List.of(player, player2, player3, player4);

        blackjackResultEvaluator.judgeGameResult(dealer, players);

        //then
        Assertions.assertThat(dealer.getTotalProfit()).isEqualTo(-1500);
    }
}
