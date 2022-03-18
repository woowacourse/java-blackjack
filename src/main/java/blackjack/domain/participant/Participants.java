package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    private static final int MAX_PLAYER_COUNT = 8;
    private static final int NOT_CONTAINS_NUMBER = -1;
    private static final String ERROR_OVER_PLAYER_COUNT = String.format("게임에 참여하는 최대 인원은 %d명 입니다.", MAX_PLAYER_COUNT);

    private final List<Player> players;
    private final Dealer dealer;

    public Participants(final List<Player> players, final Dealer dealer) {
        this.players = Collections.unmodifiableList(players);
        this.dealer = dealer;
        validatePlayers(players);
    }

    public Participants(Map<Name, BettingAmount> participantInfos) {
        this.dealer = new Dealer();
        this.players = initPlayers(participantInfos);
        validatePlayers(players);
    }

    private void validatePlayers(List<Player> players) {
        if (players.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(ERROR_OVER_PLAYER_COUNT);
        }
    }

    private List<Player> initPlayers(Map<Name, BettingAmount> participantInfos) {
        return participantInfos.entrySet()
                .stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
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
