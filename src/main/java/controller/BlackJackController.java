package controller;

import domain.*;
import dto.DealerResultDto;
import dto.PlayerDto;
import dto.PlayersResultDto;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() throws IOException {
        Players players = readPlayerNames();
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer(cardDeck);
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);
        blackJackGame.initHand();

        printInitialDealAndHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);
        printGameResult(blackJackGame);
    }

    private Players readPlayerNames() throws IOException {
        List<String> inputNames = inputView.readPlayerNames();

        return new Players(inputNames);
    }

    private void printInitialDealAndHand(BlackJackGame blackJackGame) {
        List<Participant> initialParticipants = blackJackGame.getEveryParticipants();
        List<PlayerDto> playerInitDtos = toPlayerDtos(initialParticipants);

        outputView.printInitialDeal(playerInitDtos);
        outputView.printInitialHand(playerInitDtos);
    }

    private void repeatHitUntilStand(BlackJackGame blackJackGame) throws IOException {
        for (Player player : blackJackGame.getPlayers()) {
            repeatHitUntilPlayerStand(blackJackGame, player);
        }

        repeatHitUntilDealerStand(blackJackGame);
    }

    private void repeatHitUntilPlayerStand(BlackJackGame blackJackGame, Player player) throws IOException {
        while (player.isHittable() && inputView.readCommand(player.getName()).equals("y")) {
            blackJackGame.hitParticipant(player);
            outputView.printHandAfterHit(PlayerDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.isDealerHittable()) {
            blackJackGame.hitDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        List<PlayerDto> playerDtos = toPlayerDtos(blackJackGame.getEveryParticipants());
        DealerResultDto dealerResultDto = DealerResultDto.from(blackJackGame.getGameResults());
        PlayersResultDto playersResultDto = PlayersResultDto.from(blackJackGame.getGameResults());

        outputView.printFinalHandAndScore(playerDtos);
        outputView.printWinLoss(dealerResultDto, playersResultDto);
    }

    private List<PlayerDto> toPlayerDtos(List<Participant> participants) {
        return participants.stream()
                .map(participant -> PlayerDto.from((Player) participant))
                .toList();
    }
}
