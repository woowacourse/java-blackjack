package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.player.cardopen.AllCardsOpenStrategy;
import blackjack.domain.player.dto.GameResultDTO;
import blackjack.domain.player.dto.PlayerDTO;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    public void play() {
        Users users = new Users(InputView.getUsersName());
        usersBetAmount(users);
        Dealer dealer = new Dealer();
        drawCards(users, dealer);
        printResultMessage(users, dealer);
    }

    private void drawCards(Users users, Dealer dealer) {
        OutputView.printGiveTwoCardsMessage(getUserDTOs(users), new PlayerDTO(dealer));
        dealer.setCardOpen(new AllCardsOpenStrategy());
        users.getUsers().forEach(this::drawCard);
        dealerDraw(dealer);
    }

    private void printResultMessage(Users users, Dealer dealer) {
        OutputView.printFinalCardsMessage(getUserDTOs(users), new PlayerDTO(dealer));
        Map<Name, Integer> usersResult = users.getResult(dealer);
        OutputView
            .printResultMessage(new GameResultDTO(usersResult, dealer.getResult(usersResult)));
    }

    private void usersBetAmount(Users users) {
        for (User user : users.getUsers()) {
            user.setBetAmount(InputView.getUserBetAmount(new PlayerDTO(user)));
        }
    }

    private List<PlayerDTO> getUserDTOs(Users users) {
        return users.getUsers().stream()
            .map(PlayerDTO::new)
            .collect(Collectors.toList());
    }

    private void drawCard(User user) {
        while (!user.isFinished()) {
            askDrawContinue(user);
        }
    }

    private void askDrawContinue(User user) {
        String yesOrNo = InputView.getYesOrNo(new PlayerDTO(user));
        if (user.isDrawContinue(yesOrNo)) {
            user.drawRandomOneCard();
        }
        printCardsWhenDraw(user);
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isFinished()) {
            OutputView.printUserInitialCards(new PlayerDTO(user));
        }
    }

    private void dealerDraw(Dealer dealer) {
        if (!dealer.isFinished()) {
            OutputView.printDealerDrawCardMessage();
            dealer.drawRandomOneCard();
        }
    }
}