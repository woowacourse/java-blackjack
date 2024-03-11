package manager;

import domain.BetAmount;
import domain.Name;
import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.card.RandomDrawStrategy;
import domain.game.Rule;
import domain.score.ScoreBoard;
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

        ScoreBoard scoreBoard = playGame(names, dealerCards, playerCardsBundle);
        outputView.printResults(dealerCards, playerCardsBundle);

        outputView.printScores(scoreBoard);
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
                        (x,y) -> y,
                        LinkedHashMap::new
                ));
    }

    private List<PlayerCards> makePlayerCards(Map<Name, BetAmount> betAmounts) {
        return betAmounts.entrySet()
                .stream()
                .map(betAmount -> new PlayerCards(betAmount.getKey(), betAmount.getValue(), deck.drawInitialHands()))
                .toList();
    }

    private ScoreBoard playGame(List<Name> names, DealerCards dealerCards, List<PlayerCards> playerCardsBundle) {
        ScoreBoard scoreBoard = ScoreBoard.from(names);
        Rule rule = new Rule(scoreBoard);

        for (PlayerCards playerCards : playerCardsBundle) {
            drawByOpinion(playerCards);
        }
        drawDealerCards(dealerCards);

        rule.decideResult(dealerCards, playerCardsBundle);
        return scoreBoard;
    }


    private void drawByOpinion(PlayerCards playerCards) {
        boolean opinion = inputView.readHitOpinion(playerCards.getPlayerName());
        if (!opinion) {
            return;
        }
        draw(playerCards);
        outputView.printPlayerCards(playerCards);
        if (playerCards.canDraw()) {
            drawByOpinion(playerCards);
        }
    }

    private void draw(PlayerCards playerCards) {
        if (playerCards.canDraw()) {
            playerCards.receive(deck.draw());
        }
    }

    private void drawDealerCards(DealerCards dealerCards) {
        while (dealerCards.canDraw()) {
            dealerCards.receive(deck.draw());
            outputView.printDealerGivenCard();
        }
    }
}
