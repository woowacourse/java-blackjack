import domain.game.BlackJackGame;
import domain.player.Player;
import domain.strategy.RandomBasedShuffleStrategy;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        new Application().startGame();
    }
    
    public void startGame() {
        BlackJackGame blackJackGame = new BlackJackGame(getParticipantNames(), new RandomBasedShuffleStrategy());
        initializedBlackjackGame(blackJackGame);
        
        giveCardToPlayers(blackJackGame);
        blackJackGame.battle();
        OutputView.printPlayersGameResults(blackJackGame.getPlayers());
    }
    
    private String getParticipantNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }
    
    private void initializedBlackjackGame(BlackJackGame blackJackGame) {
        blackJackGame.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJackGame.getPlayers());
    }
    
    private void giveCardToPlayers(BlackJackGame blackJackGame) {
        giveCardToParticipants(blackJackGame);
        giveCardToDealer(blackJackGame);
        OutputView.printPlayersFinalInformation(blackJackGame.getPlayers());
    }
    
    private void giveCardToParticipants(BlackJackGame blackJackGame) {
        List<Player> participants = blackJackGame.getParticipants();
        for (Player participant : participants) {
            giveCardToParticipant(blackJackGame, participant);
        }
    }
    
    private void giveCardToParticipant(BlackJackGame blackJackGame, Player participant) {
        String command = getCommand(participant);
        if ("y".equals(command)) {
            blackJackGame.giveCard(participant.getName());
            OutputView.printParticipantCardCondition(List.of(participant));
        }
        
        if ("n".equals(command) || participant.isBurst()) {
            stopGivingCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJackGame, participant);
    }
    
    private String getCommand(Player participant) {
        OutputView.printAddCardGuide(participant.getName());
        return InputView.repeat(InputView::inputAddCardCommand);
    }
    
    private void stopGivingCard(Player participant, String command) {
        if ("n".equals(command)) {
            OutputView.printParticipantCardCondition(List.of(participant));
            return;
        }
        
        if (participant.isBurst()) {
            OutputView.printBurstMessage(participant.getName());
        }
    }
    
    private void giveCardToDealer(BlackJackGame blackJackGame) {
        if (blackJackGame.shouldDealerGetCard()) {
            blackJackGame.giveDealerCard();
            OutputView.printGiveDealerCardMessage();
        }
    }
}
