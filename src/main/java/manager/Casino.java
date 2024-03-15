package manager;

import domain.game.BlackjackGame;
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
        BlackjackGame game = BlackjackGame.from(names);
        outputView.printInitialCards(game);

        play(game, names);
        ScoreBoard scoreBoard = game.payout(bets);
        outputView.printResults(game);
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

    private void play(BlackjackGame game, Names names) {
        for (Name name : names.getNames()) {
            drawByOpinion(game, name);
        }
        while (game.dealerCanDraw()) {
            game.drawDealerCards();
            outputView.printDealerGivenCard();
        }
    }

    private void drawByOpinion(BlackjackGame game, Name name) {
        boolean opinionToHit = inputView.readHitOpinion(name);
        if (opinionToHit) {
            game.drawPlayerCards(name);
        }
        outputView.printPlayerCards(name, game.player(name));
        if (opinionToHit && game.playerCanDraw(name)) {
            drawByOpinion(game, name);
        }
    }
}
