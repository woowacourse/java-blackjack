package blackjack.controller;

import blackjack.domain.GameResult;
import blackjack.domain.card.Cards;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.card.RandomCardsShuffler;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;

public class BlackjackController {
    public void run() {
        try {
            Players players = createPlayers();
            playersBettingMoney(players);
            Dealer dealer = new Dealer(players, DeckFactory.createShuffledDeck(new RandomCardsShuffler()));
            handOutCards(dealer, players);
            additionalCard(dealer, players);
            dealerAdditionalCard(dealer);
            printBlackjackResult(dealer, players);
            printGameResult(dealer, players);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
        }
    }

    private Players createPlayers() {
        String[] playerNames = InputView.inputPlayerNames();
        return new Players(toPlayers(playerNames));
    }

    private void playersBettingMoney(Players players) {
        players.sendAll(player -> {
            int playerBettingMoney = InputView.inputBettingMoney(player);
            player.setBettingMoney(playerBettingMoney);
        });
    }

    private static List<Player> toPlayers(String[] playerNames) {
        return Arrays.stream(playerNames)
                .map(name -> new Player(name.trim(), new Cards(), 10000))
                .toList();
    }

    private void handOutCards(Dealer dealer, Players players) {
        dealer.prepareBlackjack();
        OutputView.printDealerAndPlayerCards(dealer.getFirstCard(), players.getPlayers());
    }

    private void additionalCard(Dealer dealer, Players players) {
        players.sendAll((player -> {
            String answer = InputView.inputAdditionalCard(player);
            while (answer.equals("y")) {
                if (!sendCardToPlayer(dealer, player)) {
                    break;
                }
                OutputView.printPlayerCards(player);
                answer = InputView.inputAdditionalCard(player);
            }
        }));
    }

    private static boolean sendCardToPlayer(Dealer dealer, Player player) {
        try {
            dealer.sendCardToPlayer(player);
            return true;
        } catch (IllegalArgumentException e) {
            OutputView.printCannotAdditionalCard();
            return false;
        }
    }


    private void dealerAdditionalCard(Dealer dealer) {
        int prevSize = dealer.getCardSize();
        dealer.pickAdditionalCard();
        int nextSize = dealer.getCardSize();
        OutputView.printDealerAdditionalCard(nextSize - prevSize);
    }


    private void printBlackjackResult(Dealer dealer, Players players) {
        OutputView.printDealerResult(dealer);
        OutputView.printPlayerResult(players.getPlayers());
    }

    private void printGameResult(Dealer dealer, Players players) {
        GameResult gameResult = dealer.createGameResult();
        OutputView.printGameResult(gameResult, players.getPlayers());
        OutputView.printBettingResult(gameResult, players.getPlayers());
    }
}
