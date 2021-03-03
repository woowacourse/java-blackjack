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
        Users users = new Users(InputView.getUsersName());
        Player dealer = new Dealer();
        List<Player> players = setPlayers(users, dealer);
        players.forEach(Player::drawTwoCards);
        List<PlayerDto> playerDtos = getPlayerDtos(players);
        OutputView.printGiveTwoCardsMessage(playerDtos);
        users.getUsers().forEach(this::drawCard);
        dealerDraw(dealer);
    }

    private void dealerDraw(Player dealer) {
        if (dealer.isCanDraw()) {
            OutputView.printDealerDrawCardMessage();
            dealer.draw();
        }
    }

    private List<Player> setPlayers(Users users, Player dealer) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users.getUsers());
        return players;
    }

    private List<PlayerDto> getPlayerDtos(List<Player> players) {
        return players.stream()
            .map(PlayerDto::new)
            .collect(Collectors.toList());
    }

    private void drawCard(User user) {
        while (user.isCanDraw()) {
            String yesOrNo = InputView.getYesOrNo(new PlayerDto(user));
            if (user.isDrawContinue(yesOrNo)) {
                user.draw();
            }
            printCardsWhenDraw(user);
        }
    }

    private void printCardsWhenDraw(User user) {
        if (!user.isDrawStop()) {
            OutputView.printUserCards(new PlayerDto(user));
        }
    }
}
