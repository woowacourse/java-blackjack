package blackjack.controller;

import blackjack.domain.CardFactory;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Result;
import blackjack.domain.ResultStatus;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Controller {

    private static final int BLACKJACK_SCORE = 21;

    public void play() {
        Dealer dealer = createDealer();
        Players players = createPlayers();

        initializeHands(dealer, players.getPlayers());
        playerTurn(dealer, players);
        dealerTurn(dealer);
        printScoreAndResult(dealer, players);
    }

    private Dealer createDealer() {
        CardFactory cardFactory = new CardFactory();
        Deck deck = new Deck(cardFactory.createBlackJackCard(), Collections::shuffle);
        return new Dealer(deck);
    }

    private Players createPlayers() {
        List<String> playerNames = InputView.readName();
        return Players.from(playerNames);
    }

    private void initializeHands(Dealer dealer, List<Player> players) {
        dealer.shuffleDeck();
        dealer.initializeHand(dealer.draw(), dealer.draw());
        for (Player player : players) {
            player.initializeHand(dealer.draw(), dealer.draw());
        }

        OutputView.printInitialHands(dealer, players);
    }

    private void playerTurn(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            hitOrStand(dealer, player);
        }
    }

    private void dealerTurn(Dealer dealer) {
        while (dealer.canHit()) {
            dealer.putCard(dealer.draw());
            OutputView.printDealerDraw(dealer);
        }
    }

    private void printScoreAndResult(Dealer dealer, Players players) {
        OutputView.printHandsWithScore(dealer, players.getPlayers());
        OutputView.printResult(calculateResult(dealer, players), dealer);
    }

    private Result calculateResult(Dealer dealer, Players players) {
        int dealerScore = dealer.calculate();
        Map<Player, Integer> playersScore = players.calculate();

        return new Result(playersScore.entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        player -> match(dealerScore, player.getValue()),
                        (previous, next) -> next,
                        LinkedHashMap::new)));
    }

    private ResultStatus match(int dealerScore, int playerScore) {
        boolean isDealerBust = dealerScore > BLACKJACK_SCORE;
        boolean isPlayerBust = playerScore > BLACKJACK_SCORE;
        if (isDealerBust && isPlayerBust) {
            return ResultStatus.DRAW;
        }
        if (isDealerBust) {
            return ResultStatus.WIN;
        }
        if (isPlayerBust) {
            return ResultStatus.LOSE;
        }
        final int gap = dealerScore - playerScore;
        return matchWhenDealerAlive(gap);
    }

    private ResultStatus matchWhenDealerAlive(int gap) {
        if (gap > 0) {
            return ResultStatus.LOSE;
        }
        if (gap == 0) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.WIN;
    }

    private void hitOrStand(Dealer dealer, Player player) {
        while (player.canHit() && InputView.readHitOrStand(player)) {
            player.putCard(dealer.draw());
            OutputView.printTotalHand(player);
        }

        if (!player.canHit()) {
            OutputView.printBust();
        }
    }
}
