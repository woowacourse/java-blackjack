package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.User;
import blackjack.domain.Users;
import blackjack.service.GameService;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameService gameService;
    private final InputView inputView;

    public BlackjackController(GameService gameService, InputView inputView) {
        this.gameService = gameService;
        this.inputView = inputView;
    }

    public void run() {
        Users users = createUsers();
        Dealer dealer = new Dealer();

        gameService.settingCards(users, dealer);
        printGameSettingResult(users, dealer);

        getMoreCards(users);
        if (!users.isAllBurst()) {
            getMoreCardsForDealer(dealer);
        }

        printGameResult(users, dealer);
        printWinningResult(users, dealer);
    }

    private Users createUsers() {
        List<String> userNames = InputParser.parseUser(inputView.readUserName());
        List<User> users = userNames.stream()
                .map(User::new)
                .toList();
        return new Users(users);
    }

    private void printGameSettingResult(Users users, Dealer dealer) {
        OutputView.printGameSettingMessage(dealer.getName(), users.getNames());
        OutputView.printSettingCardsResult(dealer.getName(), List.of(dealer.getFirstCardName()));
        users.forEach(user ->
                OutputView.printSettingCardsResult(user.getName(), user.getCardsName())
        );
    }

    private void getMoreCards(Users users) {
        users.forEach(this::processUserTurn);
    }

    private void processUserTurn(User user) {
        boolean drew = drawIfWanted(user);
        if (!drew) {
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
        }
    }

    private boolean drawIfWanted(User user) {
        boolean drew = false;
        while (!user.isFinished() && "y".equals(inputView.readMoreCard(user.getName()))) {
            gameService.getMoreCard(user);
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
            drew = true;
        }
        return drew;
    }

    private void getMoreCardsForDealer(Dealer dealer) {
        while (dealer.shouldDrawCard()) {
            OutputView.printGetMoreCardsForDealer(dealer.getName());
            gameService.getMoreCard(dealer);
        }
    }

    private void printGameResult(Users users, Dealer dealer) {
        OutputView.printCardsResult(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        users.forEach(user ->
                OutputView.printCardsResult(user.getName(), user.getCardsName(), user.calculateCardsValue())
        );
    }

    private void printWinningResult(Users users, Dealer dealer) {
        GameResult gameResult = new GameResult();
        users.forEach(user ->
                gameService.applyGameResult(user, dealer, gameResult)
        );
        OutputView.printWinningResult(gameResult, dealer.getName());
    }
}
