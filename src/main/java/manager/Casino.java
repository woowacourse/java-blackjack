package manager;

import domain.card.DealerCards;
import domain.card.Deck;
import domain.card.PlayerCards;
import domain.game.Referee;
import domain.player.Bet;
import domain.player.Name;
import domain.player.Names;
import domain.score.ScoreBoard;
import view.InputView;
import view.OutputView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Casino {

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();

    public void run() {
        Names names = readNames();
        Map<Name, Bet> bets = readBets(names);

        Deck deck = new Deck();
        ScoreBoard scoreBoard = new ScoreBoard(bets);
        DealerCards dealerCards = new DealerCards(deck.drawTwoCards());
        List<PlayerCards> playerCardsBundle = makePlayerCards(names, deck);
        outputView.printInitialCards(dealerCards, playerCardsBundle);

        Referee referee = new Referee(scoreBoard);
        playGame(deck, dealerCards, playerCardsBundle);
        determineOutcome(dealerCards, playerCardsBundle, referee);
        outputView.printScores(scoreBoard);
    }

    private Map<Name, Bet> readBets(Names names) {
        Map<Name, Bet> bets = new HashMap<>();
        for (Name name : names.getNames()) {
            Bet bet = readBet(name);
            bets.put(name, bet);
        }
        return Collections.unmodifiableMap(bets);
    }

    private Bet readBet(Name name) {
        try {
            int bet = inputView.readBet(name);
            return new Bet(bet);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return readBet(name);
        }
    }

    private void determineOutcome(DealerCards dealerCards, List<PlayerCards> playerCardsBundle, Referee referee) {
        referee.decideResult(dealerCards, playerCardsBundle);
        outputView.printResults(dealerCards, playerCardsBundle);
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
                .map(name -> new PlayerCards(name, deck.drawTwoCards()))
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
        if (opinion) {
            drawPlayerCards(deck, playerCards);
        }
        outputView.printPlayerCards(playerCards);
        if (!playerCards.canDraw() || !opinion) {
            return;
        }
        drawByOpinion(deck, playerCards);
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
