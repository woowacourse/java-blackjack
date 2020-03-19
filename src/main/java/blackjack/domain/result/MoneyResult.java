package blackjack.domain.result;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.money.Money;
import blackjack.domain.rule.BasicRule;

public class MoneyResult extends Result {

    private MoneyChanger moneyChanger;
    private Map<Participant, Money> moneyResult;

    public MoneyResult(final Participants participants, MoneyChanger moneyChanger) {
        super(participants);
        this.moneyChanger = moneyChanger;
        this.moneyResult = new HashMap<>();
        this.moneyResult.put(dealer, Money.zero());
        for (Participant player : players) {
            decideWinner(player);
        }
    }

    @Override
    protected void execute(final Participant player, final Status playerStatus) {
        Money playerMoney = moneyChanger.get(player);
        Money dealerMoney = moneyResult.getOrDefault(dealer, Money.zero());
        if (playerStatus == Status.LOSE) {
            moneyResult.put(player, playerMoney.getOpposite());
            moneyResult.put(dealer, dealerMoney.add(playerMoney));
        }
        if (playerStatus == Status.DRAW) {
            moneyResult.put(player, Money.zero());
        }
        if (playerStatus == Status.WIN) {
            handleBlackJack(player, playerMoney, dealerMoney);
        }
    }

    private void handleBlackJack(final Participant player, final Money playerMoney, final Money dealerMoney) {
        if (BasicRule.isBlackJack(player.score(), player.cardCount())) {
            Money playerPrize = playerMoney.multiply(BasicRule.BLACK_JACK_EARNING);
            moneyResult.put(player, playerPrize);
            moneyResult.put(dealer, dealerMoney.subtract(playerPrize));
            return;
        }
        moneyResult.put(player, playerMoney);
        moneyResult.put(dealer, dealerMoney.subtract(playerMoney));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.getName()).append(": ");
        stringBuilder.append(moneyResult.get(dealer));
        stringBuilder.append(LINE_BREAK);
        stringBuilder.append(players.stream()
            .map(player -> player.getName() + ": " + moneyResult.get(player))
            .collect(Collectors.joining(LINE_BREAK)));
        return stringBuilder.toString();
    }
}
