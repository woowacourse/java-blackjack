package blackjack.domain.participant;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Hand;
import blackjack.domain.money.Money;
import blackjack.domain.result.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Players {

    private static final int MIN_PLAYERS_COUNT = 2;
    private static final int MAX_PLAYERS_COUNT = 8;
    static final String PLAYERS_COUNT_ERROR_MESSAGE = "플레이어는 최소 " + MIN_PLAYERS_COUNT + "명에서 최대 " + MAX_PLAYERS_COUNT + "명입니다. 입력값: ";

    private final List<Player> players;

    public Players(final List<Player> players) {
        isValidCount(players);
        this.players = new ArrayList<>(players);
    }

    private void isValidCount(final List<Player> players) {
        if (players.size() < MIN_PLAYERS_COUNT || players.size() > MAX_PLAYERS_COUNT) {
            throw new IllegalArgumentException(PLAYERS_COUNT_ERROR_MESSAGE + players);
        }
    }

    public void distributeHands(final List<Hand> hands) {
        validateCardsSize(hands);

        for (int i = 0; i < hands.size(); i++) {
            Hand hand = hands.get(i);
            Player player = players.get(i);

            player.receiveHand(hand);
        }
    }

    private void validateCardsSize(final List<Hand> cards) {
        if (cards.size() != players.size()) {
            throw new IllegalArgumentException("세팅 카드와 플레이어의 수가 일치하지 않습니다.");
        }
    }

    public Map<Player, Result> compareHandTo(Hand dealerHand) {
        return players.stream()
                .collect(toMap(
                        player -> player,
                        player -> player.compareHandTo(dealerHand)
                ));
    }

    public void dealOutMoney(final Map<Player, Money> exchanges) {
        for (Player player : exchanges.keySet()) {
            player.receiveMoney(exchanges.get(player));
        }
    }

    public int size() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return List.copyOf(this.players);
    }
}
