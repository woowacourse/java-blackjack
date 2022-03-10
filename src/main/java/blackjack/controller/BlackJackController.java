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
            while(Answer.from(InputView.getAnswerOfAdditionalDraw(playerName)).isYes()) {
                blackJackGame.distributeCardToPlayer(playerName);
                OutputView.printPlayerCard(blackJackGame.findPlayerDtoByName(playerName));
            }
        }
    }

    public static void main(String[] args) {
        new BlackJackController().run();
    }
}
