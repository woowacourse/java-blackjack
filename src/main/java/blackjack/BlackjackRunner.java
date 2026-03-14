package blackjack;

import blackjack.domain.bet.Bets;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Gamblers;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerInitialHand;
import blackjack.view.dto.ParticipantHandScore;
import blackjack.view.dto.PlayerHand;
import blackjack.view.dto.PlayerNicknames;
import blackjack.view.dto.PlayerProfit;
import blackjack.view.dto.TotalWinningResult;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BlackjackRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void execute() {
        Gamblers gamblers = makeGamblers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        initializeGame(gamblers, dealer, deck);

        playerTurn(gamblers, deck);
        dealerTurn(dealer, deck);
        gameEnd(gamblers, dealer);
    }

    private Gamblers makeGamblers() {
        Players players = makePlayers();
        Bets bets = makeBets(players);
        return new Gamblers(players, bets);
    }

    private Players makePlayers() {
        outputView.askGameMembers();
        List<String> playerNicknames = inputView.readPlayerNicknames();
        return Players.fromPlayerNicknames(playerNicknames);
    }

    private Bets makeBets(Players players) {
        Bets bets = new Bets();
        players.getPlayers()
                .forEach(player -> {
                    outputView.printAskPlayerBettingAmount(player.getNickname());
                    int betAmount = inputView.readPlayerBettingAmount();
                    bets.playerBet(player, betAmount);
                });
        return bets;
    }

    private void initializeGame(Gamblers gamblers, Dealer dealer, Deck deck) {
        printParticipantsNames(gamblers);
        dealer.drawInitialCards(deck);
        gamblers.distributeCards(deck);
        printInitialHand(gamblers, dealer);
    }

    private void printParticipantsNames(Gamblers gamblers) {
        List<Player> players = gamblers.getPlayers();
        PlayerNicknames playerNicknames = PlayerNicknames.from(players);
        outputView.printInitialSetUp(playerNicknames);
    }

    private void printInitialHand(Gamblers gamblers, Dealer dealer) {
        List<Player> players = gamblers.getPlayers();

        printDealerInitialHand(dealer);
        printPlayersInitialHand(players);
    }

    private void printDealerInitialHand(Dealer dealer) {
        outputView.printDealerInitialHand(DealerInitialHand.from(dealer));
    }

    private void printPlayersInitialHand(List<Player> players) {
        outputView.printPlayersInitialHand(PlayerHand.listOf(players));
    }

    private void playerTurn(Gamblers gamblers, Deck deck) {
        while (true) {
            Player currentPlayer = gamblers.getCurrentPlayer();
            if (currentPlayer == null) {
                break;
            }
            playerDraw(currentPlayer, deck);
        }
    }

    private void playerDraw(Player currentPlayer, Deck deck) {
        boolean hit = askHitOrStand(currentPlayer);
        if (hit) {
            currentPlayer.receiveCard(deck.drawCard());
            printCurrentHand(currentPlayer);
            return;
        }
        if (currentPlayer.getCardsCount() == 2) {
            printCurrentHand(currentPlayer);
        }
        currentPlayer.stand();
    }

    private boolean askHitOrStand(Player currentPlayer) {
        return retry(() -> {
            outputView.hitOrStand(currentPlayer.getNickname());
            return inputView.readUserCommand();
        });
    }

    private void printCurrentHand(Player player) {
        outputView.printParticipantInitialHand(PlayerHand.from(player));
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isDrawable()) {
            outputView.printDealerTurn();
            dealer.receiveCard(deck.drawCard());
        }
    }

    private void gameEnd(Gamblers gamblers, Dealer dealer) {
        printHandScores(gamblers, dealer);

        TotalWinningResult totalWinningResult = determineTotalWinningResult(gamblers, dealer);

        outputView.printFinalProfit(totalWinningResult);
    }

    private void printHandScores(Gamblers gamblers, Dealer dealer) {
        outputView.printParticipantsHandScore(ParticipantHandScore.listOf(gamblers.getPlayers(), dealer));
    }

    private TotalWinningResult determineTotalWinningResult(Gamblers gamblers, Dealer dealer) {
        Map<Player, Integer> playerProfits = gamblers.determinePlayerProfits(dealer);
        long dealerProfit = gamblers.getDealerProfit(playerProfits);

        return new TotalWinningResult(dealerProfit, PlayerProfit.listFrom(playerProfits));
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
