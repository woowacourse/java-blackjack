package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerDto;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    public void play() {
        Users users = new Users(InputView.getUsersName());
        Dealer dealer = new Dealer();
        drawTwoCards(users, dealer);
        OutputView.printGiveTwoCardsMessage(getUserDTOs(users), new PlayerDto(dealer));
        users.getUsers().forEach(this::drawCard);
        dealerDraw(dealer);
        OutputView.printFinalCardsMessage(getUserDTOs(users), new PlayerDto(dealer));
        OutputView.printResultMessage(users.getResult(dealer));
    }

    private void drawTwoCards(Users users, Dealer dealer) {
        dealer.drawRandomTwoCards();
        users.getUsers().forEach(Player::drawRandomTwoCards);
    }

    private List<PlayerDto> getUserDTOs(Users users) {
        return users.getUsers().stream()
            .map(PlayerDto::new)
            .collect(Collectors.toList());
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
            askDrawContinue(user);
        }
    }

    private void askDrawContinue(User user) {
        String yesOrNo = InputView.getYesOrNo(new PlayerDto(user));
        if (user.isDrawContinue(yesOrNo)) {
            user.drawRandomOneCard();
        }
        printCardsWhenDraw(user);
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isDrawStop()) {
            OutputView.printUserInitialCards(new PlayerDto(user));
        }
    }

    private void dealerDraw(Dealer dealer) {
        if (dealer.isCanDraw()) {
            OutputView.printDealerDrawCardMessage();
            dealer.drawRandomOneCard();
        }
    }
}