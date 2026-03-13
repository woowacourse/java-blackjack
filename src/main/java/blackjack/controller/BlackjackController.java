package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.BettingMoney;
import blackjack.domain.BettingMoneyInfo;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Name;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Status;
import blackjack.domain.Trump;
import blackjack.dto.FinalProfitDto;
import blackjack.strategy.ShuffleStrategy;
import blackjack.utils.Parser;
import blackjack.utils.RetryExecutor;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final ShuffleStrategy shuffleStrategy;

    public BlackjackController(ShuffleStrategy shuffleStrategy) {
        this.shuffleStrategy = shuffleStrategy;
    }

    public void run() {
        Dealer dealer = readyGame();
        Players players = RetryExecutor.retry(this::readPlayers);
        Participants participants = new Participants(players, dealer);

        BettingMoneyInfo bettingMoneyInfo = readBettingMoney(players);

        dealer.pitch(players.all());
        OutputView.printStartMessage(players.all(), dealer);
        players.all().forEach(this::handleBlackjack);
        players.all().forEach(player -> handlePlayerAction(player, dealer));
        handleDealerAction(dealer);

        printResult(participants, bettingMoneyInfo);
    }

    private Dealer readyGame() {
        Hand emptyHand = new Hand(new ArrayList<>());
        Trump trump = new Trump(shuffleStrategy);
        return new Dealer(emptyHand, Status.HIT, trump);
    }

    private BettingMoneyInfo readBettingMoney(Players players) {
        Map<Name, BettingMoney> bettingMoneyByPlayer = new HashMap<>();
        players.all().forEach(player -> {
            BettingMoney bettingMoney = RetryExecutor.retry(this::readBettingMoneyByPlayer, player);
            bettingMoneyByPlayer.put(player.getName(), bettingMoney);
        });

        return new BettingMoneyInfo(bettingMoneyByPlayer);
    }

    private BettingMoney readBettingMoneyByPlayer(Player player) {
        String rawBettingMoney = InputView.readBettingMoney(player.getName().toString());
        return new BettingMoney(rawBettingMoney);
    }

    private void printResult(Participants participants, BettingMoneyInfo bettingMoneyInfo) {
        OutputView.printFinalStatus(participants);
        FinalProfitDto finalProfitDto = FinalProfitDto.of(participants, bettingMoneyInfo);
        OutputView.printProfit(finalProfitDto);
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

    private void handleBlackjack(Player player) {
        player.handleBlackjack();
    }

    private void handlePlayerAction(Player player, Dealer dealer) {
        while (player.isHit()) {
            Answer answer =
                    RetryExecutor.retry(this::readAnswer, player.getName().toString());
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
