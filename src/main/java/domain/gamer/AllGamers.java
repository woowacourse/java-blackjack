package domain.gamer;

import domain.card.providable.CardProvidable;
import domain.gamer.action.TurnActions;
import domain.result.GameRule;
import domain.result.Result;
import domain.result.ResultDerivable;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AllGamers {
    private final Dealer dealer;
    private final List<Player> players;
    private final GameRule gameRule;

    public AllGamers(List<Player> players, GameRule gameRule) {
        this.dealer = new Dealer();
        this.players = players;
        this.gameRule = gameRule;
    }

    public void drawInitialCards(CardProvidable cardProvidable) {
        dealer.drawInitialCards(cardProvidable);
        players.forEach(player -> player.drawInitialCards(cardProvidable));
    }

    public void playPlayersTurn(CardProvidable cardProvidable, TurnActions turnActions) {
        players.forEach(player -> player.playTurn(cardProvidable, gameRule, turnActions));
    }

    public void playDealerTurn(CardProvidable cardProvidable, TurnActions turnActions) {
        dealer.playTurn(cardProvidable, gameRule, turnActions);
    }

    public List<Result> gainAllResults() {
        List<Result> allResults = new ArrayList<>();

        List<Result> playerResults = gainPlayerResults();
        allResults.add(dealer.determineResult(playerResults, gameRule));
        allResults.addAll(playerResults);

        return allResults;
    }

    private List<Result> gainPlayerResults() {
        return players.stream()
                .map(player -> player.determineResult(dealer, gameRule))
                .collect(toList());
    }

    public List<Gamer> joinAllGamers() {
        List<Gamer> gamers = new ArrayList<>();
        gamers.add(dealer);
        gamers.addAll(players);

        return gamers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
