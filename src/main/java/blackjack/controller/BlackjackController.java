package blackjack.controller;

import blackjack.domain.card.Cards;
import blackjack.domain.player.*;
import blackjack.domain.result.Result;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    public void run() {
        List<Name> names = (InputView.repeat(() -> new Names(InputView.inputPeopleNames()))).getNames();
        Players players = new Players(names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList()));
        Dealer dealer = new Dealer();
        OutputView.printReadyMessage(names);

        Cards.init();

        giveFirstCards(players, dealer);

        OutputView.printDealerCurrentCards(dealer);
        OutputView.printPlayersCurrentCards(players);

        giveCardOrNotForPlayers(players);
        giveCardOrNotForDealer(dealer);

        printResults(players, dealer);
    }

    public void giveFirstCards(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private void giveFirstCard(User user) {
        for (int i = 0; i < 2; i++) {
            user.updateCardScore(Cards.giveFirstCard());
        }
    }

    private void giveCardOrNotForPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            giveCardOrNotForPlayer(player);
        }
    }

    private void giveCardOrNotForPlayer(Player player) {
        Answer answer = Answer.of(InputView.repeat(() -> InputView.askAdditionalCard(player.getPlayerName())));

        if (answer.equals(Answer.YES) && player.isUnderLimit()) {
            player.updateCardScore(Cards.giveFirstCard());
            OutputView.printPlayerCurrentCards(player);
            giveCardOrNotForPlayer(player);
        }
    }

    private void giveCardOrNotForDealer(Dealer dealer) {
        if (dealer.isUnderLimit()) {
            OutputView.printDealerOneMore();
            dealer.updateCardScore(Cards.giveFirstCard());
            giveCardOrNotForDealer(dealer);
        }
    }

    private void printResults(Players players, Dealer dealer) {
        OutputView.printScore(dealer, players);

        int dealerScore = dealer.getTotalScore();
        HashMap<Player, Result> playerResults = new HashMap<>();
        HashMap<Result, Integer> dealerResults = initializedResults();

        for (Player player : players.getPlayers()) {
            Result playerResult = Result.calculateResult(player.getTotalScore(), dealerScore);
            Result dealerResult = playerResult.ofOppositeResult();
            playerResults.put(player, playerResult);
            dealerResults.put(dealerResult, dealerResults.get(dealerResult) + 1);
        }
        OutputView.printResults(playerResults, dealerResults);
    }

    private HashMap<Result, Integer> initializedResults() {
        HashMap<Result, Integer> dealerResults = new HashMap<>();

        for (Result result : Result.values()) {
            dealerResults.put(result, 0);
        }
        return dealerResults;
    }
}
