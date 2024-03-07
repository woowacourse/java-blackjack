package controller;

import domain.BlackJackGame;
import domain.CardDeck;
import domain.Player;
import domain.PlayerNames;
import dto.PlayerDto;
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
        List<String> inputNames = inputView.readPlayerNames();
        PlayerNames playerNames = new PlayerNames(inputNames);
        CardDeck cardDeck = new CardDeck();
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, cardDeck);

        List<PlayerDto> playerinitDtos = blackJackGame.getEveryParticipants().stream()
                .map(PlayerDto::from).toList();
        outputView.printDealingMessage(playerinitDtos);
        outputView.printInitialHand(playerinitDtos);

        for (Player player : blackJackGame.getPlayers()) {
            repeatUntilStand(blackJackGame, player);
        }

        repeatUntilDealerStand(blackJackGame);

        List<PlayerDto> playerDtos = blackJackGame.getEveryParticipants().stream()
                .map(PlayerDto::from).toList();
        outputView.printGameScore(playerDtos);

        outputView.printGameResult(blackJackGame.getGameResults());
    }

    private void repeatUntilStand(BlackJackGame blackJackGame, Player player) throws IOException {
        while (player.isHittable() && inputView.readCommand(player.getName().name()).equals("y")) {
            blackJackGame.hitPlayer(player);
            outputView.printHansAfterHit(PlayerDto.from(player));
        }
    }

    private void repeatUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.hitDealer()) {
            outputView.printDealerHitMessage();
        }
    }
}
