package blackjack.controller;

import blackjack.gamer.Dealer;
import blackjack.gamer.GameParticipant;
import blackjack.gamer.GameParticipants;
import blackjack.gamer.Nickname;
import blackjack.gamer.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        GameParticipants participants = initializeGameParticipants();

        participants.processInitialDealing();
        participants.processHit();

    }

    private GameParticipants initializeGameParticipants() {
        List<Player> players = inputView.readNicknames().stream()
                .map(Nickname::from)
                .map(nickname -> Player.of(nickname, this::askHit, this::showHands))
                .toList();

        Dealer dealer = Dealer.create(players.size(), this::showHands);

        return GameParticipants.of(players, dealer);
    }

    private boolean askHit(Player player) {
        return inputView.readShouldHit(player);
    }

    private void showHands(GameParticipant participant) {
        outputView.printParticipantCards(participant);
    }
}
