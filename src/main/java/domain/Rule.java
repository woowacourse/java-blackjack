package domain;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.Income;
import domain.result.Incomes;
import domain.result.Status;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Rule {

    private final Incomes incomes;

    private Rule(Incomes incomes) {
        this.incomes = incomes;
    }

    public static Rule of(Dealer dealer, List<Player> playerBundle) {
        Map<Name, Income> incomes = playerBundle.stream()
                .collect(Collectors.toMap(
                        Player::getPlayerName,
                        playerCards -> playerCards.determineIncome(decideStatus(playerCards, dealer)),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
        return new Rule(new Incomes(incomes));
    }

    public static Status decideStatus(Cards playerCards, Cards dealerCards) {
        if (checkPlayerLose(playerCards, dealerCards)) {
            return Status.LOSE;
        }
        if (checkPlayerBlackjack(playerCards, dealerCards)) {
            return Status.WIN_BLACKJACK;
        }
        if (checkPlayerWin(playerCards, dealerCards)) {
            return Status.WIN;
        }
        return Status.TIE;
    }

    private static boolean checkPlayerLose(Cards playerCards, Cards dealerCards) {
        return playerCards.isBurst() || dealerCards.isNotBurst() && dealerCards.isGreaterThan(playerCards);
    }

    private static boolean checkPlayerBlackjack(Cards playerCards, Cards dealerCards) {
        return playerCards.isBlackjack() && (dealerCards.isBurst() || playerCards.isGreaterThan(dealerCards));
    }

    private static boolean checkPlayerWin(Cards playerCards, Cards dealerCards) {
        return dealerCards.isBurst() || playerCards.isGreaterThan(dealerCards);
    }

    public Map<Name, Income> getIncomes() {
        return incomes.getIncomes();
    }

    public int getDealerIncome() {
        return incomes.getDealerIncome();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(incomes, rule.incomes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomes);
    }

    @Override
    public String toString() {
        return "Rule{" +
                "incomes=" + incomes +
                '}';
    }
}
