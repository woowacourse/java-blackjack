package blackjack.controller;

import blackjack.common.exception.CustomException;
import blackjack.domain.BlackJackGame;
import blackjack.domain.player.Player;
import blackjack.dto.PlayerStatusDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private BlackJackGame blackJackGame;

    public void run() {
        init();

        blackJackGame.handOutStartCards();

        PlayerStatusDto dealer = makeDealerStatus();
        List<PlayerStatusDto> challengers = makeChallengersStatus();
        OutputView.printStartStatus(dealer, challengers);

        InputView.terminate();
    }

    private List<PlayerStatusDto> makeChallengersStatus() {
        List<Player> players = blackJackGame.getChallengers();
        List<PlayerStatusDto> gameStatus = new ArrayList<>();
        for (Player player : players) {
            PlayerStatusDto playerStatusDto = new PlayerStatusDto(player);
            gameStatus.add(playerStatusDto);
        }
        return gameStatus;
    }

    private PlayerStatusDto makeDealerStatus() {
        Player dealer = blackJackGame.getDealer();
        return new PlayerStatusDto(dealer);
    }

    private void init() {
        try {
            List<String> playerNames = InputView.inputPlayerNames();
            blackJackGame = BlackJackGame.from(playerNames);
        } catch (CustomException e) {
            OutputView.printErrorMessage(e);
            init();
        }
    }
}
