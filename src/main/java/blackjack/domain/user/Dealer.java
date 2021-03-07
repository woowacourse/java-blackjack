package blackjack.domain.user;

import blackjack.domain.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Dealer extends User {

    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final String DELIMITER = ", ";
    private static final String NO_DEALER_CARD_ERROR_MESSAGE = "카드가 없습니다.";

    public Dealer() {
        super();
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_THRESHOLD;
    }

    public List<Integer> calculateMatchResult(Map<Player, Status> result) {
        List<Integer> winnings = new ArrayList<>();
        Arrays.stream(Status.values())
            .forEach(status -> winnings.add(Collections.frequency(result.values(), status)));
        return winnings;
    }

    public String showFirstCard() {
        return name + CARDS_GUIDE_MESSAGE + this.getFirstCard();
    }

    public String getFirstCard() {
        return Arrays.stream(showCards().split(DELIMITER))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                NO_DEALER_CARD_ERROR_MESSAGE));
    }
}
