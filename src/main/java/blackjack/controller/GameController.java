package blackjack.controller;

import blackjack.model.CardUnit;
import blackjack.model.participant.Players;
import blackjack.model.WinningResult;
import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.model.state.DealerInitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.Map;

public class GameController {

    public static final String BLACKJACK_MESSAGE = " (블랙잭!!)";

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = new Players(inputView.readNames());
        Dealer dealer = new Dealer(new DealerInitialState());
        CardDeck cardDeck = new CardDeck();

        distributeFirstCards(players, dealer, cardDeck);

        playHitOrStand(players, dealer, cardDeck);

        printScoreResults(players, dealer);
        printWinningResult(players, dealer);
    }

    private void distributeFirstCards(Players players, Dealer dealer, CardDeck cardDeck) {
        players.distributeFirstCards(cardDeck);
        dealer.draw(cardDeck);
        outputView.printDistributionMessage(players.getPlayerNames());
        outputView.printHandCardUnits(new CardUnit(dealer.firstDistributedCard()).getHandCardUnits());
        outputView.printHandCardUnits(new CardUnit(players.firstDistributedCards()).getHandCardUnits());
    }

    private void playHitOrStand(Players players, Dealer dealer, CardDeck cardDeck) {
        for (int playerId = 0; playerId < players.getPlayerCount(); playerId++) {
            playerHitOrStand(players, cardDeck, playerId);
        }

        while (!dealer.isFinished()) {
            dealer.draw(cardDeck);
            outputView.printDealerHitMessage();
        }
    }

    private void playerHitOrStand(Players players, CardDeck cardDeck, int playerId) {
        while (!players.isPlayerFinished(playerId) && (inputView.readIsHit(players.getNameById(playerId)))) {
            players.hit(cardDeck, playerId);
            outputView.printHandCardUnits(new CardUnit(players.getHandCardsById(playerId)).getHandCardUnits());
        }
        players.changeToStand(playerId);
    }

    private void printScoreResults(Players players, Dealer dealer) {
        printOneScoreResult(Integer.toString(dealer.cardScore().getScore()), dealer.isBlackjack(), dealer.getCardUnit());

        for (int playerId = 0; playerId < players.getPlayerCount(); playerId++) {
            String scoreResult = Integer.toString(players.getScoreById(playerId));
            printOneScoreResult(scoreResult, players.isBlackjack(playerId), players.getHandCardsById(playerId));
        }
    }

    private void printOneScoreResult(String result, boolean isBlackjack, Map<String, List<Card>> handCards) {
        if (isBlackjack) {
            result += BLACKJACK_MESSAGE;
        }
        outputView.printScoreResult(new CardUnit(handCards).getHandCardUnits(), result);
    }

    private void printWinningResult(Players players, Dealer dealer) {
        Map<String, WinningResult> results = dealer.participantWinningResults(players);

        outputView.printWinningResultMessage();
        outputView.printDealerWinningResult(results.remove(dealer.getName()).getResult());
        for (Map.Entry<String, WinningResult> playerResult : results.entrySet()) {
            WinningResult playerWinning = playerResult.getValue();
            outputView.printPlayersWinningResult(playerResult.getKey(), playerWinning.getResult());
        }
    }
}
