package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.Bet;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Status;
import blackjack.domain.Trump;
import blackjack.dto.FinalProfitsDto;
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
        players.all().forEach(player ->
            player.bet(RetryExecutor.retry(this::readBet, player)));
        final Dealer dealer = new Dealer(hand, Status.HIT, new Trump());
        final Participants participants = new Participants(players, dealer);
        dealer.pitch(players.all());
        OutputView.printStartMessage(players.all(), dealer);

        players.all().forEach(player -> handlePlayerAction(player, dealer));
        handleDealerAction(dealer);

        players.all().forEach(player -> decidePayoutPolicy(player, dealer));
        printResult(participants, players);
    }

    private void printResult(final Participants participants, final Players players) {
        final FinalProfitsDto finalProfitsDto = FinalProfitsDto.from(players);
        OutputView.printFinalStatus(participants);
        OutputView.printFinalProfits(finalProfitsDto);
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

    private Bet readBet(final Player player) {
        final int amount = InputView.readBetAmount(player.getNickname());
        return new Bet(amount);
    }

    private Answer readAnswer(final String nickname) {
        return Answer.pick(InputView.readAnswer(nickname));
    }

    private void handleAnswer(final Player player, final Dealer dealer, final Answer answer) {
        if (answer == Answer.YES) {
            playerHit(dealer, player);
        }
        if (answer == Answer.NO) {
            player.stay();
        }
    }

    private void playerHit(final Dealer dealer, final Player player) {
        dealer.giveCardTo(player);
        player.handleBurst();
    }

    private void decidePayoutPolicy(final Player player, final Dealer dealer) {
        final GameResult gameResult = GameResult.calculate(player, dealer);
        player.decidePayoutPolicy(gameResult);
    }
}
