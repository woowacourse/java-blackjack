package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.User;
import blackjack.service.GameService;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final GameService gameService;
    private final InputView inputView;

    public BlackjackController(GameService gameService, InputView inputView) {
        this.gameService = gameService;
        this.inputView = inputView;
    }

    public void run() {
        List<User> users = createUsers();
        Dealer dealer = new Dealer();

        gameService.settingCards(users, dealer);
        printGameSettingResult(users, dealer);

        getMoreCards(users, dealer);
        if (!isAllUserBurst(users)) {
            getMoreCardsForDealer(dealer);
        }

        printGameResult(users, dealer);

        printWinningResult(users, dealer);
    }

    private void printGameSettingResult(List<User> users, Dealer dealer) {
        List<String> userNames = users.stream()
                .map(User::getName)
                .toList();

        OutputView.printGameSettingMessage(dealer.getName(), userNames);
        OutputView.printSettingCardsResult(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (User user : users) {
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
        }
    }

    private List<User> createUsers() {
        List<String> userNames = InputParser.parseUser(inputView.readUserName());
        return userNames.stream()
                .map(User::new)
                .toList();
    }

    // TODO : 코드 품질 개선 필요
    private void getMoreCards(List<User> users, Dealer dealer) {
        for (User user : users) {
            int count = 0;
            while (!user.isBurst() && !user.isBlackjack()) {
                String yesOrNo = inputView.readMoreCard(user.getName());
                if (yesOrNo.equals("y")) {
                    gameService.getMoreCard(user);

                    OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
                    count++;

                    continue;
                }
                if (count == 0) {
                    OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
                }
                break;
            }
        }
    }

    private void getMoreCardsForDealer(Dealer dealer) {
        while (dealer.calculateCardsValue() < 17) {
            OutputView.printGetMoreCardsForDealer(dealer.getName());
            gameService.getMoreCardForDealer(dealer);
        }
    }

    private boolean isAllUserBurst(List<User> users) {
        int burstUserCount = (int) users.stream()
                .filter(User::isBurst)
                .count();
        return users.size() == burstUserCount;
    }

    private void printGameResult(List<User> users, Dealer dealer) {
        OutputView.printCardsResult(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        for (User user : users) {
            OutputView.printCardsResult(user.getName(), user.getCardsName(), user.calculateCardsValue());
        }
    }

    private void printWinningResult(List<User> users, Dealer dealer) {
        Map<String, Boolean> result = new HashMap<>();
        int dealerWinCount = 0;
        for (User user : users) {
            dealerWinCount += gameService.applyGameResult(user, dealer, result);
        }

        OutputView.printWinningResult(result, dealer.getName(), dealerWinCount);
    }

}
