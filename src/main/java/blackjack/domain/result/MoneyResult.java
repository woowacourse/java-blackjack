package blackjack.domain.result;

import java.util.stream.Collectors;

import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.money.Money;
import blackjack.domain.rule.BasicRule;

public class MoneyResult extends Result {

    public MoneyResult(final Participants participants) {
        super(participants);
        for (Participant player : players) {
            decideWinner(player);
        }
    }

    @Override
    protected void execute(final Participant player, final Status playerStatus) {
        Money playerMoney = player.getMoney();
        if (playerStatus == Status.LOSE) {
            dealer.earn(playerMoney);
            player.loseAll();
        } else if (playerStatus == Status.DRAW) {
            player.lose(playerMoney);
        } else if (playerStatus == Status.WIN) {
            handleBlackJack(player, playerMoney);
        }
    }

    private void handleBlackJack(final Participant player, final Money playerMoney) {
        if (BasicRule.isBlackJack(player.score(), player.cardCount())) {
            playerMoney.multiply(BasicRule.BLACK_JACK_PROFIT);
        }
        dealer.lose(player.getMoney());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dealer.getName()).append(": ");
        stringBuilder.append(dealer.getMoney());
        stringBuilder.append(LINE_BREAK);
        stringBuilder.append(players.stream()
            .map(player -> player.getName() + ": " + player.getMoney())
            .collect(Collectors.joining(LINE_BREAK)));
        return stringBuilder.toString();
    }
}
