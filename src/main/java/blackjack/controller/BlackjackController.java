package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.User;
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
        List<User> users = readUsers();
        Dealer dealer = new Dealer(new Deck());

        gameService.settingCards(users, dealer);
        printGameSettingResult(users, dealer);

        getMoreCards(users, dealer);
        getMoreCardsForDealer(dealer);

        printGameResult(users, dealer);
    }

    private void printGameSettingResult(List<User> users, Dealer dealer) {
        OutputView.printSettingCardsResult(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (User user : users) {
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
        }
    }

    private List<User> readUsers() {
        String userName = inputView.readUserName();
        return InputParser.createUser(userName);
    }

    // TODO : 코드 품질 개선 필요
    private void getMoreCards(List<User> users, Dealer dealer) {
        for (User user : users) {
            int count = 0;
            while (true) {
                String yesOrNo = inputView.readMoreCard(user.getName());
                if (yesOrNo.equals("y")) {
                    gameService.getMoreCard(user, dealer);

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

    private void printGameResult(List<User> users, Dealer dealer) {
        OutputView.printCardsResult(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        for (User user : users) {
            OutputView.printCardsResult(user.getName(), user.getCardsName(), user.calculateCardsValue());
        }
    }

}
