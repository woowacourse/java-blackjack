package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
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
        final Hand hand = new Hand(new ArrayList<>());
        final Players players = RetryExecutor.retry(this::readPlayers);
        final Dealer dealer = new Dealer(hand, Status.HIT, new Trump());
        final Participants participants = new Participants(players, dealer);
        dealer.pitch(players.all());
        OutputView.printStartMessage(players.all(), dealer);

        players.all().forEach(player -> handlePlayerAction(player, dealer));
        handleDealerAction(dealer);

        printResult(participants, players, dealer);
    }

    private void printResult(final Participants participants, final Players players,
        final Dealer dealer) {
        OutputView.printFinalStatus(participants);
        final FinalResultDto finalResultDto = FinalResultDto.of(players.all(), dealer);
        OutputView.printFinalResult(finalResultDto);
    }

    private void handleDealerAction(final Dealer dealer) {
        dealer.decideHit();
        while (dealer.isHit()) {
            dealer.giveCardMyself();
            dealer.decideHit();
            OutputView.printDealerHitMessage();
        }
        dealer.handleBurst();
    }

    private void handlePlayerAction(final Player player, final Dealer dealer) {
        while (player.isHit()) {
            final Answer answer = RetryExecutor.retry(this::readAnswer, player.getNickname());
            handleAnswer(player, dealer, answer);
            OutputView.printCardStatus(player);
        }
    }

    private Players readPlayers() {
        final String rawNicknames = InputView.readNicknames();
        final List<String> nicknames = Parser.parseNickname(rawNicknames);
        final List<Player> players = nicknames.stream()
            .map(nickname -> new Player(new Hand(new ArrayList<>()), Status.HIT, nickname))
            .toList();
        return new Players(players);
    }

    private Answer readAnswer(final String nickname) {
        return Answer.pick(InputView.readAnswer(nickname));
    }

    private void handleAnswer(final Player player, final Dealer dealer, final Answer answer) {
        if (answer == Answer.YES) { // HIT
            dealer.giveCardTo(player);
            player.handleBurst();
        }
        if (answer == Answer.NO) { // STAY
            player.stay();
        }
    }
}
