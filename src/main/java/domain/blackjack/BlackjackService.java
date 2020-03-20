package domain.blackjack;

import domain.result.MatchRule;
import domain.user.Dealer;
import domain.user.Players;
import domain.user.Profit;

public class BlackjackService {
    private Dealer dealer;
    private MatchRule matchRule;

    private BlackjackService(Dealer dealer, MatchRule matchRule) {
        this.dealer = dealer;
        this.matchRule = matchRule;
    }

    public static BlackjackService start(Dealer dealer, MatchRule matchRule) {
        return new BlackjackService(dealer, matchRule);
    }

    public Players distributeInitCards(Players players) {
        return players.receiveInitCards(dealer);
    }

    public Players confirmCardsOfPlayers(Players players) {
        return players.confirmCards(dealer);
    }

    public int confirmCardsOfDealer() {
        return dealer.confirmCards();
    }

    public Profit match(Players players) {
        return players.match(dealer, matchRule);
    }
}
