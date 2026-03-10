package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.dto.DealerInitialHand;
import blackjack.dto.ParticipantHandScore;
import blackjack.dto.PlayerGameResult;
import blackjack.dto.PlayerHand;
import blackjack.dto.PlayerNames;
import blackjack.dto.TotalWinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.Supplier;

public class BlackjackRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Participants participants = makeParticipants();
        Deck deck = new Deck();
        gameStart(participants, deck);

        playerTurn(participants, deck);
        dealerTurn(participants, deck);
        gameEnd(participants);
    }

    private Participants makeParticipants() {
        outputView.askGameMembers();
        List<String> playerNames = inputView.parsePlayerNames();
        Players players = Players.makePlayers(playerNames);
        Dealer dealer = new Dealer();
        return new Participants(players, dealer);
    }

    private void gameStart(Participants participants, Deck deck) {
        printInitialSetup(participants);
        participants.distributeCards(deck);
        printInitialResult(participants);
    }

    private void printInitialSetup(Participants participants) {
        List<Player> players = participants.getPlayers();
        outputView.printInitialSetUp(PlayerNames.from(players));
    }

    private void printInitialResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();

        printDealerInitialResult(dealer);
        printPlayersInitialResult(players);
    }

    private void printDealerInitialResult(Dealer dealer) {
        outputView.printDealerInitialHand(DealerInitialHand.from(dealer));
    }

    private void printPlayersInitialResult(List<Player> players) {
        outputView.printPlayersInitialHand(PlayerHand.listOf(players));
    }

    private void printPlayerHand(Player player) {
        outputView.printParticipantInitialHand(PlayerHand.from(player));
    }

    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }

    public void printGameResult(Participants participants) {
        outputView.printParticipantsHandScore(ParticipantHandScore.listOf(participants.getParticipants()));

        List<PlayerGameResult> gameResults = determineGameResults(participants.getPlayers(), participants.getDealer());
        TotalWinningResult totalWinningResult = TotalWinningResult.from(gameResults);

        outputView.printWinningResults(totalWinningResult);
    }

    private List<PlayerGameResult> determineGameResults(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> PlayerGameResult.of(player, dealer))
                .toList();
    }

    private void playerTurn(Participants participants, Deck deck) {
        while (true) {
            Player currentPlayer = participants.getCurrentPlayer();
            if (currentPlayer == null) {
                break;
            }
            playerDraw(currentPlayer, deck);
        }
    }

    private void playerDraw(Player currentPlayer, Deck deck) {
        boolean isPlayerDraw = isDraw(currentPlayer);
        if (isPlayerDraw) {
            currentPlayer.addCardFrom(deck);
            printPlayerHand(currentPlayer);
            return;
        }
        if (currentPlayer.getCards().size() == 2) {
            printPlayerHand(currentPlayer);
        }
        currentPlayer.stopDrawing();
    }

    private boolean isDraw(Player currentPlayer) {
        return retry(() -> {
            outputView.hitOrStand(currentPlayer.getNickname());
            return inputView.getUserCommand();
        });
    }

    private void dealerTurn(Participants participants, final Deck deck) {
        Dealer dealer = participants.getDealer();
        while (dealer.isDrawable()) {
            outputView.printDealerTurn();
            dealer.addCardFrom(deck);
        }
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printLine(e.getMessage());
            }
        }
    }
}
