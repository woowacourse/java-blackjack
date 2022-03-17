package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private static final int MAX_PLAY_GAME_COUNT = 7;
    private static final String PLAY_GAME_COUNT_EXCEPTION_MESSAGE = "게임에 참여할 수 있는 유저는 최대 7명입니다.";

    private final CardDeck cardDeck;

    public GameMachine(final CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Dealer createDealer() {
        return new Dealer(cardDeck.selectOriginalCard());
    }

    public Users createUsers(final List<String> userNames) {
        validateUserCount(userNames);
        List<User> users = userNames.stream()
                .map(userName -> new User(userName, cardDeck.selectOriginalCard()))
                .collect(Collectors.toList());
        return new Users(users);
    }

    private static void validateUserCount(final List<String> users) {
        if (users.size() > MAX_PLAY_GAME_COUNT) {
            throw new IllegalArgumentException(PLAY_GAME_COUNT_EXCEPTION_MESSAGE);
        }
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
