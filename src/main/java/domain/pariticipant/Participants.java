package domain.pariticipant;


import domain.card.Deck;
import domain.card.Hand;
import domain.result.DealerMatchResult;
import domain.result.MatchCase;
import domain.result.MatchResult;
import domain.result.PlayersMatchResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static constant.BlackjackConstant.DEALER_NAME;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(Players players) {
        this.dealer = new Dealer(new Name(DEALER_NAME), new Hand(new ArrayList<>()));
        this.players = players;
    }

    public Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Players getPlayers() {
        return players;
    }

    public void drawInitialCards(Deck deck) {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    public MatchResult calculateMatchResult() {
        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        Map<Player, MatchCase> playerMatchResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {

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
                new PlayersMatchResult(playerMatchResult),
                new DealerMatchResult(dealerWinCount, dealerDrawCount, dealerLoseCount)
        );
    }

    private boolean isDealerDraw(Player player) {
        return !dealer.isBust()
                && (dealer.getScore() == player.getScore())
                && ((player.isBlackjack() && dealer.isBlackjack())
                || (!player.isBlackjack() && !dealer.isBlackjack()));
    }

    private boolean isDealerWin(Player player) {
        return player.isBust()
                || (!dealer.isBust() && dealer.getScore() > player.getScore())
                || (dealer.isBlackjack() && !player.isBlackjack());
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
}
