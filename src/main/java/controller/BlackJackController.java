package controller;

import domain.BlackJack;
import domain.player.Player;
import domain.strategy.RandomBasedIndexGenerator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {
    public void startGame() {
        OutputView.printParticipantNamesGuide();
        String participantNames = InputView.repeat(InputView::inputParticipantNames);

        BlackJack blackJack = new BlackJack(participantNames, new RandomBasedIndexGenerator());
        blackJack.startGame();

        OutputView.printPlayersInformation(blackJack.getPlayers());

        List<Player> participants = blackJack.getParticipants();
        for (Player participant : participants) {
            while (true) {
                OutputView.printAddCardGuide(participant.getName());
                String command = InputView.repeat(InputView::inputAddCardCommand);
                if ("y".equals(command)) {
                    blackJack.giveCard(participant.getName());
                    OutputView.printParticipantCardCondition(List.of(participant));
                }

                if ("n".equals(command)) {
                    OutputView.printParticipantCardCondition(List.of(participant));
                    break;
                }

                if (participant.isBurst()) {
                    OutputView.printBurstMessage(participant.getName());
                    break;
                }
            }
        }

        if (blackJack.shouldDealerGetCard()) {
            blackJack.giveDealerCard();
            OutputView.printGiveDealerCardMessage();
        }

        OutputView.printPlayersFinalInformation(blackJack.getPlayers());

        blackJack.battle();
    }
}
