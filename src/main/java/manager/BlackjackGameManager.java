package manager;

import domain.card.Deck;
import domain.participant.BetAmount;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.result.Income;
import domain.result.Incomes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackGameManager {

    private final Deck deck = new Deck();

    public void run() {
        List<Name> names = readNames();
        Map<Name, BetAmount> betAmounts = readBetAmounts(names);

        Dealer dealer = new Dealer(deck.drawInitialHands());
        List<Player> playerBundle = makePlayerCards(betAmounts);
        printInitialCards(dealer, playerBundle);

        readPlayersHit(playerBundle);

        drawDealerCards(dealer);

        printResults(dealer, playerBundle);

        Incomes incomes = new Incomes(playerBundle);

        printFinalIncomes(incomes.dealerIncome(dealer), incomes.playersIncomes(dealer));
    }

    private List<Name> readNames() {
        return readPlayerNames()
                .stream()
                .map(Name::new)
                .toList();
    }

    private Map<Name, BetAmount> readBetAmounts(List<Name> names) {
        return names.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        name -> new BetAmount(readBetAmount(name)),
                        (oldValue, newValue) -> newValue,
                        LinkedHashMap::new
                ));
    }

    private List<Player> makePlayerCards(Map<Name, BetAmount> betAmounts) {
        return betAmounts.entrySet()
                .stream()
                .map(betAmount -> new Player(betAmount.getKey(), betAmount.getValue(), deck.drawInitialHands()))
                .toList();
    }

    private void readPlayersHit(List<Player> playerBundle) {
        for (Player player : playerBundle) {
            readPlayerHit(player);
        }
    }

    private void readPlayerHit(Player player) {
        while (player.canDraw()) {
            boolean opinion = readHitOpinion(player.getPlayerName());
            if (!opinion) {
                printPlayerCards(player);
                System.out.println();
                return;
            }
            player.receive(deck.draw());
            printPlayerCards(player);
            System.out.println();
        }
    }

    private void drawDealerCards(Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.receive(deck.draw());
            printDealerGivenCard();
        }
    }

    private void printFinalIncomes(Income dealerIncome, Map<Player, Income> playersIncomes) {
        printIncomes(dealerIncome.getIncome(), playersIncomes);
    }
}
