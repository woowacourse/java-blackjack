package blackjack.controller;

import blackjack.model.Players;
import blackjack.model.WinningResult;
import blackjack.model.card.HandCard;
import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.model.state.DealerInitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Dealer dealer = new Dealer(new DealerInitialState(new HandCard()));
        CardDeck cardDeck = new CardDeck();

        distributeFirstCards(players, dealer, cardDeck);
        printFirstCardDistribution(players, dealer);

        playHitOrStand(players, dealer, cardDeck);

        printScoreResults(players, dealer);
        printWinningResult(players, dealer);
    }

    private void distributeFirstCards(Players players, Dealer dealer, CardDeck cardDeck) {
        players.distributeFirstCards(cardDeck);
        dealer.draw(cardDeck);
    }

    private void printFirstCardDistribution(Players players, Dealer dealer) {
        outputView.printDistributionMessage(players.getPlayerNames());

        Map<String, List<Card>> dealerDistributedCards = new HashMap<>();
        dealerDistributedCards.put(dealer.getName(), dealer.firstDistributedCard());
        outputView.printHandCardUnits(handCardUnits(dealerDistributedCards));
        outputView.printHandCardUnits(handCardUnits(players.firstDistributedCards()));
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
        while (!players.isPlayerFinished(playerId) && inputView.readIsHit(players.getNameById(playerId))) {
            players.hit(cardDeck, playerId);
            Map<String, List<Card>> playerHandCards = players.getHandCardsById(playerId);
            outputView.printHandCardUnits(handCardUnits(playerHandCards));
        }
        if (!players.isPlayerFinished(playerId)) {
            players.changeToStand(playerId);
        }
    }

    private void printScoreResults(Players players, Dealer dealer) {
        printOneScoreResult(Integer.toString(dealer.getScore()), dealer.isBlackjack(), dealer.handCards());

        for (int playerId = 0; playerId < players.getPlayerCount(); playerId++) {
            String scoreResult = Integer.toString(players.getScoreById(playerId));
            printOneScoreResult(scoreResult, players.isBlackjack(playerId), players.getHandCardsById(playerId));
        }
    }

    private void printOneScoreResult(String result, boolean isBlackjack, Map<String, List<Card>> handCards) {
        if (isBlackjack) {
            result += BLACKJACK_MESSAGE;
        }
        outputView.printScoreResult(handCardUnits(handCards), result);
    }

    private void printWinningResult(Players players, Dealer dealer) {
        outputView.printWinningResultMessage();
        Map<String, WinningResult> results = dealer.winningResults(players);

        WinningResult dealerResult = results.remove(dealer.getName());
        outputView.printDealerWinningResult(dealerResult.getWin(), dealerResult.getDraw(), dealerResult.getLose());

        for (Map.Entry<String, WinningResult> playerResult : results.entrySet()) {
            WinningResult playerWinning = playerResult.getValue();
            outputView.printPlayersWinningResult(playerResult.getKey(), playerWinning.getWin(), playerWinning.getDraw(), playerWinning.getLose());
        }
    }

    private Map<String, List<String>> handCardUnits(Map<String, List<Card>> handCards) {
        Map<String, List<String>> handCardUnits = new HashMap<>();

        for (Map.Entry<String, List<Card>> handCard : handCards.entrySet()) {
            List<Card> cards = handCard.getValue();
            List<String> cardUnits = cards.stream().map(Card::cardUnit).collect(Collectors.toList());
            handCardUnits.put(handCard.getKey(), cardUnits);
        }
        return handCardUnits;
    }
}
