package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Status;
import blackjack.domain.Trump;
import blackjack.dto.FinalResultDto;
import blackjack.utils.Parser;
import blackjack.utils.RetryExecutor;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    public void run() {
        Trump trump = new Trump();
        Hand hand = new Hand(new ArrayList<>());
        List<Player> players = RetryExecutor.retry(this::readNicknames);
        Dealer dealer = new Dealer(hand, Status.HIT, trump);
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players);
        dealer.pitch(players);
        OutputView.printStartMessage(players, dealer);

        players.forEach(player -> handlePlayerAction(player, dealer));

        dealer.decideHit();
        while(dealer.isHit()) {
            dealer.giveCard();
            dealer.decideHit();
            OutputView.printDealerHitMessage();
        }

        dealer.handleBurst();

        OutputView.printFinalStatus(participants);
        FinalResultDto finalResultDto = FinalResultDto.of(players, dealer);
        OutputView.printFinalResult(finalResultDto);
    }

    private void handlePlayerAction(Player player, Dealer dealer) {
        while (player.isHit()) {
            Answer answer =
                RetryExecutor.retry(this::readAnswer, player.getNickname());
            handleAnswer(player, dealer, answer);
            OutputView.printCardStatus(player);
        }
    }

    private List<Player> readNicknames() {
        String rawNicknames = InputView.readNicknames();
        List<String> nicknames = Parser.parseNickname(rawNicknames);
        return nicknames.stream()
            .map(nickname ->
                new Player(new Hand(new ArrayList<>()), Status.HIT, nickname))
            .toList();
    }

    private Answer readAnswer(final String nickname) {
        return Answer.pick(InputView.readAnswer(nickname));
    }

    private void handleAnswer(final Player player, final Dealer dealer, final Answer answer) {
        if (answer == Answer.YES) { // HIT
            dealer.giveCard(player);
            player.handleBurst();
        }
        if (answer == Answer.NO) { // STAY
            player.stay();
        }
    }
}
