package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = new BlackJackGame(requestPlayerNames());
        blackJackGame.drawInitialCards();
        showInitialCards(blackJackGame.getParticipants());

    }

    private List<String> requestPlayerNames() {
        outputView.printRequestPlayerNames();
        return inputView.readPlayerNames();
    }

    private void showInitialCards(final Participants participants) {
        List<String> playerNames = participants.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        outputView.printInitialCardMessage(playerNames);
        outputView.printInitialPlayerCards(participants.getDealer().getName(), participants.getDealer().getOpenCardName());
        participants.getPlayers().forEach(player ->
                outputView.printInitialPlayerCards(player.getName(), player.getCardNames()));
    }


}
