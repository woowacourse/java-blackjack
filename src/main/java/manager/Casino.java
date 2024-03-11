package manager;

import domain.BetAmount;
import domain.Name;
import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.card.RandomDrawStrategy;
import domain.game.Rule;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Casino {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Deck deck = new Deck(new RandomDrawStrategy());

    public void run() {
        List<Name> names = readNames();
        Map<Name, BetAmount> betAmounts = readBetAmounts(names);

        DealerCards dealerCards = new DealerCards(deck.drawInitialHands());
        List<PlayerCards> playerCardsBundle = makePlayerCards(betAmounts);
        outputView.printInitialCards(dealerCards, playerCardsBundle);

        for (PlayerCards playerCards : playerCardsBundle) {
            while (playerCards.canDraw() && inputView.readHitOpinion(playerCards.getPlayerName())) {
                playerCards.receive(deck.draw());
                outputView.printPlayerCards(playerCards);
            }
        }

        drawDealerCards(dealerCards);


        outputView.printResults(dealerCards, playerCardsBundle);

        Rule rule = Rule.of(dealerCards, playerCardsBundle);
    }

    private List<Name> readNames() {
        return inputView.readPlayerNames()
                .stream()
                .map(Name::new)
                .toList();
    }

    private Map<Name, BetAmount> readBetAmounts(List<Name> names) {
        return names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        name -> new BetAmount(inputView.readBetAmount(name)),
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
    
    private void drawDealerCards(DealerCards dealerCards) {
        while (dealerCards.canDraw()) {
            dealerCards.receive(deck.draw());
            outputView.printDealerGivenCard();
        }
    }
}
