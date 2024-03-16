package controller;

import domain.Bet.BetResult;
import domain.blackjack.BlackJack;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
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
        printParticipantsCards(blackJack);

        startParticipantHit(blackJack);
        dealerHit(blackJack);

        BetResult betResult = blackJack.makeBetResult();

        printScoreAndResult(blackJack, betResult);
    }

    private BlackJack initBlackJack() {
        Players players = makeParticipants();
        return new BlackJack(new Dealer(), players);
    }

    private Players makeParticipants() {
        List<String> names = InputView.inputParticipantName();
        List<Player> players = names.stream()
                .map(name -> new Player(name, InputView.inputBetAmount(new Name(name))))
                .toList();
        return new Players(players);
    }

    private void printParticipantsCards(BlackJack blackJack) {
        Players players = blackJack.getPlayers();
        OutputView.printBeginDealingInformation(players);
        OutputView.printDealerHands(blackJack.getDealer());
        for (Participant participant : players.getValue()) {
            OutputView.printParticipantHands(participant);
        }
    }

    public void startParticipantHit(BlackJack blackJack) {
        Players players = blackJack.getPlayers();
        Dealer dealer = blackJack.getDealer();
        for (Player player : players.getValue()) {
            participantHit(player, dealer);
        }
    }

    private void participantHit(Player player, Dealer dealer) {
        while (player.canHit()) {
            String option = InputView.inputHitOption(player.getName());
            HitOption hitOption = HitOption.from(option);
            if (hitOption.isStayOption()) {
                break;
            }
            player.receiveCard(dealer.draw());
            OutputView.printParticipantHands(player);
        }
    }

    private void dealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScoreAndResult(BlackJack blackJack, BetResult betResult) {
        OutputView.printParticipantResult(blackJack.getDealer());
        Players players = blackJack.getPlayers();
        for (Participant participant : players.getValue()) {
            OutputView.printParticipantResult(participant);
        }
        OutputView.printBetResult(betResult);
    }
}
