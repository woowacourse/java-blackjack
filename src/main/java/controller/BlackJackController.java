package controller;

import domain.blackjack.BetAmount;
import domain.blackjack.BettingResult;
import domain.blackjack.BlackJack;
import domain.blackjack.HitOption;
import domain.dto.ParticipantDto;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import view.InputView;
import view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackController {

    public void run() {
        List<String> names = InputView.inputParticipantName();
        Dealer dealer = new Dealer();
        Players players = new Players(names);
        Map<Player, BetAmount> betAmount = createBetAmount(players);
        BlackJack blackJack = new BlackJack(players, dealer);

        blackJack.beginDealing(this::beginBlackJack);
        blackJack.play(this::isHitOption, OutputView::printParticipantHands);
        dealerHit(blackJack);

        printScore(players, dealer);
        printResult(betAmount, dealer);
    }

    private Map<Player, BetAmount> createBetAmount(Players players) {
        Map<Player, BetAmount> betAmount = new LinkedHashMap<>();
        for (Player player : players.getValue()) {
            betAmount.put(player, new BetAmount(InputView.inputBetAmount(player.getName())));
        }
        return betAmount;
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

    private void printResult(Map<Player, BetAmount> bet, Dealer dealer) {
        BettingResult bettingResult = new BettingResult(bet);
        OutputView.printBlackJackResult(bettingResult, dealer);
    }
}
