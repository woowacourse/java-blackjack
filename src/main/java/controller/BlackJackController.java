package controller;

import domain.blackjack.BetAmount;
import domain.blackjack.BlackJack;
import domain.blackjack.HitOption;
import domain.dto.ParticipantDto;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackController {

    public void run() {
        List<Player> inputPlayers = InputView.inputParticipantName().stream()
                .map(name -> new Player(new Name(name), new BetAmount(InputView.inputBetAmount(new Name(name)))))
                .toList();
        Dealer dealer = new Dealer();
        Players players = new Players(inputPlayers);
        BlackJack blackJack = new BlackJack(players, dealer);

        blackJack.beginDealing(this::beginBlackJack);
        blackJack.play(this::isHitOption, OutputView::printParticipantHands);
        dealerHit(blackJack);

        printScore(players, dealer);
        printResult(players, dealer);
    }

    private void beginBlackJack(Players players, Dealer dealer) {
        OutputView.printBeginDealingInformation(players.getNames());
        OutputView.printDealerHands(dealer.getName(), dealer.getCards());
        for (Player player : players.getValue()) {
            OutputView.printParticipantHands(ParticipantDto.from(player));
        }
    }

    private HitOption isHitOption(Name name) {
        return HitOption.from(InputView.inputHitOption(name));
    }

    private void dealerHit(BlackJack blackJack) {
        OutputView.printDealerHit(blackJack.dealerHit());
    }

    private void printScore(Players players, Dealer dealer) {
        OutputView.printParticipantResult(ParticipantDto.from(dealer));
        for (Player player : players.getValue()) {
            OutputView.printParticipantResult(ParticipantDto.from(player));
        }
    }

    private void printResult(Players players, Dealer dealer) {
        OutputView.printBlackJackResult(players, dealer);
    }
}
