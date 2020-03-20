package domain.blackjack;

import domain.card.Deck;
import domain.result.MatchRule;
import domain.result.Results;
import domain.user.Dealer;
import domain.user.Players;

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

    public Results match(Players players) {
        return players.match(dealer, matchRule);
    }
}
