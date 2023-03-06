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

        for (int playerId = 0; playerId < players.getPlayerCount(); playerId++) {
            playerHitOrStand(players, cardDeck, playerId);
        }
        dealerHitOrStand(cardDeck, dealer);

        printDealerScoreResult(dealer);
        printPlayerScoreResult(players);

        printWinningResult(players, dealer);
    }

    private void distributeFirstCards(Players players, Dealer dealer, CardDeck cardDeck) {
        players.distributeFirstCards(cardDeck);
        dealer.draw(cardDeck);
    }

    private void printFirstCardDistribution(Players players, Dealer dealer) {
        List<String> dealerCards = dealer.firstDistributedCard().stream()
                .map(Card::cardUnit)
                .collect(Collectors.toList());
        Map<String, List<String>> dealerDistributedCards = new HashMap<>();
        dealerDistributedCards.put(dealer.getName(), dealerCards);
        outputView.printDistributionMessage(players.getPlayerNames());
        outputView.printNameAndHand(dealerDistributedCards);
        outputView.printNameAndHand(handCardUnits(players.firstDistributedCards()));
    }


    private void playerHitOrStand(Players players, CardDeck cardDeck, int playerId) {
        while (!players.isPlayerFinished(playerId) && inputView.readIsHit(players.getNameById(playerId))) {
            players.hit(cardDeck, playerId);
            Map<String, List<Card>> playerHandCards = players.getHandCardsById(playerId);
            outputView.printNameAndHand(handCardUnits(playerHandCards));
        }
        if (!players.isPlayerFinished(playerId)) {
            players.changeToStand(playerId);
        }
    }

    private void dealerHitOrStand(CardDeck cardDeck, Dealer dealer) {
        while (!dealer.isFinished()) {
            dealer.draw(cardDeck);
            outputView.printDealerHitMessage();
        }
    }

    private void printDealerScoreResult(Dealer dealer) {
        String dealerResult = Integer.toString(dealer.getScore());
        if (dealer.isBlackjack()) {
            dealerResult += " (블랙잭!!)";
        }
        outputView.printScoreResult(handCardUnits(dealer.handCards()), dealerResult);
    }

    private void printPlayerScoreResult(Players players) {
        for (int playerId = 0; playerId < players.getPlayerCount(); playerId++) {
            Map<String, List<Card>> playerHandCards = players.getHandCardsById(playerId);
            String scoreResult = Integer.toString(players.getScoreById(playerId));
            if (players.isBlackjack(playerId)) {
                scoreResult += "(블랙잭!!)";
            }
            outputView.printScoreResult(handCardUnits(playerHandCards), scoreResult);
        }
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
