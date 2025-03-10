package blackjack.controller;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.GameParticipant;
import blackjack.domain.gamer.GameParticipants;
import blackjack.domain.gamer.Nickname;
import blackjack.domain.gamer.Player;
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
