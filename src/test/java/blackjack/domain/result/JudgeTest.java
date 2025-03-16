package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JudgeTest {

    private Judge judge;
    private DealerResults dealerResults;
    private PlayerResults playerResults;

    @BeforeEach
    void setUp() {
        dealerResults = new DealerResults();
        playerResults = new PlayerResults();
        judge = new Judge(dealerResults, playerResults);
    }

    @Test
    void 둘_다_블랙잭인_경우_무승부를_저장한다() {
        testGameResult(
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 딜러 블랙잭
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 플레이어 블랙잭
                GameResultType.TIE,
                GameResultType.TIE
        );
    }

    @Test
    void 딜러만_블랙잭인_경우_딜러는_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 딜러 블랙잭
                createHandWithCards(CardRank.TWO, CardRank.TEN),  // 플레이어 일반
                GameResultType.WIN,
                GameResultType.LOSE
        );
    }

    @Test
    void 플레이어만_블랙잭인_경우_플레이어는_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.TWO, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.ACE, CardRank.TEN),  // 플레이어 블랙잭
                GameResultType.LOSE,
                GameResultType.WIN
        );
    }

    @Test
    void 둘_다_버스트_된_경우_무승부를_저장한다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 플레이어 블랙잭
                GameResultType.TIE,
                GameResultType.TIE
        );
    }

    @Test
    void 플레이어만_버스트인_경우_딜러는_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),
                GameResultType.WIN,
                GameResultType.LOSE
        );
    }

    @Test
    void 딜러만_버스트인_경우_플레이어는_이긴다() {
        testGameResult(
                createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN),  // 딜러 일반
                createHandWithCards(CardRank.TEN, CardRank.TEN),
                GameResultType.LOSE,
                GameResultType.WIN
        );
    }


    private Hand createHandWithCards(CardRank... ranks) {
        Hand hand = new Hand();
        CardSuit[] suits = CardSuit.values();

        for (int i = 0; i < ranks.length; i++) {
            hand.takeCard(new Card(suits[i % suits.length], ranks[i]));
        }
        return hand;
    }

    private void testGameResult(Hand dealerHand, Hand playerHand, GameResultType expectedDealerResult,
                                GameResultType expectedPlayerResult) {
        Dealer dealer = new Dealer(dealerHand);
        Player player = new Player("히로", playerHand, new BetAmount(1_000));

        judge.calculateResult(dealer, player);

        assertResults(player, expectedDealerResult, expectedPlayerResult);
    }

    private void assertResults(Player player, GameResultType expectedDealerResult,
                               GameResultType expectedPlayerResult) {
        assertAll(
                () -> assertThat(dealerResults.findResultByPlayer(player).getGameResultType())
                        .isEqualTo(expectedDealerResult),
                () -> assertThat(playerResults.findResultByPlayer(player).getGameResultType())
                        .isEqualTo(expectedPlayerResult)
        );
    }
}
