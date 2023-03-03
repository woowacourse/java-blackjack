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
    private Players players;
    private Dealer dealer;

    public void run() {
        getReady();
        giveFirstCards();

        OutputView.printDealerCurrentCards(dealer);
        OutputView.printPlayersCurrentCards(players);

        giveAdditionalCards();
        giveAdditionalCardToDealer();

        OutputView.printScore(dealer, players);
        printResults();
    }

    private void printResults() {
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

    private void giveAdditionalCards() {
        for (Player player : players.getPlayers()) {
            String answer = InputView.askAdditionalCard(player.getPlayerName());
            giveAdditionalCard(answer, player);
        }
    }

    private static void giveAdditionalCard(String answer, Player player) {
        while ("y".equals(answer) && player.isUnderLimit()) {
            player.updateCardScore(Cards.giveFirstCard());
            OutputView.printPlayerCurrentCards(player);
            answer = InputView.askAdditionalCard(player.getPlayerName());
        }
        OutputView.printPlayerCurrentCards(player);
    }

    public void giveAdditionalCardToDealer() {
        while (dealer.isUnderLimit()) {
            OutputView.printDealerOneMore();
            dealer.updateCardScore(Cards.giveFirstCard());
        }
    }

    private void getReady() {
        List<Name> names = new Names(InputView.inputPeopleNames()).getNames();

        List<Player> players = names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList());

        this.players = new Players(players);
        this.dealer = new Dealer();

        OutputView.printReadyMessage(names);
    }

    public void giveFirstCards() {
        Cards.init();
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
}
