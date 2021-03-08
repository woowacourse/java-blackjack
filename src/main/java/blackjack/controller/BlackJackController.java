package blackjack.controller;

import blackjack.domain.ResultType;
import blackjack.domain.player.AllCardsOpenStrategy;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.dto.GameResultDTO;
import blackjack.domain.player.dto.PlayerDTO;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {
    public void play() {
        Users users = new Users(InputView.getUsersName());
        Dealer dealer = new Dealer();
        drawTwoCards(users, dealer);
        OutputView.printGiveTwoCardsMessage(getUserDTOs(users), new PlayerDTO(dealer));
        dealer.setCardOpen(new AllCardsOpenStrategy());
        users.getUsers().forEach(this::drawCard);
        dealerDraw(dealer);
        OutputView.printFinalCardsMessage(getUserDTOs(users), new PlayerDTO(dealer));
        Map<Name, ResultType> usersResult = users.getResult(dealer);
        OutputView.printResultMessage(new GameResultDTO(usersResult, dealer.getResult(usersResult)));
    }

    private void drawTwoCards(Users users, Dealer dealer) {
        dealer.drawRandomTwoCards();
        users.drawRandomTwoCard();
    }

    private List<PlayerDTO> getUserDTOs(Users users) {
        return users.getUsers().stream()
            .map(PlayerDTO::new)
            .collect(Collectors.toList());
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
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
        if (!user.isDrawStop()) {
            OutputView.printUserInitialCards(new PlayerDTO(user));
        }
    }

    private void dealerDraw(Dealer dealer) {
        if (dealer.isCanDraw()) {
            OutputView.printDealerDrawCardMessage();
            dealer.drawRandomOneCard();
        }
    }
}