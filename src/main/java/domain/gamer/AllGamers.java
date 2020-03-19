package domain.gamer;

import domain.card.providable.CardProvidable;
import domain.gamer.action.TurnActions;
import domain.result.Result;
import domain.result.ResultDerivable;
import domain.result.score.ScoreCalculable;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AllGamers {
    private final Dealer dealer;
    private final List<Player> players;

    public AllGamers(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public void drawInitialCards(CardProvidable cardProvidable) {
        dealer.drawInitialCards(cardProvidable);
        players.forEach(player -> player.drawInitialCards(cardProvidable));
    }

    public void playPlayersTurn(CardProvidable cardProvidable, ScoreCalculable scoreCalculable, TurnActions turnActions) {
        players.forEach(player -> player.playTurn(cardProvidable, scoreCalculable, turnActions));
    }

    public void playDealerTurn(CardProvidable cardProvidable, ScoreCalculable scoreCalculable, TurnActions turnActions) {
        dealer.playTurn(cardProvidable, scoreCalculable, turnActions);
    }

    public List<Result> gainAllResults(ResultDerivable resultDerivable) {
        List<Result> allResults = new ArrayList<>();

        List<Result> playerResults = gainPlayerResults(resultDerivable);
        allResults.add(dealer.determineResult(playerResults, resultDerivable));
        allResults.addAll(playerResults);

        return allResults;
    }

    private List<Result> gainPlayerResults(ResultDerivable resultDerivable) {
        return players.stream()
                .map(player -> player.determineResult(dealer, resultDerivable))
                .collect(toList());
    }

    public List<AbstractGamer> joinAllGamers() {
        List<AbstractGamer> gamers = new ArrayList<>();
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
