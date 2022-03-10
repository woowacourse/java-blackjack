package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.GamerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.getNames();
        BlackJackGame blackJackGame = new BlackJackGame(names);
        blackJackGame.distributeFirstCards();
        OutputView.printFirstCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());

        List<String> playerNames = blackJackGame.getPlayerNames();
        for (String playerName : playerNames) {
            drawAdditionalCard(blackJackGame, playerName);
        }
        OutputView.printAdditionalDrawDealer(blackJackGame.distributeAdditionalToDealer());
        OutputView.printFinalCards(blackJackGame.getDealerDto(), blackJackGame.getPlayerDtos());

    }

    private void drawAdditionalCard(BlackJackGame blackJackGame, String playerName) {
        while (!blackJackGame.isBurst(playerName)
                && Answer.from(InputView.getAnswerOfAdditionalDraw(playerName)).isYes()) {
            blackJackGame.distributeCardToPlayer(playerName);
            GamerDto playerDtoByName = blackJackGame.findPlayerDtoByName(playerName);
            OutputView.printPlayerCard(playerDtoByName);
        }
    }

    public static void main(String[] args) {
        new BlackJackController().run();
    }
}
