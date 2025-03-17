package game;

import static card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static card.CardDeck.DRAW_COUNT_WHEN_START;
import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardDeck;
import card.CardNumber;
import card.Pattern;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러는_게임_시작_시_두_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Dealer dealer = new Dealer();

        //when
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));

        //then
        assertThat(dealer.getHand().getCards()).hasSize(2);
    }

    @Test
    void 딜러는_게임_시작_시_한_장의_카드를_공개한다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Dealer dealer = new Dealer();
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_START));

        //when
        Card singleCard = dealer.getSingleCard();

        //then
        assertThat(singleCard.equals(new Card(Pattern.SPADE, CardNumber.KING))).isTrue();
    }

    @Test
    void 딜러는_한_장의_카드를_받는다() {
        //given
        CardDeck cardDeck = CardDeck.prepareDeck(ArrayList::new);
        Dealer dealer = new Dealer();

        //when
        dealer.draw(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));

        //then
        assertThat(dealer.getHand().getCards()).hasSize(1);
    }

    @Test
    void 딜러가_보유한_카드의_합계를_구한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TEN)
        ));

        // when
        int actual = dealer.calculateTotalPoints();

        //when & then
        assertThat(actual).isEqualTo(20);
    }

    @Test
    void 딜러가_보유한_카드의_합계가_21을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.TWO)
        ));

        //when & then
        assertThat(dealer.isBust()).isTrue();
    }

    @Test
    void 딜러가_보유한_카드의_합계가_16을_넘었는지_판정한다() {
        //given
        Dealer dealer = new Dealer();
        dealer.draw(List.of(
                new Card(Pattern.CLOVER, CardNumber.TEN),
                new Card(Pattern.SPADE, CardNumber.SEVEN)
        ));

        //when & then
        assertThat(dealer.isOverDrawBound()).isTrue();
    }

    @Test
    void 딜러의_수익을_계산한다() {
        //given
        Player player1 = new Player("a", 1000);
        Player player2 = new Player("b", 1000);
        Player player3 = new Player("c", 1000);
        Player player4 = new Player("d", 1000);
        List<Player> players = List.of(player1, player2, player3, player4);

        List<GameResult> gameResults = List.of(
                GameResult.LOSE, GameResult.DRAW, GameResult.WIN, GameResult.BLACKJACK);

        Profits profits = Profits.of(players, gameResults);

        //when
        int dealerProfit = profits.evaluateDealerProfit();

        //then
        assertThat(dealerProfit).isEqualTo(-1500);
    }

}
