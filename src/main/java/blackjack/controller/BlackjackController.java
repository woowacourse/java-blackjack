package blackjack.controller;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Participants participants = createParticipants();
        BlackjackGame blackjackGame = new BlackjackGame(participants);

        printInitialCards(blackjackGame);
        dealMoreCards(blackjackGame);
        printResult(blackjackGame.getParticipants());
    }

    private Participants createParticipants() {
        List<String> names = inputPlayersName();
        return new Participants(createPlayersWithBettingMoney(names));
    }

    private List<String> inputPlayersName() {
        return InputView.inputPlayerName();
    }

    private List<Player> createPlayersWithBettingMoney(List<String> names) {
        return names.stream()
            .map(name -> new Player(name, InputView.inputBettingMoney(name)))
            .collect(Collectors.toList());
    }

    private void printInitialCards(BlackjackGame blackjackGame) {
        OutputView.printInitialCardInformation(ParticipantsDto.from(blackjackGame.getParticipants()));
    }

    private void dealMoreCards(BlackjackGame blackjackGame) {
        dealMoreCardsToPlayers(blackjackGame);
        dealMoreCardsToDealer(blackjackGame);
    }

    private void dealMoreCardsToPlayers(BlackjackGame blackjackGame) {
        while (!blackjackGame.isPlayersTurnEnd()) {
            Player player = blackjackGame.getCurrentPlayer();
            blackjackGame.playPlayerTurn(InputView.inputPlayerHit(player.getName()));
            OutputView.printPlayerCardInformation(ParticipantDto.from(player));
        }
    }

    private void dealMoreCardsToDealer(BlackjackGame blackjackGame) {
        while (!blackjackGame.isDealerTurnEnd()) {
            blackjackGame.playDealerTurn();
            OutputView.printDealerHitMessage();
        }
    }

    private void printResult(Participants participants) {
        OutputView.printCardsAndPoint(ParticipantsDto.from(participants));
        GameResult gameResult = new GameResult(participants);
        OutputView.printProfitResult(ProfitResultDto.from(gameResult.calculateTotalProfitResult()));
    }
}

