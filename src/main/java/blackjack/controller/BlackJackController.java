package blackjack.controller;

import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckCreator;
import blackjack.domain.player.*;
import blackjack.domain.rule.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

import static blackjack.domain.DrawDecision.YES;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = initCardDeck();
        Players players = initPlayers(cardDeck);
        Player dealer = initDealer(cardDeck);
        printPlayersInformation(players, cardDeck, dealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);

        printDealerPopCount(dealer);

        printPlayerScore(dealer);
        printPlayersScore(players);
        printDealerGameResult(dealer, players);
        printPlayersGameResult(players, dealer);
    }

    private CardDeck initCardDeck() {
        CardDeckCreator cardDeckCreator = new CardDeckCreator();
        return cardDeckCreator.create();
    }

    private Players initPlayers(CardDeck cardDeck) {
        InputMapper inputMapper = new InputMapper();
        PlayerCreator playerCreator = new PlayerCreator(new HandCreator());
        List<PlayerName> playerNames = inputMapper.mapToPlayers(inputView.readNames());

        return new Players(playerNames.stream()
                .map(playerName -> playerCreator.createPlayerFrom(playerName, cardDeck))
                .toList());
    }

    private Player initDealer(CardDeck cardDeck) {
        PlayerCreator playerCreator = new PlayerCreator(new HandCreator());
        return playerCreator.createDealerFrom(cardDeck);
    }

    private void printPlayersInformation(Players players, CardDeck cardDeck, Player dealer) {
        outputView.printHandOutEvent(players, 2);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            completePlayerHand(player, cardDeck);
        }
    }

    private void completePlayerHand(Player player, CardDeck cardDeck) {
        Score playerScore = calculateScore(player);
        HitStrategy hitStrategy = new PlayerHitStrategy();

        while (playerScore.hitAllowed(hitStrategy) && readHitDecision(player) == YES) {
            player.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(player);
            playerScore = calculateScore(player);
        }
    }

    private DrawDecision readHitDecision(Player player) {
        String name = player.getName();
        InputMapper inputMapper = new InputMapper();
        DrawDecision hitDecision = inputMapper.mapToDrawDecision(inputView.readDrawPlan(name));
        return hitDecision;
    }

    private void completeDealerHand(Player dealer, CardDeck cardDeck) {
        Score dealerScore = calculateScore(dealer);
        HitStrategy hitStrategy = new DealerHitStrategy();

        while (dealerScore.hitAllowed(hitStrategy)) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
            dealerScore = calculateScore(dealer);
        }
    }

    private Score calculateScore(Player player) {
        ScoreCalculateStrategy scoreCalculateStrategy = new ScoreCalculateStrategy();
        return player.calculateHandScore(scoreCalculateStrategy);
    }

    private void printDealerPopCount(Player dealer) {
        int dealerPopCount = dealer.handSize() - 2;
        if (dealerPopCount > 0) {
            outputView.printDealerPopCount(16, dealerPopCount);
        }
    }

    private void printPlayersScore(Players players) {
        players.getPlayers().forEach(this::printPlayerScore);
    }

    private void printPlayerScore(Player player) {
        Score score = calculateScore(player);
        outputView.printPlayerScore(player, score);
    }

    private void printDealerGameResult(Player dealer, Players players) {
        Score dealerScore = calculateScore(dealer);
        int playerWinCount = players.countPlayerWithScoreAbove(dealerScore, new ScoreCalculateStrategy());
        int dealerWinCount = players.countPlayer() - playerWinCount;

        DealerGameResult dealerGameResult = new DealerGameResult(dealerWinCount, playerWinCount);
        outputView.printDealerGameResult(dealerGameResult);
    }

    private void printPlayersGameResult(Players players, Player dealer) {
        Judge judge = new Judge();
        Score dealerScore = calculateScore(dealer);
        players.getPlayers()
                .forEach(player -> {
                    Score playerScore = calculateScore(player);
                    outputView.printPlayerGameResult(player, judge.isPlayerWin(dealerScore, playerScore));
                });
    }
}
