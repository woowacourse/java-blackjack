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
import blackjack.strategy.ShuffleStrategy;
import blackjack.utils.Parser;
import blackjack.utils.RetryExecutor;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final ShuffleStrategy shuffleStrategy;

    public BlackjackController(ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }

    public void run() {
        Hand hand = new Hand(new ArrayList<>());
        Players players = RetryExecutor.retry(this::readPlayers);
        Participants participants = new Participants(players, dealer);

        dealer.pitch(players.all());
        OutputView.printStartMessage(players.all(), dealer);

        players.all().forEach(player -> handlePlayerAction(player, dealer));
        handleDealerAction(dealer);

        printResult(participants, players, dealer);
    }

    private void printResult(Participants participants, Players players, Dealer dealer) {
        OutputView.printFinalStatus(participants);
        FinalResultDto finalResultDto = FinalResultDto.of(players.all(), dealer);
        OutputView.printFinalResult(finalResultDto);
    }

    private void handleDealerAction(Dealer dealer) {
        dealer.decideHit();
        while (dealer.isHit()) {
            dealer.giveCard();
            dealer.decideHit();
            OutputView.printDealerHitMessage();
        }
        dealer.handleBurst();
    }

    private void handlePlayerAction(Player player, Dealer dealer) {
        while (player.isHit()) {
            Answer answer =
                    RetryExecutor.retry(this::readAnswer, player.getNickname());
            handleAnswer(player, dealer, answer);
            OutputView.printCardStatus(player);
        }
    }

    private Players readPlayers() {
        String rawNicknames = InputView.readNicknames();
        List<String> nicknames = Parser.parseNickname(rawNicknames);
        List<Player> players = nicknames.stream()
                .map(nickname ->
                        new Player(new Hand(new ArrayList<>()), Status.HIT, nickname))
                .toList();
        return new Players(players);
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
