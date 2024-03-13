package blackjack;

import blackjack.domain.card.strategy.RandomCardGenerator;
import blackjack.domain.game.Betting;
import blackjack.domain.game.BettingCashier;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.HitStay;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackjackMachine {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame game = initializeGame();
        Betting betting = takeBet(game);
        distributeStartingCards(game);
        playPlayersTurn(game);
        playDealerTurn(game);
        printCardsAndScores(game);
//        printResult(game);
        printBettingResult(betting, game);
    }

    private Betting takeBet(BlackjackGame game) {
        Betting betting = new Betting();
        for (Player player : game.getPlayers()) {
            betting.bet(player, inputView.readMoney(player));
        }
        return betting.unmodifiableBetting();
    }

    private void distributeStartingCards(BlackjackGame game) {
        game.distributeStartingCards();
        outputView.printDistributionMessage(game);
        outputView.printAllParticipantsCards(game);
    }

    private BlackjackGame initializeGame() {
        List<String> names = inputView.readNames();
        List<Player> players = names.stream()
                .map(Player::nameOf)
                .toList();
        return new BlackjackGame(Participants.createWithDealer(players), new RandomCardGenerator());
    }

    private void playPlayersTurn(BlackjackGame game) {
        for (Player player : game.getPlayers()) {
            playPlayerTurn(game, player);
        }
    }

    private void playPlayerTurn(BlackjackGame game, Player player) {
        while (player.isReceivable() && isHitRequested(player)) {
            game.giveOneCard(player);
            outputView.printParticipantCards(player);
        }
        if (player.isBusted()) {
            outputView.printBustMessage(player);
            return;
        }
        outputView.printParticipantCards(player);
    }

    private boolean isHitRequested(Player player) {
        return inputView.readHitStay(player) == HitStay.HIT;
    }

    private void playDealerTurn(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        if (dealer.isReceivable()) {
            game.giveOneCard(dealer);
            outputView.printDealerDrawMessage();
            playDealerTurn(game);
        }
    }

    private void printCardsAndScores(BlackjackGame game) {
        outputView.printAllParticipantsCardsWithScore(game);
    }

    private void printResult(BlackjackGame game) {
        Result result = Result.of(game.getPlayers(), game.getDealer());
        outputView.printResult(game, result);
    }

    private void printBettingResult(Betting betting, BlackjackGame game) {
        Result result = Result.of(game.getPlayers(), game.getDealer());
        BettingCashier cashier = BettingCashier.of(betting, result);
        outputView.printProfit(game, cashier);
    }
}
