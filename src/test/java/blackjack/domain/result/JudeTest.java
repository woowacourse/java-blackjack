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

public class JudeTest {
//    @Test
//    void 참가자들의_게임_결과를_저장한다() {
//        // given
//        ParticipantResults participantResults = new ParticipantResults();
//        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
//        Dealer dealer = new Dealer(new Hand());
//        GameRuleEvaluator gameRuleEvaluator = new GameRuleEvaluator();
//        Players players = new Players(List.of(player));
//        Judge judge = new Judge(participantResults);
//
//        // when
//        judge.calculateAllResults(dealer, players, gameRuleEvaluator);
//
//        // then
//        ParticipantResult result = participantResults.findResult(dealer);
//        assertThat(result.getCountsOfResultTypes().getOrDefault(GameResultType.TIE, 0)).isEqualTo(1);
//    }

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
}
