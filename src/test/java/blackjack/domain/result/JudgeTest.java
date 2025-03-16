package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;

public class JudgeTest {

    @Test
    void 둘_다_블랙잭인_경우_무승부를_저장한다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand dealerHand = new Hand();
        dealerHand.takeCard(card1);
        dealerHand.takeCard(card2);

        Dealer dealer = new Dealer(dealerHand);

        Card card3 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card4 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand playerHand = new Hand();
        playerHand.takeCard(card3);
        playerHand.takeCard(card4);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        Judge judge = new Judge(new DealerResults(), new PlayerResults());

        // when
        judge.calculateResult(dealer, player);

        // then
        DealerResults dealerResults = judge.getDealerResults();
        PlayerResults playerResults = judge.getPlayerResults();

        DealerResult dealerResult = dealerResults.findResultByPlayer(player);
        PlayerResult playerResult = playerResults.findResultByPlayer(player);

        assertAll(
                () -> assertThat(dealerResult.getGameResultType())
                        .isEqualTo(GameResultType.TIE),
                () -> assertThat(playerResult.getGameResultType())
                        .isEqualTo(GameResultType.TIE)
        );
    }

    @Test
    void 딜러만_블랙잭인_경우_딜러는_이긴다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card2 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand dealerHand = new Hand();
        dealerHand.takeCard(card1);
        dealerHand.takeCard(card2);

        Dealer dealer = new Dealer(dealerHand);

        Card card3 = new Card(CardSuit.CLUB, CardRank.TWO);
        Card card4 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand playerHand = new Hand();
        playerHand.takeCard(card3);
        playerHand.takeCard(card4);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        Judge judge = new Judge(new DealerResults(), new PlayerResults());

        // when
        judge.calculateResult(dealer, player);

        // then
        DealerResults dealerResults = judge.getDealerResults();
        PlayerResults playerResults = judge.getPlayerResults();

        DealerResult dealerResult = dealerResults.findResultByPlayer(player);
        PlayerResult playerResult = playerResults.findResultByPlayer(player);

        assertAll(
                () -> assertThat(dealerResult.getGameResultType())
                        .isEqualTo(GameResultType.WIN),
                () -> assertThat(playerResult.getGameResultType())
                        .isEqualTo(GameResultType.LOSE)
        );
    }

    @Test
    void 플레이어만_블랙잭인_경우_플레이어는_이긴다() {
        // given
        Card card1 = new Card(CardSuit.CLUB, CardRank.TWO);
        Card card2 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand dealerHand = new Hand();
        dealerHand.takeCard(card1);
        dealerHand.takeCard(card2);

        Dealer dealer = new Dealer(dealerHand);

        Card card3 = new Card(CardSuit.CLUB, CardRank.ACE);
        Card card4 = new Card(CardSuit.HEART, CardRank.TEN);
        Hand playerHand = new Hand();
        playerHand.takeCard(card3);
        playerHand.takeCard(card4);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        Judge judge = new Judge(new DealerResults(), new PlayerResults());

        // when
        judge.calculateResult(dealer, player);

        // then
        DealerResults dealerResults = judge.getDealerResults();
        PlayerResults playerResults = judge.getPlayerResults();

        DealerResult dealerResult = dealerResults.findResultByPlayer(player);
        PlayerResult playerResult = playerResults.findResultByPlayer(player);

        assertAll(
                () -> assertThat(dealerResult.getGameResultType())
                        .isEqualTo(GameResultType.LOSE),
                () -> assertThat(playerResult.getGameResultType())
                        .isEqualTo(GameResultType.WIN)
        );
    }
}
