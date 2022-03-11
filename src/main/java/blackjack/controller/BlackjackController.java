package blackjack.controller;

import blackjack.domain.GameMachine;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void play() {
        final GameMachine gameMachine = new GameMachine(InputView.responseNames());
        OutputView.printPlayersInitCardInfo(gameMachine.getPlayers());
        decideMoreCard(gameMachine);
        announcePlayersFinishInfo(gameMachine.getPlayers());
        competeWithDealer(gameMachine.getPlayers());
        OutputView.printResult(gameMachine.getPlayers());
    }

    private void decideMoreCard(final GameMachine gameMachine) {
        for (Player participant : gameMachine.getPlayers().getParticipants()) {
            decideParticipantOneMoreCard(participant, gameMachine);
        }
        decideOneMoreCard(gameMachine);
    }

    private void decideParticipantOneMoreCard(final Player player, final GameMachine gameMachine) {
        while (isNotOverMaxScore(player) && InputView.oneMoreCard(player)) {
            gameMachine.giveCard(player);
            OutputView.printPlayerCardInfo(player);
        }
    }

    private boolean isNotOverMaxScore(final Player player) {
        Participant participant = Participant.changeToParticipant(player);
        if (participant.isOverMaxScore()) {
            OutputView.printParticipantOverMaxScore(player.getName());
        }
        return !participant.isOverMaxScore();
    }

    private void decideOneMoreCard(final GameMachine gameMachine) {
        if (gameMachine.isDealerGetCard()) {
            OutputView.printDealerAcceptCard();
            return;
        }
        OutputView.printDealerDenyCard();
    }

    private void announcePlayersFinishInfo(final Players players) {
        OutputView.printPlayerFinalInfo(players.getDealer());
        OutputView.printFinishParticipantInfo(players.getParticipants());
    }

    private void competeWithDealer(final Players players) {
        players.getParticipants()
                .forEach(player -> (Dealer.changeToDealer(players.getDealer()))
                        .compete((Participant) player));
    }
}
