package blackjack.controller;

import blackjack.domain.player.User;
import blackjack.domain.player.UserDto;
import blackjack.domain.player.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public void play() {
        String usersNames = InputView.getUsersName();
        Users users = new Users(usersNames);
        // todo - 출력문 표시
        for (User user : users.getUsers()) {
            drawCard(user);
        }
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
            user.draw(InputView.getYesOrNo(new UserDto(user)));
            printCardsWhenDraw(user);
        }
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isDrawStop()) {
            OutputView.printUserCards(new UserDto(user));
        }
    }
}
