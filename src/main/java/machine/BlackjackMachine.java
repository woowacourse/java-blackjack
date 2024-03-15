package machine;

import domain.betting.BetInfo;
import domain.betting.Money;
import domain.betting.ProfitInfo;
import domain.card.Deck;
import domain.game.PlayerResults;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import strategy.RandomCardGenerator;
import view.InputView;
import view.OutputView;

public class BlackjackMachine {

    private static final int STARTING_CARDS_AMOUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = Dealer.withNoCards();
        Deck deck = Deck.from(new RandomCardGenerator());
        BetInfo betInfo = BetInfo.withNoEntry();
        ProfitInfo profitInfo = ProfitInfo.withNoEntry();
        PlayerResults playerResults = PlayerResults.withNoEntry();

        Players players = makePlayers();
        readBetAmount(players, betInfo);
        distributeStartingCards(dealer, players, deck);
        playPlayerTurns(players, deck);
        playDealerTurn(dealer, deck);

        makeResults(dealer, players, playerResults);
        distributeMoney(players, playerResults, betInfo, profitInfo);
        showCardsAndScores(dealer, players);
        showProfits(players, profitInfo);
    }

    private Players makePlayers() {
        List<String> names = inputView.readNames();
        return Players.withNames(names);
    }

    private void readBetAmount(Players players, BetInfo betInfo) {
        for (Player player : players.getPlayers()) {
            int rawBetMoney = inputView.readBetAmount(player.getName());
            Money betMoney = Money.betValueOf(rawBetMoney);
            betInfo.add(player, betMoney);
        }
    }

    private void distributeStartingCards(Dealer dealer, Players players, Deck deck) {
        dealer.tryReceive(deck.drawCards(STARTING_CARDS_AMOUNT));
        for (Player player : players.getPlayers()) {
            player.tryReceive(deck.drawCards(STARTING_CARDS_AMOUNT));
        }
        outputView.printDistributionMessage(players.getPlayers());
        outputView.printStartingCardsOfAllParticipants(dealer, players);
    }

    private void playPlayerTurns(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            playPlayerTurn(player, deck);
        }
    }

    private void playPlayerTurn(Player player, Deck deck) {
        while (player.isNotBust() && isHitRequested(player)) {
            player.tryReceive(deck.drawCard());
            outputView.printNameAndCardsOfParticipant(player.getName(), player.getCards());
        }
        if (player.isBust()) {
            outputView.printBustMessage(player.getName());
            return;
        }
        outputView.printNameAndCardsOfParticipant(player.getName(), player.getCards());
    }

    private boolean isHitRequested(Player player) {
        return inputView.readHitOrStay(player) == HitStay.HIT;
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.isReceivable()) {
            dealer.tryReceive(deck.drawCard());
            outputView.printDealerDrawMessage();
        }
    }

    private void makeResults(Dealer dealer, Players players, PlayerResults playerResults) {
        for (Player player : players.getPlayers()) {
            playerResults.addResultOf(player, dealer);
        }
    }

    private void distributeMoney(Players players, PlayerResults results, BetInfo betInfo, ProfitInfo profitInfo) {
        for (Player player : players.getPlayers()) {
            Money money = betInfo.findBetAmountBy(player);
            Money profitMoney = money.calculateProfit(results.resultOf(player));
            profitInfo.add(player, profitMoney);
        }
    }

    private void showCardsAndScores(Dealer dealer, Players players) {
        outputView.printFinalCardsAndScoresOfAllParticipants(dealer, players);
    }

    private void showProfits(Players players, ProfitInfo profitInfo) {
        outputView.printPlayerNamesAndProfitsOfAllParticipants(players, profitInfo);
    }
}
