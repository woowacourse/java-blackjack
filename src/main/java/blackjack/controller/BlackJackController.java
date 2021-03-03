package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.player.PlayerDto;
import blackjack.domain.player.Users;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    public void play() {
        String usersNames = InputView.getUsersName();
        List<Player> players = new ArrayList<>();

        Player dealer = new Dealer();
        players.add(dealer);

        Users users = new Users(usersNames);
        players.addAll(users.getUsers());

        for (Player player : players) {
            player.drawTwoCards();
        }

        List<PlayerDto> playerDtos = players.stream()
            .map(PlayerDto::new)
            .collect(Collectors.toList());

        OutputView.printGiveTwoCardsMessage(playerDtos);

        for (User user : users.getUsers()) {
            drawCard(user);
        }
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
            user.draw(InputView.getYesOrNo(new PlayerDto(user)));
            printCardsWhenDraw(user);
        }
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isDrawStop()) {
            OutputView.printUserCards(new PlayerDto(user));
        }
    }
}
