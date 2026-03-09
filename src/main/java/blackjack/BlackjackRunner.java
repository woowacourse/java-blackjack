package blackjack;

import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.PlayingCards;
import blackjack.domain.participant.Dealer;
import blackjack.dto.WinningResult;
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
        PlayingCards deck = PlayingCards.createShuffledDeck();
        gameStart(participants, deck);
        
        playerTurn(participants, deck);
        dealerTurn(participants, deck);
        gameEnd(participants);
    }
    
    private void gameEnd(Participants participants) {
        printGameResult(participants);
    }
    
    private void printInitialResult(Participants participants) {
        outputView.printInitialResult(participants.getInitialResults());
    }
    
    private void printInitialSetup(Participants participants) {
        outputView.printInitialSetUp(participants.getPlayerNicknames());
    }
    
    private Participants makeParticipants() {
        outputView.askGameMembers();
        List<String> playerNames = inputView.parsePlayerNames();
        Players players = Players.makePlayers(playerNames);
        Dealer dealer = new Dealer();
        return new Participants(players, dealer);
    }
    
    public void printGameResult(Participants participants) {
        outputView.printTotalResult(participants.getTotalResults());
        
        WinningResult winningResults = participants.getWinningResult();
        outputView.printWinningResults(winningResults);
    }
    
    private void gameStart(Participants participants, PlayingCards deck) {
        printInitialSetup(participants);

        participants.distributeCards(deck);

        printInitialResult(participants);
    }
    
    private void dealerTurn(Participants participants, final PlayingCards deck) {
        while (participants.isDealerDraw()) {
            outputView.printDealerTurn();
            participants.dealerDraw(deck);
        }
    }
    
    private void playerTurn(Participants participants, final PlayingCards deck) {
        while (participants.findDrawablePlayer() != null) {
            playerDraw(participants, deck);
        }
    }
    
    private void playerDraw(Participants participants, PlayingCards deck) {
        String drawablePlayerNickname = participants.findDrawablePlayer();
        boolean isPlayerDraw = isDraw(drawablePlayerNickname);
        if (isPlayerDraw) {
            String drawResult = participants.addCardToAvailablePlayer(deck);
            printDrewResult(drawResult);
            return;
        }
        participants.dontWantDraw();
    }
    
    private void printDrewResult(String drawResult) {
        outputView.printLine(drawResult);
    }
    
    private boolean isDraw(String nickname) {
        outputView.hitOrStand(nickname);
        return getCommand() == UserCommand.YES;
    }
    
    private UserCommand getCommand() {
        UserCommand command = null;
        while (command == null) {
            command = getValidCommand();
        }
        return command;
    }
    
    private UserCommand getValidCommand() {
        try {
            return UserCommand.from(inputView.readLine());
        } catch (IllegalArgumentException exception) {
            outputView.printLine(exception.getMessage());
            return null;
        }
    }
}
