package controller;

import domain.BlackJack;
import domain.player.Player;
import domain.strategy.RandomBasedIndexGenerator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackApplication {
    public void startGame() {
        BlackJack blackJack = new BlackJack(getParticipantNames(), new RandomBasedIndexGenerator());
        initializedBlackjackGame(blackJack);

        giveCardToPlayers(blackJack);
        blackJack.battle2();
        OutputView.printPlayersGameResults(blackJack.getPlayers());
    }

    private String getParticipantNames() {
        OutputView.printParticipantNamesGuide();
        return InputView.repeat(InputView::inputParticipantNames);
    }

    private void initializedBlackjackGame(BlackJack blackJack) {
        blackJack.giveTwoCardToPlayers();
        OutputView.printPlayersInformation(blackJack.getPlayers());
    }

    private void giveCardToPlayers(BlackJack blackJack) {
        giveCardToParticipants(blackJack);
        giveCardToDealer(blackJack);
        OutputView.printPlayersFinalInformation(blackJack.getPlayers());
    }

    private void giveCardToParticipants(BlackJack blackJack) {
        List<Player> participants = blackJack.getParticipants();
        for (Player participant : participants) {
            giveCardToParticipant(blackJack, participant);
        }
    }

    private void giveCardToParticipant(BlackJack blackJack, Player participant) {
        String command = getCommand(participant);
        if ("y".equals(command)) {
            blackJack.giveCard(participant.getName());
            OutputView.printParticipantCardCondition(List.of(participant));
        }

        if ("n".equals(command) || participant.isBurst()) {
            stopGivingCard(participant, command);
            return;
        }
        giveCardToParticipant(blackJack, participant);
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

    private void giveCardToDealer(BlackJack blackJack) {
        if (blackJack.shouldDealerGetCard()) {
            blackJack.giveDealerCard();
            OutputView.printGiveDealerCardMessage();
        }
    }
}
