package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.game.GameSign;
import blackjack.model.game.MoneyBetter;
import blackjack.model.game.TurnProgress;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {
    private static final int PLAYER_COUNT_LIMIT = 8;

    private final List<Participant> players;
    private final Participant dealer;

    public Participants(final List<String> names, final MoneyBetter moneyBetter) {
        checkPlayersCount(names);
        checkDuplicateIn(names);
        this.players = names.stream()
                .map(name -> new Player(name, moneyBetter.orderToBet(name)))
                .collect(Collectors.toUnmodifiableList());
        this.dealer = new Dealer();
    }

    private void checkPlayersCount(final List<String> names) {
        if (names.size() > PLAYER_COUNT_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 참가자는 최대 8명 까지 참여 가능합니다.");
        }
    }

    private void checkDuplicateIn(final List<String> names) {
        if (countDuplicateRemove(names) != names.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
        }
    }

    private int countDuplicateRemove(final List<String> names) {
        return (int) names.stream()
                .distinct()
                .count();
    }

    public void drawFrom(final CardDeck cardDeck) {
        players.forEach(player -> player.drawFrom(cardDeck));
        dealer.drawFrom(cardDeck);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.stream()
                .collect(Collectors.toUnmodifiableList()));
        return participants;
    }

    public void hitFrom(final CardDeck cardDeck, final GameSign gameSign, final TurnProgress turnProgress) {
        players.forEach(player -> player.hitFrom(cardDeck, gameSign, turnProgress));
        dealer.hitFrom(cardDeck, gameSign, turnProgress);
    }

    public Map<String, Double> createBettingResult() {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Participant player : players) {
            result.put(player.getName(), player.getProfit(dealer));
        }
        return result;
    }
}