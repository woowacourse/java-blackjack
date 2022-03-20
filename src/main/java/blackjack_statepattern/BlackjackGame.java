package blackjack_statepattern;

import blackjack_statepattern.card.CardDeck;
import blackjack_statepattern.participant.BetMoney;
import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Player;
import blackjack_statepattern.participant.Players;
import blackjack_statepattern.view.InputView;
import blackjack_statepattern.view.OutputView;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class BlackjackGame {
    private BlackjackBoard blackjackBoard;

    public void run() {
        initializeGame();
        playGame();
        printResult();
    }

    private void initializeGame() {
        blackjackBoard = new BlackjackBoard(new Dealer(), initializePlayers(), CardDeck.createNewCardDeck());
    }

    private Players initializePlayers() {
        return Players.create(InputView.askPlayerNames().stream().collect(
                Collectors.toMap(playerName -> playerName, this::askBetMoney, (a, b) -> b, LinkedHashMap::new)));
    }

    private BetMoney askBetMoney(String name) {
        try {
            return new BetMoney(InputView.askBetMoney(name));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askBetMoney(name);
        }
    }

    private void playGame() {
        distributeCards();
        playPlayersTurn();
        playDealerTurn();
    }

    private void distributeCards() {
        blackjackBoard.distributeCards();
        OutputView.printDistributedCards(blackjackBoard.getInitialCardsDto());
    }

    private void playPlayersTurn() {
        List<Player> players = blackjackBoard.getPlayers();
        for (Player player : players) {
            playPlayerTurn(player);
        }
    }

    private void playPlayerTurn(Player player) {
        while (!player.isFinished() && askPlayerResponse(player).isHit()) {
            blackjackBoard.hitCard(player);
            OutputView.printPlayerCards(player);
        }
        if (!player.isFinished()) {
            player.stay();
        }
    }

    private DrawCommand askPlayerResponse(Player player) {
        try {
            return DrawCommand.from(InputView.askDrawCommand(player));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return askPlayerResponse(player);
        }
    }

    private void playDealerTurn() {
        Dealer dealer = blackjackBoard.getDealer();
        while (!dealer.isFinished() && dealer.isRequiredMoreCard()) {
            blackjackBoard.hitCard(dealer);
            OutputView.printDealerReceiveCardMessage();
        }
    }

    private void printResult() {
        OutputView.printFinalCards(blackjackBoard.getFinalCardsDto());
        OutputView.printGameResult(blackjackBoard.getGameResult());
    }
}
