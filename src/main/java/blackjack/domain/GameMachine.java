package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.money.UserBettingMoney;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.result.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameMachine {

    private static final int MAX_PLAY_GAME_COUNT = 7;
    private static final String PLAY_GAME_COUNT_EXCEPTION_MESSAGE = "게임에 참여할 수 있는 유저는 최대 7명입니다.";

    private final CardDeck cardDeck;
    private UserBettingMoney userBettingMoney;

    public GameMachine(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Dealer createDealer() {
        return new Dealer(cardDeck.drawInitialCard());
    }

    public Users createUsers(final List<String> userNames) {
        validateUserCount(userNames);
        List<User> users = userNames.stream()
                .map(userName -> new User(userName, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
        userBettingMoney = new UserBettingMoney(users);
        return new Users(users);
    }

    private void validateUserCount(final List<String> users) {
        if (users.size() > MAX_PLAY_GAME_COUNT) {
            throw new IllegalArgumentException(PLAY_GAME_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public void drawCardToPlayer(final Player player) {
        player.drawCard(cardDeck.drawCard());
    }

    public void putBettingMoney(final User user, final int money) {
        userBettingMoney.putBettingMoney(user, money);
    }

    public Map<User, Integer> getUserRevenue(final Map<User, Result> userResult) {
        return userBettingMoney.getUserRevenue(userResult);
    }

    public int getDealerRevenue() {
        return userBettingMoney.getDealerRevenue();
    }
}
