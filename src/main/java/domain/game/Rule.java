package domain.game;

import domain.Income;
import domain.Incomes;
import domain.Name;
import domain.card.Cards;
import domain.card.DealerCards;
import domain.card.PlayerCards;
import domain.score.Status;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rule {

    private final Incomes incomes;

    private Rule(Incomes incomes) {
        this.incomes = incomes;
    }

    public static Rule of(DealerCards dealerCards, List<PlayerCards> playerCardsBundle) {
        Map<Name, Income> incomes = playerCardsBundle.stream()
                .collect(Collectors.toMap(
                        playerCards -> playerCards.getPlayerName(),
                        playerCards -> new Income(playerCards.determineIncome(decideStatus(playerCards, dealerCards))),
                        (x, y) -> y,
                        LinkedHashMap::new
                ));
        return new Rule(new Incomes(incomes));
    }

    public static Status decideStatus(Cards targetCards, Cards otherCards) {
        if (checkTargetLose(targetCards, otherCards)) {
            return Status.LOSE;
        }
        if (checkTargetBlackjack(targetCards, otherCards)) {
            return Status.WIN_BLACKJACK;
        }
        if (checkTargetWin(targetCards, otherCards)) {
            return Status.WIN;
        }
        return Status.TIE;
    }

    private static boolean checkTargetLose(Cards targetCards, Cards otherCards) {
        return targetCards.isBurst() || otherCards.isGreaterThan(targetCards);
    }

    private static boolean checkTargetBlackjack(Cards targetCards, Cards otherCards) {
        return targetCards.isBlackjack() && targetCards.isGreaterThan(otherCards);
    }

    private static boolean checkTargetWin(Cards targetCards, Cards otherCards) {
        return otherCards.isBurst() || targetCards.isGreaterThan(otherCards);
    }

    public Map<Name, Income> getIncomes() {
        return incomes.getIncomes();
    }

    public int getDealerIncome() {
        return incomes.getDealerIncome();
    }
}
