package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private static final int MAX_PLAY_GAME_COUNT = 7;
    private static final String PLAY_GAME_COUNT_EXCEPTION_MESSAGE = "게임에 참여할 수 있는 유저는 최대 7명입니다.";

    private CardDeck cardDeck;

    public GameMachine(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Dealer createDealer() {
        return new Dealer(cardDeck.drawInitialCard());
    }

    public List<User> createUsers(List<String> users) {
        validateUserCount(users);
        return users.stream()
                .map(user -> new User(user, cardDeck.drawInitialCard()))
                .collect(Collectors.toList());
    }

    private static void validateUserCount(List<String> users) {
        if (users.size() > MAX_PLAY_GAME_COUNT) {
            throw new IllegalArgumentException(PLAY_GAME_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public boolean checkPlayerReceiveCard(Player player) {
        if (player.isPossibleToPickCard()) {
            drawCardToPlayer(player);
            return true;
        }
        return false;
    }

    public void drawCardToPlayer(Player player) {
        player.pickCard(cardDeck.drawCard());
    }

    public boolean haveBlackJack(List<User> users, Dealer dealer) {
        return dealer.isBlackJack() || users.stream()
                .anyMatch(Player::isBlackJack);
    }
}
