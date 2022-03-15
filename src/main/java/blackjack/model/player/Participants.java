package blackjack.model.player;

import java.util.List;

public class Participants {
    private final Players players;
    private final Participant dealer;

    public Participants(final List<String> names) {
        checkDuplicateIn(names);
        this.players = new Players(names);
        this.dealer = new Dealer();
    }

    private void checkDuplicateIn(final List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
        }
    }
}
