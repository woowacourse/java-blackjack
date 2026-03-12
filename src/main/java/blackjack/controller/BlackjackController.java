package blackjack.controller;

import static blackjack.util.constant.Constants.GET_MORE_CARD_BUTTON;

import blackjack.domain.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;
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
        try {
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
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private Users createUsers() {
        List<String> userNames = InputParser.parseUser(inputView.readUserName());
        List<User> users = userNames.stream()
                .map(name -> new User(name, readBetting(name)))
                .toList();
        return new Users(users);
    }
    private BettingAmount readBetting(String name) {
        String userName = inputView.readBettingAmount(name);
        int amount = InputParser.parseBettingAmount(userName);
        return new BettingAmount(amount);
    }


    private void printGameSettingResult(Users users, Dealer dealer) {
        OutputView.printGameSettingMessage(dealer.getName(), users.getNames());
        OutputView.printSettingCardsResult(dealer.getName(), List.of(dealer.getFirstCardName()));
        users.forEach(user ->
                OutputView.printSettingCardsResult(user.getName(), user.getCardsName())
        );
        OutputView.printEmptyLine();
    }

    private void getMoreCards(Users users) {
        users.forEach(this::processUserTurn);
    }

    private void processUserTurn(User user) {
        drawIfWanted(user);
        OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
    }

    private void drawIfWanted(User user) {
        while (!user.isFinished() && GET_MORE_CARD_BUTTON.equals(inputView.readMoreCard(user.getName()))) {
            gameService.getMoreCard(user);
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
        }
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
        OutputView.printEmptyLine();
        users.forEach(user ->
                gameService.applyGameResult(user, dealer, gameResult)
        );
        OutputView.printWinningResult(gameResult, dealer.getName());
    }
}
