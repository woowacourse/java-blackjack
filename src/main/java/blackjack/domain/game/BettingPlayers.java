package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Player;

import java.util.*;

public class BettingPlayers {
    private static final double BLACK_JACK_PROFIT_RATE = 1.5;

    private final Map<Player, Money> players = new LinkedHashMap<>();

    public BettingPlayers(final List<Player> players, final List<Money> moneys) {
        validate(players, moneys);
        saveBettingInformation(players, moneys);
    }

    public Map<Player, Money> decideBetResultBy(final Dealer dealer) {
        players.keySet().forEach(player -> {
            final ResultType playerResult = dealer.judgeResult(player);
            final Money currentMoney = players.get(player);
            reflectPlayerMoney(player, playerResult, currentMoney);
        });
        return new HashMap<>(players);
    }

    private void saveBettingInformation(final List<Player> players, final List<Money> moneys) {
        final int playerSize = players.size();
        for (int index = 0; index < playerSize; index++) {
            final Player currentPlayer = players.get(index);
            final Money currentMoney = moneys.get(index);
            this.players.put(currentPlayer, currentMoney);
        }
    }

    private void reflectPlayerMoney(final Player player, final ResultType playerResult, final Money currentMoney) {
        reflectIfBlackJack(player, playerResult, currentMoney);
        reflectIfWin(player, playerResult, currentMoney);
        reflectIfLose(player, playerResult, currentMoney);
    }

    private void reflectIfBlackJack(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.BLACK_JACK) {
            final Money earnMoney = currentMoney.times(BLACK_JACK_PROFIT_RATE);
            players.put(player, currentMoney.earn(earnMoney));
        }
    }

    private void reflectIfWin(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.WIN) {
            players.put(player, currentMoney.earn(currentMoney));
        }
    }

    private void reflectIfLose(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.LOSE) {
            players.put(player, currentMoney.spend(currentMoney));
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players.keySet());
    }

    private void validate(final List<Player> players, final List<Money> moneys) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어 목록은 비어있을 수 없습니다.");
        }
        if (moneys.isEmpty()) {
            throw new IllegalArgumentException("돈 목록은 비어있을 수 없습니다.");
        }
    }
}
