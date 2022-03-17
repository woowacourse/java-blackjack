package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.dto.MatchRecordDto;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame game = BlackJackGame.fromPlayerNames(inputView.inputPlayersAndMoney());
        game.drawInitialCards(users -> outputView.printInitCards(UsersDto.fromInit(users)));
        if (game.isEnd()) {
            game.settleGame(
                user -> outputView.printDealerBlackJack(UserDto.fromEvery(user)),
                users -> outputView.printAllUserCardsWithScore(UsersDto.fromEvery(users)),
                matchRecord -> outputView.printMatchResult(MatchRecordDto.fromRecords(matchRecord))
            );
            return;
        }
        runHitOrStayPhase(game);
    }

    private void runHitOrStayPhase(BlackJackGame game) {
        game.hitOrStayCardsPlayer(
            user -> () -> inputView.inputWhetherToDrawCard(UserDto.fromEvery(user)),
            user -> outputView.printUserCards(UserDto.fromEvery(user))
        );
        game.hitOrStayCardsDealer(user -> outputView.printDealerHit(UserDto.fromEvery(user)));
        end(game);
    }

    private void end(BlackJackGame game) {
        game.settleGame(
            users -> outputView.printAllUserCardsWithScore(UsersDto.fromEvery(users)),
            matchRecord -> outputView.printMatchResult(MatchRecordDto.fromRecords(matchRecord))
        );
    }
}
