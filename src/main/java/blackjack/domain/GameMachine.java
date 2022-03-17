package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.money.BettingMoney;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameMachine {

    private static final int MAX_PLAY_GAME_COUNT = 7;
    private static final String PLAY_GAME_COUNT_EXCEPTION_MESSAGE = "게임에 참여할 수 있는 유저는 최대 7명입니다.";

    private final CardDeck cardDeck;
    private final Map<User, BettingMoney> userBettingMoney;

    public GameMachine(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
        this.userBettingMoney = new HashMap<>();
    }

    public Dealer createDealer() {
        return new Dealer(cardDeck.selectOriginalCard());
    }

    public Users createUsers(final List<String> userNames) {
        validateUserCount(userNames);
        List<User> users = userNames.stream()
                .map(userName -> new User(userName, cardDeck.selectOriginalCard()))
                .collect(Collectors.toList());
        initUserBettingMoney(users);
        return new Users(users);
    }

    private static void validateUserCount(final List<String> users) {
        if (users.size() > MAX_PLAY_GAME_COUNT) {
            throw new IllegalArgumentException(PLAY_GAME_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private void initUserBettingMoney(final List<User> users) {
        users.forEach(user -> this.userBettingMoney.put(user, null));
    }

    public void putBettingMoney(final User user, final int money) {
        userBettingMoney.put(user, BettingMoney.of(money));
    }

    public boolean checkPlayerReceiveCard(final Player player) {
        if (player.canDrawCard()) {
            drawCardToPlayer(player);
            return true;
        }
        return false;
    }

    public void drawCardToPlayer(final Player player) {
        player.drawCard(cardDeck.selectCard());
    }
}
