package blackjack.domain.user;

import blackjack.domain.card.deck.Deck;
import java.util.List;

public class Players {

    private static final int MAX_PLAYER = 10;
    private static final String TOO_MANY_GAMER_ERROR_MESSAGE = "참여할 수 있는 플레이어 수는 1명 이상 " + MAX_PLAYER + "명 이하만 가능합니다.";
    private static final String DUPLICATE_NAME_ERROR_MESSAGE = "플레이어의 이름이 중복되었습니다.";
    private static final String SAME_DEALER_NAME_ERROR_MESSAGE = "플레이어의 이름가 딜러면 안된다.";
    private static final String DEALER_NAME = "딜러";

    private final List<User> users;

    public Players(List<User> users) {
        validateSize(users);
        validateDuplicateName(users);
        validateSameDealerName(users);

        this.users = users;
    }

    private void validateSize(List<User> users) {
        if (users.size() > MAX_PLAYER || users.isEmpty()) {
            throw new IllegalArgumentException(TOO_MANY_GAMER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateName(List<User> users) {
        int distinctNameCount = (int) users.stream()
                .map(gamer -> gamer.getName().get())
                .distinct()
                .count();

        if (users.size() != distinctNameCount) {
            throw new IllegalArgumentException(DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private void validateSameDealerName(List<User> users) {
        boolean hasSameDealerName = users.stream()
                .anyMatch(gamer -> gamer.getName().get().equals(DEALER_NAME));

        if (hasSameDealerName) {
            throw new IllegalArgumentException(SAME_DEALER_NAME_ERROR_MESSAGE);
        }
    }

    public void takeInitHand(Deck deck) {
        for (User user : users) {
            user.takeInitHand(deck);
        }
    }

    public List<User> get() {
        return users;
    }
}
