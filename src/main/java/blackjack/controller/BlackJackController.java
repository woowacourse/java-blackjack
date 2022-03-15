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
        BlackJackGame game = BlackJackGame.fromPlayerNames(inputView.inputPlayerNames());
        game.drawInitialCards(users -> outputView.printInitCards(UsersDto.fromInit(users)));
        game.hitOrStayCardsPlayer(
            user -> () -> inputView.inputWhetherToDrawCard(UserDto.fromEvery(user)),
            user -> outputView.printUserCards(UserDto.fromEvery(user))
        );
        game.hitOrStayCardsDealer(user -> outputView.printDealerHit(UserDto.fromEvery(user)));
        game.settleGame(
            users -> outputView.printAllUserCardsWithScore(UsersDto.fromEvery(users)),
            matchRecord -> outputView.printMatchResult(MatchRecordDto.fromRecords(matchRecord))
        );
    }
}
