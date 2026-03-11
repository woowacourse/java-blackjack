package domain.pariticipant;

import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;
import domain.result.DealerMatchResult;
import domain.result.MatchCase;
import domain.result.MatchResult;
import domain.result.PlayerMatchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constant.BlackjackConstant.DEALER_DRAW_BOUND;

public class Dealer extends Participant {
    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public MatchResult calculateMatchResult(List<Player> players) {
        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        Map<Player, MatchCase> playerMatchResult = new HashMap<>();
        for (Player player : players) {

            if (isDealerWin(player)) { // 딜러승, 플레이어 패배
                dealerWinCount = ifDealerWin(player, dealerWinCount, playerMatchResult);
                continue;
            }

            if (isDealerDraw(player)) { // 무승부
                dealerDrawCount = ifDealerDraw(player, dealerDrawCount, playerMatchResult);
                continue;
            }

            dealerLoseCount = ifDealerLose(player, dealerLoseCount, playerMatchResult);
        }

        return new MatchResult(
                new PlayerMatchResult(playerMatchResult),
                new DealerMatchResult(dealerWinCount, dealerDrawCount, dealerLoseCount)
        );
    }

    private boolean isDealerDraw(Player player) {
        return !this.isBust()
                && (this.getScore() == player.getScore())
                && ((player.isBlackjack() && this.isBlackjack())
                || (!player.isBlackjack() && !this.isBlackjack()));
    }

    private boolean isDealerWin(Player player) {
        return player.isBust()
                || (!this.isBust() && this.getScore() > player.getScore())
                || (this.isBlackjack() && !player.isBlackjack());
    }

    private static int ifDealerWin(Player player, int dealerWinCount, Map<Player, MatchCase> playerMatchResult) {
        dealerWinCount++;
        playerMatchResult.put(player, MatchCase.LOSE);
        return dealerWinCount;
    }

    private static int ifDealerDraw(Player player, int dealerDrawCount, Map<Player, MatchCase> playerMatchResult) {
        dealerDrawCount++;
        playerMatchResult.put(player, MatchCase.DRAW);
        return dealerDrawCount;
    }

    private static int ifDealerLose(Player player, int dealerLoseCount, Map<Player, MatchCase> playerMatchResult) {
        dealerLoseCount++; // 딜러 패배, 플레이어 승
        playerMatchResult.put(player, MatchCase.WIN);
        return dealerLoseCount;
    }

    public void drawAdditionalCard(Deck deck, CardShuffler cardShuffler) {
        this.drawCard(deck, cardShuffler);
    }

    public boolean shouldHit() {
        return this.getScore() <= DEALER_DRAW_BOUND;
    }
}
