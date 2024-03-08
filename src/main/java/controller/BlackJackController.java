package controller;

import domain.BlackJackGame;
import domain.CardDeck;
import domain.Player;
import domain.PlayerNames;
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
        PlayerNames playerNames = readPlayerNames();
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        printInitialDealAndHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);
        printGameResult(blackJackGame);
    }

    private PlayerNames readPlayerNames() throws IOException {
        List<String> inputNames = inputView.readPlayerNames();

        return new PlayerNames(inputNames);
    }

    private void repeatHitUntilStand(BlackJackGame blackJackGame) throws IOException {
        for (Player player : blackJackGame.getPlayers()) {
            repeatHitUntilPlayerStand(blackJackGame, player);
        }

        repeatHitUntilDealerStand(blackJackGame);
    }

    private void repeatHitUntilPlayerStand(BlackJackGame blackJackGame, Player player) throws IOException {
        while (player.isHittable() && inputView.readCommand(player.getName()).equals("y")) {
            blackJackGame.hitPlayer(player);
            outputView.printHandAfterHit(PlayerDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.hitDealer()) {
            outputView.printDealerHitMessage();
        }
    }

    private void printInitialDealAndHand(BlackJackGame blackJackGame) {
        List<Player> initialParticipants = blackJackGame.getEveryParticipants();
        List<PlayerDto> playerInitDtos = toPlayerDtos(initialParticipants);

        outputView.printInitialDeal(playerInitDtos);
        outputView.printInitialHand(playerInitDtos);
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        List<PlayerDto> playerDtos = toPlayerDtos(blackJackGame.getEveryParticipants());
        DealerResultDto dealerResultDto = DealerResultDto.from(blackJackGame.getGameResults());
        PlayersResultDto playersResultDto = PlayersResultDto.from(blackJackGame.getGameResults());

        outputView.printFinalHandAndScore(playerDtos);
        outputView.printWinLoss(dealerResultDto, playersResultDto);
    }

    private List<PlayerDto> toPlayerDtos(List<Player> players) {
        return players.stream()
                .map(PlayerDto::from)
                .toList();
    }
}
