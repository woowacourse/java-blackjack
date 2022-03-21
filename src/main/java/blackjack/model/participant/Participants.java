package blackjack.model.participant;

import blackjack.model.betting.BettingResult;
import blackjack.model.game.DrawStrategy;
import blackjack.model.game.GameSign;
import blackjack.model.game.MoneyBetter;
import blackjack.model.game.TurnProgress;
import java.util.ArrayList;
import java.util.List;
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

    public void drawFrom(final DrawStrategy drawStrategy) {
        players.forEach(player -> player.drawFrom(drawStrategy));
        dealer.drawFrom(drawStrategy);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.stream()
                .collect(Collectors.toUnmodifiableList()));
        return participants;
    }

    public void hitFrom(final DrawStrategy drawStrategy, final GameSign gameSign, final TurnProgress turnProgress) {
        players.forEach(player -> player.hitFrom(drawStrategy, gameSign, turnProgress));
        dealer.hitFrom(drawStrategy, gameSign, turnProgress);
    }

    public BettingResult createBettingResult() {
        return new BettingResult(dealer, players);
    }
}