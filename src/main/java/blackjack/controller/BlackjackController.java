package blackjack.controller;

import blackjack.domain.GameBoard;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomCardsShuffler;
import blackjack.domain.card.ScoreCalculator;
import blackjack.domain.participants.BettingMoney;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.domain.participants.Players;
import blackjack.domain.winning.Profit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {
    public void run() {
        try {
            Players players = createPlayers();
            Dealer dealer = new Dealer(new Cards());
            GameBoard gameBoard = new GameBoard(Deck.defaultDeck(), dealer, players);
            handOutCards(gameBoard);
            additionalCard(gameBoard, players);
            dealerAdditionalCard(gameBoard);
            printBlackjackResult(dealer, players);
            printBlackjackProfit(gameBoard, players);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e);
        }
    }

    private Players createPlayers() {
        List<String> playerNames = InputView.inputPlayerNames();
        return new Players(toPlayers(playerNames));
    }

    private List<Player> toPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(name -> new Player(
                        name,
                        new Cards(new ArrayList<>(), new ScoreCalculator()),
                        new BettingMoney(InputView.inputBattingAmount(name))))
                .toList();
    }

    private void handOutCards(GameBoard gameBoard) {
        gameBoard.prepareGame(new RandomCardsShuffler());
        OutputView.printDealerAndPlayerCards(gameBoard.getDealer(), gameBoard.getPlayers());
    }

    private void additionalCard(GameBoard gameBoard, Players players) {
        players.sendAll((player -> {
            while (InputView.inputAdditionalCard(player)) {
                if (!sendCardToPlayer(gameBoard, player)) {
                    break;
                }
                OutputView.printPlayerCards(player);
            }
        }));
    }

    private boolean sendCardToPlayer(GameBoard gameBoard, Player player) {
        try {
            gameBoard.drawCard(player);
            return true;
        } catch (RuntimeException e) {
            OutputView.printCannotAdditionalCard();
            return false;
        }
    }

    private void dealerAdditionalCard(GameBoard gameBoard) {
        int prevDealerCardSize = gameBoard.getDealerCardSize();
        gameBoard.drawAdditionalCardOfDealer();
        int nextDealerCardSize = gameBoard.getDealerCardSize();
        OutputView.printDealerAdditionalCard(nextDealerCardSize - prevDealerCardSize);
    }

    private void printBlackjackResult(Dealer dealer, Players players) {
        OutputView.printDealerResult(dealer);
        OutputView.printPlayerResult(players.getPlayers());
    }

    private void printBlackjackProfit(GameBoard gameBoard, Players players) {
        Profit profit = gameBoard.createProfit();
        OutputView.printProfit(profit, players.getPlayers());
    }
}
