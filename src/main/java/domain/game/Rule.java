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

    private static final int STANDARD = 21;

    private final Incomes incomes;

    private Rule(Incomes incomes) {
        this.incomes = incomes;
    }

    public static Rule of(DealerCards dealerCards, List<PlayerCards> playerCardsBundle) {
        Map<Name, Income> incomes = playerCardsBundle.stream()
                .collect(Collectors.toMap(
                        playerCards -> playerCards.getPlayerName(),
                        playerCards -> new Income(playerCards.determineIncome(decideStatus(playerCards, dealerCards))),
                        (x,y) -> y,
                        LinkedHashMap::new
                ));
        return new Rule(new Incomes(incomes));
    }

    public static Status decideStatus(Cards targetCards, Cards otherCards) {
        if (targetCards.bestSum() > STANDARD) {
            return Status.LOSE;
        }

        if (otherCards.bestSum() > STANDARD) {
            return Status.WIN;
        }

        if (otherCards.bestSum() < targetCards.bestSum()) {
            return Status.WIN;
        }

        if (otherCards.bestSum() > targetCards.bestSum()) {
            return Status.LOSE;
        }
        return Status.TIE;
    }

    public Map<Name, Income> getIncomes() {
        return incomes.getIncomes();
    }

    public int getDealerIncome() {
        return incomes.getDealerIncome();
    }
}
