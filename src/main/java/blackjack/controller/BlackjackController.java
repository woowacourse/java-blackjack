package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.GameRuleEvaluator;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.Judge;
import blackjack.domain.result.ParticipantResults;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final Judge judge;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, Judge judge) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.judge = judge;
    }

    public void run() {
        List<String> names = InputView.readNames();
        Players players = savePlayers(names);
        Dealer dealer = new Dealer(new Hand());

        BlackjackGame blackjackGame = new BlackjackGame(new Deck(), players);
        giveStartingCards(blackjackGame);

        players.getPlayers().forEach(participant -> giveMoreCard(participant, blackjackGame));

        ParticipantResults participantResults = calculateResultOfParticipants(players, dealer);

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

    private ParticipantResults calculateResultOfParticipants(Players players, Dealer dealer) {
        judge.calculateAllResults(dealer, players, gameRuleEvaluator);
        return judge.getParticipantResults();
    }

    private Players savePlayers(List<String> names) {
        List<Player> players = new java.util.ArrayList<>(names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList());
        return new Players(players);
    }
}
