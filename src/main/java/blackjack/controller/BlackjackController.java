package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Hand;
import blackjack.domain.game.Participant;
import blackjack.domain.game.Player;
import blackjack.domain.game.Players;
import blackjack.domain.result.BetAmount;
import blackjack.domain.profit.BettingResult;
import blackjack.domain.profit.DealerProfits;
import blackjack.domain.result.DealerResults;
import blackjack.domain.result.Judge;
import blackjack.domain.profit.PlayerProfits;
import blackjack.domain.result.PlayerResults;
import blackjack.domain.result.Score;
import blackjack.view.Confirmation;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public void run() {
        List<String> names = InputView.readNames();
        Players players = savePlayers(names);
        Dealer dealer = new Dealer(new Hand());

        BlackjackGame blackjackGame = new BlackjackGame(new Deck(), players, new Dealer(new Hand()));
        giveStartingCards(blackjackGame);

        players.getPlayers().forEach(participant -> giveMoreCard(participant, blackjackGame));
        takeCardManually(dealer, blackjackGame);

        Judge judge = new Judge(new DealerResults(), new PlayerResults());
        judge.calculateAllResults(dealer, players);

        DealerResults dealerResults = judge.getDealerResults();
        PlayerResults playerResults = judge.getPlayerResults();

        OutputView.printCardResults(dealer, dealerResults, playerResults);

        BettingResult bettingResult = new BettingResult(new DealerProfits(), new PlayerProfits());
        bettingResult.calculateAllResults(playerResults, dealerResults);

        DealerProfits dealerProfits = bettingResult.getDealerProfits();
        PlayerProfits playerProfits = bettingResult.getPlayerProfits();

        OutputView.printProfit(dealerProfits, playerProfits);
    }

    private void giveMoreCard(Participant participant, BlackjackGame blackjackGame) {
        if (participant.canDecideToTakeMoreCard()) {
            takeCardsAsLongAsWanted(participant, blackjackGame);
        }
    }

    private void takeCardsAsLongAsWanted(Participant participant, BlackjackGame blackjackGame) {
        Confirmation confirmation = InputView.askToGetMoreCard(participant);
        if (confirmation.equals(Confirmation.N)) {
            OutputView.printCardResult(participant);
            return;
        }

        blackjackGame.giveMoreCard(participant);
        OutputView.printCardResult(participant);

        if (new Score(participant).isBusted()) {
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

        OutputView.printStartingCardsStatuses(blackjackGame.getDealer(), blackjackGame.getPlayers());
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
