package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Participants {

    private static final int MAX_PLAYER_COUNT = 8;
    private static final int NOT_CONTAINS_NUMBER = -1;
    private static final String ERROR_LIST_SIZE = "플레이어 이름과 배팅 금액이 매칭되지 않습니다.";
    private static final String ERROR_OVER_PLAYER_COUNT = String.format("게임에 참여하는 최대 인원은 %d명 입니다.", MAX_PLAYER_COUNT);

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(final List<Player> players, final Dealer dealer) {
        this.players = Collections.unmodifiableList(players);
        this.dealer = dealer;
        validatePlayers(players);
    }

    public Participants(List<Name> names, List<BettingAmount> bettingAmounts) {
        this.dealer = new Dealer();
        this.players = initPlayers(new ArrayList<>(names), new ArrayList<>(bettingAmounts));
        validatePlayers(players);
    }

    private void validatePlayers(List<Player> players) {
        if (players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(ERROR_OVER_PLAYER_COUNT);
        }
    }

    private List<Player> initPlayers(List<Name> names, List<BettingAmount> bettingAmounts) {
        if (names.size() != bettingAmounts.size()) {
            throw new IllegalArgumentException(ERROR_LIST_SIZE);
        }
        return IntStream.range(0, names.size())
                .mapToObj(i -> new Player(names.get(i), bettingAmounts.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCard(Participant participant, Card card) {
        int index = players.indexOf(participant);

        if (index == NOT_CONTAINS_NUMBER) {
            dealer.drawCard(card);
            return;
        }
        players.get(index).drawCard(card);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
