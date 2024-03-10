package manager;

import domain.Name;
import domain.Names;
import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.game.Referee;
import domain.score.ScoreBoard;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Casino {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public void run() {
        Names names = readNames();

        Deck deck = new Deck();
        DealerCards dealerCards = new DealerCards(deck.drawInitialHands());
        List<PlayerCards> playerCardsBundle = makePlayerCards(names, deck);
        outputView.printInitialCards(dealerCards, playerCardsBundle);

        playGame(deck, dealerCards, playerCardsBundle);
        ScoreBoard scoreBoard = ScoreBoard.from(names);
        determineOutcome(dealerCards, playerCardsBundle, scoreBoard);
    }

    private void determineOutcome(DealerCards dealerCards, List<PlayerCards> playerCardsBundle, ScoreBoard scoreBoard) {
        Referee referee = new Referee(scoreBoard);
        referee.decideResult(dealerCards, playerCardsBundle);
        outputView.printResults(dealerCards, playerCardsBundle);
        outputView.printScores(scoreBoard);
    }

    private Names readNames() {
        try {
            List<Name> names = inputView.readPlayerNames()
                    .stream()
                    .map(Name::new)
                    .toList();
            return new Names(names);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readNames();
        }
    }

    private List<PlayerCards> makePlayerCards(Names names, Deck deck) {
        return names.getNames().stream()
                .map(name -> new PlayerCards(name, deck.drawInitialHands()))
                .toList();
    }

    private void playGame(Deck deck, DealerCards dealerCards, List<PlayerCards> playerCardsBundle) {
        for (PlayerCards playerCards : playerCardsBundle) {
            drawByOpinion(deck, playerCards);
        }
        drawDealerCards(deck, dealerCards);
    }

    private void drawByOpinion(Deck deck, PlayerCards playerCards) {
        boolean opinion = inputView.readHitOpinion(playerCards.getPlayerName());
        if (!opinion) {
            return;
        }
        drawPlayerCards(deck, playerCards);
        outputView.printPlayerCards(playerCards);
        if (playerCards.canDraw()) {
            drawByOpinion(deck, playerCards);
        }
    }

    private void drawDealerCards(Deck deck, DealerCards dealerCards) {
        while (dealerCards.canDraw()) {
            dealerCards.receive(deck.draw());
            outputView.printDealerGivenCard();
        }
    }

    private void drawPlayerCards(Deck deck, PlayerCards playerCards) {
        if (playerCards.canDraw()) {
            playerCards.receive(deck.draw());
        }
    }
}
