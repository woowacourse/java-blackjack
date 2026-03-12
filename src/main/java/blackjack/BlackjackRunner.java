package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerNicknames;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.dto.DealerInitialHand;
import blackjack.view.dto.ParticipantHandScore;
import blackjack.view.dto.PlayerHand;
import blackjack.view.dto.TotalWinningResult;
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
        initializeGame(participants, deck);
        
        playerTurn(participants, deck);
        dealerTurn(participants, deck);
        gameEnd(participants);
    }
    
    private Participants makeParticipants() {
        outputView.askGameMembers();
        Players players = makePlayers();
        Dealer dealer = new Dealer();
        return new Participants(players, dealer);
    }
    
    private Players makePlayers() {
        List<String> playerNicknames = inputView.readPlayerNicknames();
        PlayerNicknames nicknames = new PlayerNicknames(playerNicknames);
        List<Integer> playerBettingAmounts = nicknames.nicknames().stream()
                .map(nickname -> {
                    outputView.printAskPlayerBettingAmount(nickname);
                    return inputView.readPlayerBettingAmount();
                })
                .toList();
        return Players.fromNameAndBettingAmounts(playerNicknames, playerBettingAmounts);
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
    
    private void initializeGame(Participants participants, Deck deck) {
        printParticipantsNames(participants);
        participants.distributeCards(deck);
        printInitialHand(participants);
    }
    
    private void printParticipantsNames(Participants participants) {
        List<Player> players = participants.getPlayers();
        outputView.printInitialSetUp(PlayerNicknames.from(players));
    }
    
    private void printInitialHand(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        
        printDealerInitialHand(dealer);
        printPlayersInitialHand(players);
    }
    
    private void printDealerInitialHand(Dealer dealer) {
        outputView.printDealerInitialHand(DealerInitialHand.from(dealer));
    }
    
    private void printPlayersInitialHand(List<Player> players) {
        outputView.printPlayersInitialHand(PlayerHand.listOf(players));
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
    
    private void dealerTurn(Participants participants, final Deck deck) {
        Dealer dealer = participants.getDealer();
        while (dealer.isDrawable()) {
            outputView.printDealerTurn();
            dealer.receiveCard(deck.drawCard());
        }
    }
    
    private void printCurrentHand(Player player) {
        outputView.printParticipantInitialHand(PlayerHand.from(player));
    }
    
    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }
    
    public void printGameResult(Participants participants) {
        outputView.printParticipantsHandScore(ParticipantHandScore.listOf(participants.getParticipants()));
        
        TotalWinningResult totalWinningResult = determineTotalWinningResult(participants);
        
        outputView.printWinningResults(totalWinningResult);
    }
    
    private TotalWinningResult determineTotalWinningResult(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        
        return TotalWinningResult.of(dealer, players);
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
