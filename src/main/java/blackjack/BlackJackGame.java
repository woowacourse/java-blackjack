package blackjack;

import static blackjack.domain.DrawDecision.YES;

import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.player.HandCreator;
import blackjack.domain.player.Participant;
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
        Participant dealer = initDealer(cardDeck);
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

    private Participant initDealer(CardDeck cardDeck) {
        PlayerCreator playerCreator = new PlayerCreator(new HandCreator());
        return playerCreator.createDealerFrom(cardDeck);
    }

    private void printPlayersInformation(Players players, Participant dealer) {
        outputView.printHandOutEvent(players, 2);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        players.getPlayers().forEach(player -> completePlayerHand(player, cardDeck));
    }

    private void completePlayerHand(Participant participant, CardDeck cardDeck) {
        while (participant.canHit() && readHitDecision(participant) == YES) {
            participant.appendCard(cardDeck.popCard());
            outputView.printPlayerHand(participant);
        }
    }

    private DrawDecision readHitDecision(Participant participant) {
        InputMapper inputMapper = new InputMapper();
        return inputMapper.mapToDrawDecision(inputView.readDrawPlan(participant.getName()));
    }

    private void completeDealerHand(Participant dealer, CardDeck cardDeck) {
        while (dealer.canHit()) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
        }
    }

    private void printDealerPopCount(Participant dealer) {
        int dealerPopCount = dealer.handSize() - 2;
        if (dealerPopCount > 0) {
            outputView.printDealerPopCount(16, dealerPopCount);
        }
    }

    private void printPlayersScore(Players players) {
        players.getPlayers().forEach(this::printPlayerScore);
    }

    private void printPlayerScore(Participant participant) {
        outputView.printPlayerScore(participant, participant.calculateHandScore());
    }

    private void printDealerGameResult(Participant dealer, Players players) {
        Score dealerScore = dealer.calculateHandScore();
        int playerWinCount = players.countPlayerWithScoreAbove(dealerScore, new ScoreCalculateStrategy());
        int dealerWinCount = players.countPlayer() - playerWinCount;

        DealerGameResult dealerGameResult = new DealerGameResult(dealerWinCount, playerWinCount);
        outputView.printDealerGameResult(dealerGameResult);
    }

    private void printPlayersGameResult(Players players, Participant dealer) {
        Judge judge = new Judge();
        Score dealerScore = dealer.calculateHandScore();
        players.getPlayers()
                .forEach(player -> {
                    Score playerScore = player.calculateHandScore();
                    outputView.printPlayerGameResult(player, judge.isPlayerWin(dealerScore, playerScore));
                });
    }
}
