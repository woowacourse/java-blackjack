import domain.BlackJackGame;
import domain.card.CardBundle;
import domain.participant.Participant;
import domain.participant.ParticipantsResult;
import java.util.ArrayList;
import java.util.List;
import utils.InputSplitter;
import view.InputView;
import view.OutputView;
import view.OutputViewOfProfit;

public class Application {

    static final InputView inputView = new InputView();
    static final OutputView outputView = new OutputViewOfProfit();
    static final CardBundle cardBundle = new CardBundle();

    public static void main(String[] args) {
        String userNames = inputView.inputUserNames();
        BlackJackGame blackJackGame = createBlackJackName(userNames);
        List<Participant> participants = blackJackGame.getParticipants();
        outputView.printInitialParticipantHands(participants);
        extraCardProcessOnPlayers(participants, blackJackGame);
        extraCardProcessOnDealer(blackJackGame);
        processOfPrintParticipantsInfo(participants);
        processOfPrintGameResult(blackJackGame);
    }

    private static BlackJackGame createBlackJackName(String userNames) {
        List<String> players = InputSplitter.split(userNames);
        List<Integer> bettingAmounts = new ArrayList<>();
        inputBettingAmountOfPlayer(players, bettingAmounts);
        return BlackJackGame.ofInit(cardBundle, players, bettingAmounts);
    }

    private static void inputBettingAmountOfPlayer(List<String> players,
        List<Integer> bettingAmounts) {
        for (String player : players) {
            int bettingAmount = inputView.inputBettingAmountOfPlayer(player);
            bettingAmounts.add(bettingAmount);
        }
    }

    private static void processOfPrintGameResult(BlackJackGame blackJackGame) {
        ParticipantsResult participantsResult = blackJackGame.calculateParticipantsResult();
        outputView.printGameResult(participantsResult);
    }

    private static void processOfPrintParticipantsInfo(List<Participant> participants) {
        for (Participant participant : participants) {
            outputView.printFullParticipantInfo(participant);
        }
    }

    private static void extraCardProcessOnPlayers(List<Participant> participants,
        BlackJackGame blackJackGame) {
        for (Participant participant : participants) {
            extraCardProcessOnPlayer(participant, blackJackGame);
        }
    }

    private static void extraCardProcessOnDealer(BlackJackGame blackJackGame) {
        int countOfDealerProcess = blackJackGame.receiveExtraCardProcessOfDealer();
        outputView.printBlankLine();
        outputView.printDealerPickMessageBy(countOfDealerProcess);
    }

    private static void extraCardProcessOnPlayer(Participant participant,
        BlackJackGame blackJackGame) {
        while (blackJackGame.canExtraReceiveOfPlayer(participant)) {
            String userOpinion = inputView.inputPlayerWantMoreCard(participant);
            if (userOpinion.equalsIgnoreCase("y")) {
                blackJackGame.receiveExtraCardProcessOfPlayer(participant);
            }
            outputView.printParticipantNameAndCard(participant);
            if (userOpinion.equalsIgnoreCase("n")) {
                break;
            }
        }
    }
}
