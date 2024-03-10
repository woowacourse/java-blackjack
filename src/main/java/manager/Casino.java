package manager;

import domain.Name;
import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.game.Rule;
import domain.score.ScoreBoard;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Casino {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final Deck deck = new Deck();

    public void run() {
        List<Name> names = readNames();

        DealerCards dealerCards = new DealerCards(deck.drawInitialHands());
        List<PlayerCards> playerCardsBundle = makePlayerCards(names);
        outputView.printInitialCards(dealerCards, playerCardsBundle);

        ScoreBoard scoreBoard = playGame(names, dealerCards, playerCardsBundle);
        outputView.printResults(dealerCards, playerCardsBundle);

        outputView.printScores(scoreBoard);
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

    private List<PlayerCards> makePlayerCards(List<Name> names) {
        return names.stream()
                .map(name -> new PlayerCards(name, deck.drawInitialHands()))
                .toList();
    }

    private List<Name> readNames() {
        return inputView.readPlayerNames()
                .stream()
                .map(Name::new)
                .toList();
    }

    private void drawDealerCards(DealerCards dealerCards) {
        while (dealerCards.canDraw()) {
            dealerCards.receive(deck.draw());
            outputView.printDealerGivenCard();
        }
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
}
