package blackjack;

import blackjack.domain.Deck;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.dto.ParticipantStatus;
import blackjack.dto.TotalWinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import blackjack.view.UserCommand;
import java.util.List;

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
    
    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }
    
    private void printInitialResult(Participants participants) {
        outputView.printInitialResult(participants.getParticipantsInitialStatus());
    }
    
    private void printInitialSetup(Participants participants) {
        outputView.printInitialSetUp(participants.getPlayersStatus());
    }
    
    private Participants makeParticipants() {
        outputView.askGameMembers();
        List<String> playerNames = inputView.parsePlayerNames();
        Players players = Players.makePlayers(playerNames);
        Dealer dealer = new Dealer();
        return new Participants(players, dealer);
    }
    
    public void printGameResult(Participants participants) {
        outputView.printTotalResult(participants.getParticipantsStatus());
        
        TotalWinningResult totalWinningResults = participants.getWinningResult();
        
        outputView.printWinningResults(totalWinningResults);
    }
    
    private void gameStart(Participants participants, Deck deck) {
        printInitialSetup(participants);
        
        participants.distributeCards(deck);
        
        printInitialResult(participants);
    }
    
    private void dealerTurn(Participants participants, final Deck deck) {
        while (participants.isDealerDraw()) {
            outputView.printDealerTurn();
            participants.dealerDraw(deck);
        }
    }
    
    private void playerTurn(Participants participants, final Deck deck) {
        while (participants.isPlayerDraw()) {
            playerDraw(participants, deck);
        }
    }
    
    private void playerDraw(Participants participants, Deck deck) {
        ParticipantStatus drawablePlayer = participants.getDrawablePlayerInfo();
        if (isDraw(drawablePlayer)) {
            ParticipantStatus drawResult = participants.giveCard(deck);
            printDrewResult(drawResult);
            return;
        }
        participants.dontWantDraw();
    }
    
    private void printDrewResult(ParticipantStatus drawResult) {
        outputView.printPlayerStatus(drawResult);
    }
    
    private boolean isDraw(ParticipantStatus drawablePlayer) {
        outputView.hitOrStand(drawablePlayer);
        return getCommand() == UserCommand.YES;
    }
    
    private UserCommand getCommand() {
        UserCommand command = null;
        while (command == null) {
            command = inputView.getCommand();
        }
        return command;
    }
}
