package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.BetAmount;
import blackjack.domain.result.Judge;
import blackjack.domain.result.ParticipantResults;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator) {
        this.gameRuleEvaluator = gameRuleEvaluator;
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = savePlayers(names);
        Dealer dealer = new Dealer(new Hand());

        BlackjackGame blackjackGame = new BlackjackGame(new Deck(), players);
        giveStartingCards(blackjackGame);

        players.getPlayers().forEach(participant -> giveMoreCard(participant, blackjackGame));

        Judge judge = new Judge(new ParticipantResults());
        ParticipantResults participantResults = calculateResultOfParticipants(players, dealer, judge);

        OutputView.printCardResult(participantResults);
        OutputView.printGameResult(participantResults);
    }

    private void giveMoreCard(Participant participant, BlackjackGame blackjackGame) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant, blackjackGame);
            return;
        }
        takeCardManually(participant, blackjackGame);
    }

    private void takeCardsAsLongAsWanted(Participant participant, BlackjackGame blackjackGame) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackGame.giveMoreCard(participant);
        OutputView.printCardResult(participant);

        if (gameRuleEvaluator.isBusted(participant)) {
            OutputView.printBustedParticipantWithName(participant);
            return;
        }

        if (participant.ableToTakeMoreCards()) {
            giveMoreCard(participant, blackjackGame);
        }
    }

    private void takeCardManually(Participant participant, BlackjackGame blackjackGame) {
        while (participant.ableToTakeMoreCards()) {
            OutputView.printMoreCard();
            blackjackGame.giveMoreCard(participant);
        }
    }


    private void giveStartingCards(BlackjackGame blackjackGame) {
        blackjackGame.giveStartingCards();

        OutputView.printStartingCardsStatuses(blackjackGame.getParticipants());
    }

    private ParticipantResults calculateResultOfParticipants(Players players, Dealer dealer, Judge judge) {
        judge.calculateAllResults(dealer, players, gameRuleEvaluator);
        return judge.getParticipantResults();
    }

    private Players savePlayers(List<String> names) {
        List<Player> players = new ArrayList<>();

        for (String name : names) {
            int rawBetAmount = InputView.askBetAmount(name);
            BetAmount betAmount = new BetAmount(rawBetAmount);

            players.add(new Player(name, new Hand(), betAmount));
        }

        return new Players(players);
    }
}
