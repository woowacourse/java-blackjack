package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Money;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class BettingPlayers {
    private static final double BLACK_JACK_PROFIT_RATE = 1.5;

    private final Map<Player, Money> players = new LinkedHashMap<>();

    public BettingPlayers(final Deck deck, final List<String> nameValues, final List<Integer> moneyValues) {
        validate(nameValues, moneyValues);
        saveProfitInformation(deck, nameValues, moneyValues);
    }

    private void validate(final List<String> players, final List<Integer> moneys) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("이름 목록은 비어있을 수 없습니다.");
        }
        if (moneys.isEmpty()) {
            throw new IllegalArgumentException("돈 목록은 비어있을 수 없습니다.");
        }
        if (players.size() != moneys.size()) {
            throw new IllegalArgumentException("이름 목록과 돈 목록의 크기는 다를 수 없습니다.");
        }
    }

    private void saveProfitInformation(final Deck deck, final List<String> nameValues, final List<Integer> moneyValues) {
        final int size = nameValues.size();
        IntStream.range(0, size).forEach(index -> {
            final String nameValue = nameValues.get(index);
            final int moneyValue = moneyValues.get(index);
            final Player player = new Player(deck, nameValue);
            final Money money = new Money(moneyValue);
            players.put(player, money);
        });
    }

    public Map<Participant, Money> findBettingResultsBy(final Dealer dealer) {
        players.keySet().forEach(player -> {
            final ResultType playerResult = dealer.judgeResult(player);
            final Money currentMoney = players.get(player);
            reflectPlayerProfit(player, playerResult, currentMoney);
        });
        return new LinkedHashMap<>(players);
    }

    private void reflectPlayerProfit(final Player player, final ResultType playerResult, final Money currentMoney) {
        reflectIfBlackJack(player, playerResult, currentMoney);
        reflectIfWin(player, playerResult, currentMoney);
        reflectIfLose(player, playerResult, currentMoney);
        reflectIfPush(player, playerResult);
    }

    private void reflectIfBlackJack(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.BLACK_JACK) {
            final Money earnMoney = currentMoney.times(BLACK_JACK_PROFIT_RATE);
            players.put(player, earnMoney);
        }
    }

    private void reflectIfWin(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.WIN) {
            players.put(player, currentMoney);
        }
    }

    private void reflectIfLose(final Player player, final ResultType playerResult, final Money currentMoney) {
        if (playerResult == ResultType.LOSE) {
            players.put(player, currentMoney.times(-1.0));
        }
    }

    private void reflectIfPush(final Player player, final ResultType playerResult) {
        if (playerResult == ResultType.PUSH) {
            players.put(player, Money.ZERO);
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players.keySet());
    }
}
