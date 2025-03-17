package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.game.BetAmount;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JudgeTest {

    private PlayerResults playerResults;

    @BeforeEach
    void setUp() {
        playerResults = new PlayerResults();
    }

    @Test
    void 둘_다_블랙잭인_경우_무승부를_저장한다() {
        Hand playerHand = createHandWithCards(CardRank.ACE, CardRank.TEN);
        Hand dealerHand = createHandWithCards(CardRank.ACE, CardRank.TEN);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isTie());
    }

    @Test
    void 딜러만_블랙잭인_경우_딜러는_이긴다() {
        Hand playerHand = createHandWithCards(CardRank.TWO, CardRank.TEN); // 플레이어 일반 패
        Hand dealerHand = createHandWithCards(CardRank.ACE, CardRank.TEN); // 딜러 블랙잭

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isLose());
    }

    @Test
    void 플레이어만_블랙잭인_경우_플레이어는_이긴다() {
        Hand dealerHand = createHandWithCards(CardRank.TWO, CardRank.TEN); // 딜러 일반
        Hand playerHand = createHandWithCards(CardRank.ACE, CardRank.TEN); // 플레이어 블랙잭

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isWinByBlackjack());
    }

    @Test
    void 둘_다_버스트_된_경우_무승부를_저장한다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isTie());
    }

    @Test
    void 플레이어만_버스트인_경우_딜러는_이긴다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.TEN);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isLose());
    }

    @Test
    void 딜러만_버스트인_경우_플레이어는_이긴다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.TEN, CardRank.TEN);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.TEN);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isWinByNotBlackjack());
    }

    @Test
    void 딜러의_결과가_더_클_경우_딜러는_이긴다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isLose());
    }

    @Test
    void 플레이어의_결과가_더_클_경우_플레이어는_이긴다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isWinByNotBlackjack());
    }

    @Test
    void 딜러와_플레이어의_결과가_같을_경우_무승부로_저장된다() {
        Hand dealerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);
        Hand playerHand = createHandWithCards(CardRank.TEN, CardRank.FIVE, CardRank.TWO);

        Player player = new Player("히로", playerHand, new BetAmount(1_000));
        Dealer dealer = new Dealer(dealerHand);

        Judge judge = new Judge(playerResults, dealer);
        judge.calculateAllResults(dealer, new Players(List.of(player)));

        PlayerResult result = playerResults.findResultByPlayer(player);

        Assertions.assertTrue(result.isTie());
    }

    private Hand createHandWithCards(CardRank... ranks) {
        Hand hand = new Hand();
        CardSuit[] suits = CardSuit.values();

        for (int i = 0; i < ranks.length; i++) {
            hand.takeCard(new Card(suits[i % suits.length], ranks[i]));
        }
        return hand;
    }
}
