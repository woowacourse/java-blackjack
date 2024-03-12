package controller;

import static view.CardName.getHandStatusAsString;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
import controller.dto.gamer.GamerHandStatus;
import domain.Dealer;
import domain.Deck;
import domain.GameRule;
import domain.Gamer;
import domain.Gamers;
import java.util.ArrayList;
import java.util.List;
import view.CardName;
import view.InputView;
import view.OutputView;

public class Game {
    private final Dealer dealer = new Dealer();
    private final Gamers gamers;

    public Game(final List<String> gamerNames) {
        this.gamers = new Gamers(gamerNames.stream()
                .map(Gamer::new)
                .toList());
    }

    public void readyGame(final OutputView outputView) {
        initiateGameCondition(dealer, gamers);
        printInitiateGameResult(outputView);
    }

    private void initiateGameCondition(final Dealer dealer, final Gamers gamers) {
        Deck.shuffle();

        dealer.pickTwoCards();
        for (Gamer gamer : gamers.listOf()) {
            gamer.pickTwoCards();
        }
    }

    private void printInitiateGameResult(final OutputView outputView) {
        outputView.printNoticeAfterStartGame(gamers.getNames());
        outputView.printDealerStatusAfterStartGame(CardName.getDealerHandStatusWithHiddenCard(dealer.getHand()));
        outputView.printPlayerStatusAfterStartGame(gamers.getNames(), CardName.getGamerHandStatus(gamers));
    }

    public void playGame(final InputView inputView, final OutputView outputView) {
        for (Gamer gamer : gamers.listOf()) {
            giveCardToGamer(gamer, outputView, inputView);
        }
    }

    private void giveCardToGamer(final Gamer gamer, final OutputView outputView, final InputView inputView) {
        GamerHandStatus currentHand = new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
        GameCommand command = inputCommand(gamer.getName(), inputView);

        while (command.isHit()) {
            gamer.pickOneCard();
            currentHand = new GamerHandStatus(gamer.getName(), getHandStatusAsString(gamer.getHand()));
            if (gamer.isNotAbleToDrawCard()) {
                break;
            }
            outputView.printCardStatus(gamer.getName(), currentHand);
            command = inputCommand(gamer.getName(), inputView);
        }
        outputView.printCardStatus(gamer.getName(), currentHand);
    }

    private GameCommand inputCommand(final String name, final InputView inputView) {
        return GameCommand.valueOf(inputView.decideToGetMoreCard(name));
    }

    public void printDealerDrawMessage(final OutputView outputView) {
        int count = dealer.cardDrawCount();
        outputView.printDealerPickMessage(count);
    }

    public void printResult(final OutputView outputView) {
        printCardStatusAndScores(outputView);
        outputView.printGameResult(getResults());
    }

    private void printCardStatusAndScores(final OutputView outputView) {
        outputView.printHandStatusWithScore(
                dealer.getCurrentDealerHandScore(),
                gamers.getCurrentGamerHandScore(),
                gamers.getNames()
        );
    }

    private GameResult getResults() {
        GameRule rule = new GameRule(dealer, gamers);
        List<Boolean> results = rule.judge();
        List<String> names = gamers.getNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }
}
