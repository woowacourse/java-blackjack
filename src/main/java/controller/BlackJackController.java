package controller;

import domain.card.CardDeck;
import domain.game.BlackJackGame;
import domain.game.Command;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.PlayerNames;
import dto.PlayerDto;
import dto.ResultDto;
import dto.ScoreDto;
import java.io.IOException;
import java.util.List;
import view.InputView;
import view.OutputView;

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
        Dealer dealer = new Dealer(cardDeck);
        BlackJackGame blackJackGame = new BlackJackGame(playerNames, dealer);

        printInitialDealAndHand(blackJackGame);
        repeatHitUntilStand(blackJackGame);
        printGameResult(blackJackGame);
    }

    private PlayerNames readPlayerNames() throws IOException {
        List<String> inputNames = inputView.readPlayerNames();

        return new PlayerNames(inputNames);
    }

    private void printInitialDealAndHand(BlackJackGame blackJackGame) {
        List<Player> initialParticipants = blackJackGame.getEveryParticipants();
        List<PlayerDto> playerInitDtos = PlayerDto.from(initialParticipants);

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
        while (player.isHittable() && Command.from(inputView.readCommand(player.getName())).isProceed()) {
            blackJackGame.hitPlayer(player);
            outputView.printHandAfterHit(PlayerDto.from(player));
        }
    }

    private void repeatHitUntilDealerStand(BlackJackGame blackJackGame) {
        while (blackJackGame.hitDealer()) {
            outputView.printDealerHitMessage();
        }
    }

    private void printGameResult(BlackJackGame blackJackGame) {
        List<PlayerDto> playerDtos = PlayerDto.from(blackJackGame.getEveryParticipants());
        ResultDto resultDto = ResultDto.from(blackJackGame.getGameResults());
        ScoreDto scoreDto = ScoreDto.from(blackJackGame.getScores());

        outputView.printFinalHandAndScore(playerDtos, scoreDto);
        outputView.printWinLoss(resultDto);
    }
}
