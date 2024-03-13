package manager;

import domain.BetAmount;
import domain.result.Income;
import domain.Name;
import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.card.RandomDrawStrategy;
import domain.Rule;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackGameManager {

    private final Deck deck = new Deck(new RandomDrawStrategy());

    public void run() {
        List<Name> names = readNames();
        Map<Name, BetAmount> betAmounts = readBetAmounts(names);

        DealerCards dealerCards = new DealerCards(deck.drawInitialHands());
        System.out.println(dealerCards);
        List<PlayerCards> playerCardsBundle = makePlayerCards(betAmounts);
        printInitialCards(dealerCards, playerCardsBundle);

        readPlayersHit(playerCardsBundle);

        drawDealerCards(dealerCards);

        printResults(dealerCards, playerCardsBundle);

        printFinalIncomes(dealerCards, playerCardsBundle);
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
                        name -> name,
                        name -> new BetAmount(readBetAmount(name)),
                        (x, y) -> y,
                        LinkedHashMap::new
                ));
    }

    private List<PlayerCards> makePlayerCards(Map<Name, BetAmount> betAmounts) {
        return betAmounts.entrySet()
                .stream()
                .map(betAmount -> new PlayerCards(betAmount.getKey(), betAmount.getValue(), deck.drawInitialHands()))
                .toList();
    }

    private void readPlayersHit(List<PlayerCards> playerCardsBundle) {
        for (PlayerCards playerCards : playerCardsBundle) {
            readPlayerHit(playerCards);
        }
    }

    private void readPlayerHit(PlayerCards playerCards) {
        while (playerCards.canDraw()) {
            boolean opinion = readHitOpinion(playerCards.getPlayerName());
            if (!opinion) {
                printPlayerCards(playerCards);
                System.out.println();
                return;
            }
            playerCards.receive(deck.draw());
            printPlayerCards(playerCards);
            System.out.println();
        }
    }

    private void drawDealerCards(DealerCards dealerCards) {
        while (dealerCards.canDraw()) {
            dealerCards.receive(deck.draw());
            printDealerGivenCard();
        }
    }

    private void printFinalIncomes(DealerCards dealerCards, List<PlayerCards> playerCardsBundle) {
        Rule rule = Rule.of(dealerCards, playerCardsBundle);
        Map<Name, Income> incomes = rule.getIncomes();
        printIncomes(rule.getDealerIncome(), incomes);
    }
}
