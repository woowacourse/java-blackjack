package team.blackjack.service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import team.blackjack.domain.Card;
import team.blackjack.domain.rule.BlackjackRule;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.PayoutResult;
import team.blackjack.service.dto.PlayerRequest;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.domain.BlackjackGame;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;

public class BlackJackService {
    private BlackjackGame blackjackGame;
    private BlackjackRule blackjackRule;

    public BlackJackService(BlackjackRule blackjackRule){
        this.blackjackRule = blackjackRule;
    }

    public void initGame(List<PlayerRequest> playerRequests) {
        this.blackjackGame = new BlackjackGame(playerRequests);
    }

    /**
     * 각 게임(라운드)에 앞서 기본 카드를 발급하는 로직
     */
    public void dealInitialCards() {
        for (Player player : blackjackGame.getPlayers()) {
            blackjackGame.dealInitialCardsTo(player);
        }

        final Dealer dealer = blackjackGame.getDealer();
        blackjackGame.dealInitialCardsTo(dealer);
    }

    public DrawResult getHandResult() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        final List<Card> cards = blackjackGame.getDealer().getHand().getCards();
        final Map<String, List<String>> playerCards = blackjackGame.getAllPlayerCards();

        return new DrawResult(playerNames, cards.getFirst().getCardName(), playerCards);
    }

    public List<Player> getPlayer() {
        return this.blackjackGame.getPlayers();
    }

    public void playerHit(Player player) {
        blackjackGame.dealCardTo(player);
    }

    public void dealerHit() {
        final Dealer dealer = blackjackGame.getDealer();
        blackjackGame.dealCardTo(dealer);
    }

    public boolean shouldDealerHit() {
        return blackjackGame.getDealer().shouldHit();
    }

    public ScoreResult calculateAllParticipantScore() {
        final List<String> playerNames = blackjackGame.getPlayerNames();
        final Map<String, List<String>> playerCards = blackjackGame.getAllPlayerCards();
        final Map<String, Integer> playerScores = blackjackGame.getAllPlayerScores();
        final List<String> dealerCards = blackjackGame.getDealerCards();
        final int dealerScore = blackjackGame.getDealerScore();

        return new ScoreResult(
                dealerCards,
                dealerScore,
                playerNames,
                playerCards,
                playerScores
        );
    }

    public PayoutResult getPayoutResult() {
        Map<String, BigDecimal> playerPayouts = blackjackGame.calculatePlayersPayout(blackjackRule);
        BigDecimal dealerPayout = blackjackGame.calculateDealerPayout(playerPayouts);

        return new PayoutResult(parseDealerPayouts(dealerPayout), parsePlayerPayouts(playerPayouts));
    }

    private Map<String, String> parsePlayerPayouts(Map<String, BigDecimal> playerPayouts) {
        Map<String, String> playerPayoutResults = new LinkedHashMap<>();

        playerPayouts.forEach((name, payout)
                -> playerPayoutResults.put(name, payout.stripTrailingZeros().toPlainString()));

        return playerPayoutResults;
    }

    private String parseDealerPayouts(BigDecimal dealerPayout) {
        return dealerPayout.stripTrailingZeros().toPlainString();
    }
}
