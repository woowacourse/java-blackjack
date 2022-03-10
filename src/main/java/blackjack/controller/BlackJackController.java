package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.Rule;
import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> inputNames = inputView.inputPlayerNames();
        Users users = Users.from(inputNames);
        Deck deck = new Deck();
        users.setInitCardsPerPlayer(deck);

        UsersDto usersDto = new UsersDto(users);
        outputView.printInitCards(usersDto);

        List<Player> players = users.getPlayers();
        for (Player player : players) {
            drawPlayerCardByYes(deck, player);
        }
        drawDealerCard(deck, users.getDealer());

        Rule rule = new Rule();

        users.calculateAllUser(rule);

        Dealer dealer = users.getDealer();

        outputView.printWithScore(UserDto.from(dealer), dealer.getScore());
        for (Player player : players) {
            outputView.printWithScore(UserDto.from(player), player.getScore());
        }
        //최종 승패
        Map<String, Result> map = Result.getMap(players, dealer);
        outputView.printYield(map);
    }

    private void drawDealerCard(Deck deck, Dealer dealer) {
        if (dealer.isDrawable()) {
            dealer.drawCard(deck);
            outputView.printDealer();
        }
    }

    private void drawPlayerCardByYes(Deck deck, Player player) {
        while (player.isDrawable() &&
                inputView.inputWhetherToDrawCard(UserDto.from(player))) {
            player.drawCard(deck);
            outputView.printCards(UserDto.from(player));
        }
    }
}
