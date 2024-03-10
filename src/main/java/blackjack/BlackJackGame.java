package blackjack;

import static blackjack.domain.DrawDecision.YES;

import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.HandCreator;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerCreator;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.rule.Judge;
import blackjack.domain.rule.Score;
import blackjack.domain.rule.ScoreCalculateStrategy;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeck.createShuffledDeck();
        Players players = initPlayers(cardDeck);
        Player dealer = initDealer(cardDeck);
        printPlayersInformation(players, dealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);

        printDealerPopCount(dealer);

        printPlayerScore(dealer);
        printPlayersScore(players);
        printDealerGameResult(dealer, players);
        printPlayersGameResult(players, dealer);
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

    private void printPlayersInformation(Players players, Player dealer) {
        outputView.printHandOutEvent(players, 2);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> completePlayerHand(player, cardDeck));
    }

    private void completePlayerHand(Player player, CardDeck cardDeck) {
        while (player.canHit() && readHitDecision(player) == YES) {
            player.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(player);
        }
    }

    private DrawDecision readHitDecision(Player player) {
        InputMapper inputMapper = new InputMapper();
        return inputMapper.mapToDrawDecision(inputView.readDrawPlan(player.getName()));
    }

    private void completeDealerHand(Player dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
        }
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
        outputView.printPlayerScore(player, player.calculateHandScore());
    }

    private void printDealerGameResult(Player dealer, Players players) {
        Score dealerScore = dealer.calculateHandScore();
        int playerWinCount = players.countPlayerWithScoreAbove(dealerScore, new ScoreCalculateStrategy());
        int dealerWinCount = players.countPlayer() - playerWinCount;

        DealerGameResult dealerGameResult = new DealerGameResult(dealerWinCount, playerWinCount);
        outputView.printDealerGameResult(dealerGameResult);
    }

    private void printPlayersGameResult(Players players, Player dealer) {
        Judge judge = new Judge();
        Score dealerScore = dealer.calculateHandScore();
        players.getPlayers()
                .forEach(player -> {
                    Score playerScore = player.calculateHandScore();
                    outputView.printPlayerGameResult(player, judge.isPlayerWin(dealerScore, playerScore));
                });
    }
}
