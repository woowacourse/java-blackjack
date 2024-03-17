package controller;

import domain.bet.BetResult;
import domain.blackjack.BlackJack;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.HitOption;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJack blackJack = initBlackJack();

        blackJack.beginDealing();
        printInitBlackJack(blackJack);

        startPlayersHit(blackJack);
        startDealerHit(blackJack);

        printScoreAndResult(blackJack);
    }

    private BlackJack initBlackJack() {
        Players players = makePlayers();
        return new BlackJack(new Dealer(), players);
    }

    private Players makePlayers() {
        List<String> names = InputView.inputPlayersName();
        List<Player> players = names.stream()
                .map(name -> new Player(name, InputView.inputBetAmount(new Name(name))))
                .toList();
        return new Players(players);
    }

    private void printInitBlackJack(BlackJack blackJack) {
        Players players = blackJack.getPlayers();
        OutputView.printInitBlackJack(players.getNames());
        printInitHands(blackJack, players);
    }

    private void printInitHands(BlackJack blackJack, Players players) {
        OutputView.printDealerInitHands(blackJack.getDealer().revealCardOnInitDeal());
        for (Player player : players.getValue()) {
            OutputView.printPlayerHands(player.getName(), player.revealCardOnInitDeal());
        }
        OutputView.printBlank();
    }

    private void startPlayersHit(BlackJack blackJack) {
        Players players = blackJack.getPlayers();
        Dealer dealer = blackJack.getDealer();
        for (Player player : players.getValue()) {
            playersHit(player, dealer);
        }
    }

    private void playersHit(Player player, Dealer dealer) {
        while (player.canHit()) {
            String option = InputView.inputHitOption(player.getName());
            HitOption hitOption = HitOption.from(option);
            if (hitOption.isStayOption()) {
                break;
            }
            player.receiveCard(dealer.draw());
            OutputView.printPlayerHands(player.getName(), player.getCards());
        }
    }

    private void startDealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScoreAndResult(BlackJack blackJack) {
        printScore(blackJack);
        printBetResult(blackJack);
    }

    private void printScore(BlackJack blackJack) {
        Dealer dealer = blackJack.getDealer();
        OutputView.printParticipantScore(dealer.getName(), dealer.getCards(), dealer.getScore());
        Players players = blackJack.getPlayers();
        for (Player player : players.getValue()) {
            OutputView.printParticipantScore(player.getName(), player.getCards(), player.getScore());
        }
    }

    private void printBetResult(BlackJack blackJack) {
        BetResult betResult = blackJack.makeBetResult();
        OutputView.printBetResultMessage();
        OutputView.printDealerBetResult(betResult.calculateDealerProfit());
        OutputView.printPlayersBetResult(betResult);
    }
}
