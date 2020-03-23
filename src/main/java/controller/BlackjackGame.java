package controller;

import domain.UserInterface;
import domain.blackjack.BlackjackService;
import domain.card.Deck;
import domain.result.MatchRule;
import domain.user.*;
import view.OutputView;

public class BlackjackGame {
    private Deck deck;
    private MatchRule matchRule;
    private UserInterface userInterface;

    public BlackjackGame(Deck deck, MatchRule matchRule, UserInterface userInterface) {
        this.deck = deck;
        this.matchRule = matchRule;
        this.userInterface = userInterface;
    }

    public void play() {
        Dealer dealer = Dealer.shuffle(deck);
        Players players = Players.join(userInterface);
        BlackjackService blackjackService = BlackjackService.of(dealer, matchRule);
        players = distributeInitCards(dealer, players, blackjackService);
        players = confirmCards(dealer, players, blackjackService);
        Profit dealerProfit = blackjackService.match(players);
        OutputView.printResult(dealer.serialize(dealerProfit), players.serialize());
    }

    private Players distributeInitCards(Dealer dealer, Players players, BlackjackService blackjackService) {
        players = blackjackService.distributeInitCards(players);
        OutputView.printInitGame(dealer.serialize(), players.serialize());
        return players;
    }

    private Players confirmCards(Dealer dealer, Players players, BlackjackService blackjackService) {
        players = blackjackService.confirmCardsOfPlayers(players);
        int countOfHit = blackjackService.confirmCardsOfDealer();
        OutputView.printDealerHit(dealer.serialize(), countOfHit);
        return players;
    }
}
