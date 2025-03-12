package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Players;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
import blackjack.manager.BlackJackInitManager;
import blackjack.manager.BlackjackProcessManager;
import blackjack.manager.GameRuleEvaluator;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final GameRuleEvaluator gameRuleEvaluator;
    private final BlackJackInitManager blackJackInitManager;
    private final BlackjackProcessManager blackjackProcessManager;

    public BlackjackController(GameRuleEvaluator gameRuleEvaluator, BlackJackInitManager blackJackInitManager) {
        this.gameRuleEvaluator = gameRuleEvaluator;
        this.blackJackInitManager = blackJackInitManager;
        this.blackjackProcessManager = new BlackjackProcessManager(blackJackInitManager.generateDeck(),
                PlayersResults.create());
    }

    public void run() {
        List<String> names = InputView.readNames();

        Players players = blackJackInitManager.savePlayers(names);
        Dealer dealer = blackJackInitManager.saveDealer();

        List<PlayerResult> playerResults = getCardResultOfPlayer(players, dealer);
        DealerResult dealerResult = getCardResultOfDealer(dealer);

        OutputView.printCardResult(playerResults, dealerResult, dealer);
        OutputView.printGameResult(dealerResult, playerResults);

        //TODO: 위의 로직 제거

        Participants participants = blackJackInitManager.saveParticipants(names);

        giveStartingCards(participants);
        participants.getParticipants().forEach(this::giveMoreCard);

    }

    private void giveMoreCard(Participant participant) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant);
            return;
        }
        takeCardManually(participant);
    }

    private void takeCardsAsLongAsWanted(Participant participant) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackProcessManager.giveCard(participant);
        OutputView.printCardResult(participant);

        if (gameRuleEvaluator.isBusted(participant)) {
            OutputView.printBustedParticipantWithName(participant);
            return;
        }

        if (participant.ableToTakeMoreCards()) {
            giveMoreCard(participant);
        }
    }

    private void takeCardManually(Participant participant) {
        while (participant.ableToTakeMoreCards()) {
            OutputView.printMoreCard();
            blackjackProcessManager.giveCard(participant);
        }
    }

    private void giveStartingCards(Participants participants) {
        blackjackProcessManager.giveStartingCards(participants);

        OutputView.printStartingCardsStatuses(participants);
    }

    private List<PlayerResult> getCardResultOfPlayer(Players players, Dealer dealer) {
        blackjackProcessManager.calculateCardResult(players, dealer, gameRuleEvaluator);
        return blackjackProcessManager.getPlayersResult();
    }

    private DealerResult getCardResultOfDealer(Dealer dealer) {
        return blackjackProcessManager.calculateDealerResult(dealer);
    }
}
