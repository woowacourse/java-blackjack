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
        OutputView.printSettingCardsResult(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (User user : users) {
            OutputView.printSettingCardsResult(user.getName(), user.getCardsName());
        }
    }

    private List<User> readUsers() {
        String userName = inputView.readUserName();
        return InputParser.createUser(userName);
    }


}
