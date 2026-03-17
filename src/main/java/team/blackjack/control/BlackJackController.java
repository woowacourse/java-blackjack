package team.blackjack.control;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import team.blackjack.domain.Card;
import team.blackjack.domain.Deck;
import team.blackjack.domain.Result;
import team.blackjack.service.RevenueService;
import team.blackjack.domain.Dealer;
import team.blackjack.domain.Player;
import team.blackjack.domain.Players;
import team.blackjack.service.dto.DrawResult;
import team.blackjack.service.dto.RevenueResult;
import team.blackjack.service.dto.ScoreResult;
import team.blackjack.service.BlackjackService;
import team.blackjack.view.InputView;
import team.blackjack.view.OutputView;

public class BlackJackController {
    public static final String YES_INPUT = "y";
    public static final String NO_INPUT = "n";

    private final BlackjackService blackJackService;
    private final RevenueService revenueService;

    public BlackJackController(BlackjackService blackJackService,
                               RevenueService revenueService) {
        this.blackJackService = blackJackService;
        this.revenueService = revenueService;
    }

    public void run() {
        final Deck deck = new Deck();
        final Dealer dealer = new Dealer();
        final Players players = new Players(readPlayerNames());

        blackJackService.drawInitialCards(deck, players, dealer);

        readAllPlayerBattingMoneyRetry(players.getPlayerList());

        DrawResult drawResult = blackJackService.getDrawResult(dealer, players);
        OutputView.printDrawResult(drawResult);

        readPlayerHitDecision(deck, players);

        while (blackJackService.shouldDealerHit(dealer)) {
            OutputView.printDealerHitMessage();
            blackJackService.hit(deck, dealer);
        }

        ScoreResult scoreResult = blackJackService.calculateAllParticipantScore(players, dealer);
        OutputView.printParticipantScoreResult(scoreResult);

        final Map<Player, Result> judgeResults = players.getPlayerList().stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> blackJackService.judge(player, dealer)
                ));

        RevenueResult revenueResult = revenueService.getRevenueResult(judgeResults);
        OutputView.printRevenueResult(revenueResult);
    }

    public void readAllPlayerBattingMoneyRetry(List<Player> playerNames) {
       playerNames.forEach(player -> {
           int battingMoney = readPlayerBattingMoneyRetry(player.getName());
           blackJackService.batMoney(player, battingMoney);
       });
    }

    private int readPlayerBattingMoneyRetry(String playerName) {
        int battingMoney;

        do {
            battingMoney = readPlayerBattingMoney(playerName);
        } while (battingMoney <= 0);

        return battingMoney;
    }

    private int readPlayerBattingMoney(String playerName) {
        int battingMoney = -1;
        OutputView.printAskBettingMoney(playerName);
        try {
            battingMoney = InputView.readPlayerBattingMoney();
        } catch (NumberFormatException e) {
            OutputView.printWrongDecisionInputMessage();
        }

        return battingMoney;
    }

    private List<String> readPlayerNames(){
        OutputView.printPlayerNameRequest();
        List<String> playerNames = InputView.readPlayerNames();

        while (hasDuplicatedName(playerNames)) {
            OutputView.printDuplicatedNameMessage();
            OutputView.printPlayerNameRequest();
            playerNames = InputView.readPlayerNames();
        }
        return playerNames;
    }

    private boolean hasDuplicatedName(List<String> playerNames) {
        return playerNames.size() != playerNames.stream().distinct().count();
    }

    private void readPlayerHitDecision(Deck deck, Players players) {
        try {
            players.getPlayerList()
                    .forEach(player -> processPlayerHit(deck, player));
        } catch (IllegalStateException e) {
            OutputView.printBustMessage();
        }
    }

    private void processPlayerHit(Deck deck, Player player) {
        while (readPlayerHitDecision(player.getName())) {
            blackJackService.hit(deck, player);
            OutputView.printPlayerCards(player.getName(), player.getCards().stream()
                    .map(Card::getCardName)
                    .toList());
        }
    }

    private boolean readPlayerHitDecision(String playerName) {
        OutputView.printAskDrawCard(playerName);
        String hitYn = InputView.readHitDecision();

        while (!YES_INPUT.equalsIgnoreCase(hitYn) && !NO_INPUT.equalsIgnoreCase(hitYn)) {
            OutputView.printWrongDecisionInputMessage();
            hitYn = InputView.readHitDecision();
        }

        return YES_INPUT.equalsIgnoreCase(hitYn);
    }

}
