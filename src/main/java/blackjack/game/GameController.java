package blackjack.game;

import blackjack.domain.card.Deck;
import blackjack.domain.player.*;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController {
    private static final int INIT_FIRST_CARD = 2;

    public void run() {
        Dealer dealer = new Dealer();
        Deck deck = Deck.initializeCards();
        List<Name> names = (InputView.repeat(() -> new Names(InputView.inputPeopleNames()))).getNames();
        Players players = initializePlayers(names);

        inputPlayerBetAmount(players.getPlayers());

        giveFirstCardsForUsers(players, dealer, deck);

        for (Player player : players.getPlayers()) {
            player.checkBlackJack(player.getTotalScore());
        }

        OutputView.printReadyMessage(names);
        printUserFirstCards(players.getPlayers(), dealer);

        giveCardOrNotForPlayers(players, deck);
        giveCardOrNotForDealer(dealer, deck);

        OutputView.printResults(dealer, players.getPlayers());
        printScore(players, dealer);
    }

    private void inputPlayerBetAmount(List<Player> players) {
        for (Player player : players) {
            int betAmount = InputView.repeat(() -> InputView.inputBetAmount(player.getPlayerName()));
            player.inputBetAmount(betAmount);
        }
    }

    private Players initializePlayers(List<Name> names) {
        return new Players(names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    private void giveFirstCardsForUsers(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            giveFirstCards(player, deck);
        }
        giveFirstCards(dealer, deck);
    }

    private void giveFirstCards(User user, Deck deck) {
        for (int i = 0; i < INIT_FIRST_CARD; i++) {
            user.updateCardScore(deck.draw());
        }
    }

    private void printUserFirstCards(List<Player> players, Dealer dealer) {
        OutputView.printDealerFirstCard(dealer);
        for (Player player : players) {
            OutputView.printPlayerCurrentCards(player);
        }
        System.out.println();
    }

    private void giveCardOrNotForPlayers(Players players, Deck shuffledDeck) {
        for (Player player : players.getPlayers()) {
            giveCardOrNotForPlayer(player, shuffledDeck);
        }
    }

    private void giveCardOrNotForPlayer(Player player, Deck deck) {
        while (player.isUnderLimit() &&
                (InputView.repeat(() -> InputView.askAdditionalCard(player.getPlayerName())))) {
            player.updateCardScore(deck.draw());
            OutputView.printPlayerCurrentCards(player);
        }
    }

    private void giveCardOrNotForDealer(Dealer dealer, Deck deck) {
        while (dealer.isUnderLimit()) {
            OutputView.printMessageDealerOneMore();
            dealer.updateCardScore(deck.draw());
        }
    }

    private void printScore(Players players, Dealer dealer) {
        GameResult gameResult = new GameResult(players.getPlayers(), dealer);
        Map<String, Integer> playersScore = gameResult.getPlayerGameResults();
        OutputView.printScore(playersScore, gameResult.calculateDealerProfit());
    }
}
