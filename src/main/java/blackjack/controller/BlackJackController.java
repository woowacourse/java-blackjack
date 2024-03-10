package blackjack.controller;

import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckCreator;
import blackjack.domain.card.Hand;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import blackjack.domain.player.Players;
import blackjack.domain.rule.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = initPlayers();
        CardDeck cardDeck = initCardDeck();
        Player dealer = initDealer();

        prepareGame(players, cardDeck, dealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);

        printDealerPopCount(dealer);

        printPlayerScore(dealer);
        printPlayersScore(players);
        printDealerGameResult(dealer, players);
        printPlayersGameResult(players, dealer);
    }

    private Players initPlayers() {
        InputMapper inputMapper = new InputMapper();
        List<PlayerName> playerNames = inputMapper.mapToPlayers(inputView.readNames());
        return Players.from(playerNames);
    }

    private CardDeck initCardDeck() {
        CardDeckCreator cardDeckCreator = new CardDeckCreator();
        return cardDeckCreator.create();
    }

    private Player initDealer() {
        Hand hand = new Hand(new ArrayList<>());
        return new Player(new PlayerName("딜러"), hand);
    }

    private void prepareGame(Players players, CardDeck cardDeck, Player dealer) {
        issueInitialCardsToPlayers(players, cardDeck);
        issueInitialCardsToDealer(dealer, cardDeck);

        outputView.printHandOutEvent(players, 2);
        outputView.printDealerInitialHand(dealer);
        players.getPlayers().forEach(outputView::printPlayerHand);
    }

    private void issueInitialCardsToPlayers(Players players, CardDeck cardDeck) {
        players.getPlayers().stream()
                .map(Player::getHand)
                .forEach(hand -> addIssuedCardToHand(hand, issueInitialHand(cardDeck)));
    }

    private void issueInitialCardsToDealer(Player dealer, CardDeck cardDeck) {
        List<Card> cards = issueInitialHand(cardDeck);
        addIssuedCardToHand(dealer.getHand(), cards);
    }

    private List<Card> issueInitialHand(CardDeck cardDeck) {
        return cardDeck.popCards(2);
    }

    private void addIssuedCardToHand(Hand hand, List<Card> cards) {
        cards.forEach(hand::append);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            completePlayerHand(player, cardDeck);
        }
    }

    private void completePlayerHand(Player player, CardDeck cardDeck) {
        Score playerScore = calculateScore(player);
        HitStrategy hitStrategy = new PlayerHitStrategy();

        while (playerScore.hitAllowed(hitStrategy)) {
            DrawDecision drawDecision = readHitDecision(player);
            if (drawDecision == DrawDecision.NO) {
                break;
            }
            Card card = cardDeck.popCard();
            player.appendCard(card);
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
        Judge judge = new Judge();

        int playerWinCount = (int) players.getPlayers().stream()
                .map(this::calculateScore)
                .filter(playerScore -> judge.isPlayerWin(dealerScore, playerScore))
                .count();

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

    private Score calculateScore(Player player) {
        ScoreCalculateStrategy scoreCalculateStrategy = new ScoreCalculateStrategy();
        return player.calculateHandScore(scoreCalculateStrategy);
    }
}
