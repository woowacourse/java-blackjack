package blackjack.controller;

import blackjack.dto.MatchRecordDto;
import blackjack.domain.card.Deck;
import blackjack.domain.strategy.DealerHitStrategy;
import blackjack.domain.strategy.PlayerHitStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.Users;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Deck deck = new Deck();

        Users users = inputPlayerNames();
        drawInitialCards(deck, users);
        hitOrStayCardPerUsers(deck, users);
        showGameResult(users);
    }

    private Users inputPlayerNames() {
        return Users.from(inputView.inputPlayerNames());
    }

    private void drawInitialCards(Deck deck, Users users) {
        users.drawInitCards(deck);
        outputView.printInitCards(UsersDto.fromInit(users));
    }

    private void hitOrStayCardPerUsers(Deck deck, Users users) {
        hitOrStayCardPerPlayers(deck, users.getPlayers());
        hitOrStayCardDealer(deck, users.getDealer());
    }

    private void hitOrStayCardDealer(Deck deck, Dealer dealer) {
        boolean isHit = dealer.hitOrStay(deck, new DealerHitStrategy(dealer.getScore()));
        if (isHit) {
            outputView.printDealerHit();
        }
    }

    private void hitOrStayCardPerPlayers(Deck deck, List<Player> players) {
        for (Player player : players) {
            hitOrStayCard(deck, player);
        }
    }

    private void hitOrStayCard(Deck deck, User user) {
        boolean isHit = user.hitOrStay(deck,
            new PlayerHitStrategy(inputView.inputWhetherToDrawCard(UserDto.fromEvery(user))));
        if (isHit) {
            outputView.printCards(UserDto.fromEvery(user));
            hitOrStayCard(deck, user);
        }
    }

    private void showGameResult(Users users) {
        outputView.printAllUserCardsWithScore(UsersDto.fromEvery(users));
        outputView.printMatchResult(MatchRecordDto.fromRecords(users.createPlayerMatchRecords()));
    }
}
