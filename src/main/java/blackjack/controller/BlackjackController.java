package blackjack.controller;

import blackjack.domain.Game;
import blackjack.domain.user.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Game game = new Game(InputView.receivePlayerNames());

        initCards(game);
        drawCardsV2(game);
        printResults(game);
    }

    private void initCards(Game game) {
        game.initialCards();
        OutputView.printInitialCards(game.getDealer(), game.getPlayers());
    }

    private void drawCardsV2(Game game) {
        while (game.isAnyPlayerHit()) {
            PlayerDto hitPlayerDto = game.getAnyHitPlayerDto();
            game.askDrawToPlayer(hitPlayerDto, InputView.askIfMoreCardV2(hitPlayerDto));
            OutputView.printPlayerCardV2(hitPlayerDto);
        }
        OutputView.printDealerDraw(game.askDrawToDealer());
    }

    private void printResults(Game game) {
        OutputView.printUserResult(game.getResultDTOs());
        OutputView.printWinningResult(game.getWinningResultDTOs());
    }
}

