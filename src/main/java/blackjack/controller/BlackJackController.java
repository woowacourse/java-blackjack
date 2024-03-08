package blackjack.controller;

import blackjack.domain.DealerGameResult;
import blackjack.domain.DrawDecision;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckCreator;
import blackjack.domain.player.Hand;
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

        issueInitialCardsToPlayers(players, cardDeck);
        issueInitialCardsToDealer(dealer, cardDeck);
        outputView.printHandOutEvent(players, 2);
        players.getPlayers().forEach(outputView::printPlayerHand);

        //딜러 카드 보여주는 메서드 만들어야 함 (카드 한장만)
        outputView.printPlayerHand(dealer);

        completePlayersHand(players, cardDeck);
        completeDealerHand(dealer, cardDeck);

        int dealerPopCount = dealer.getHand().getCards().size() - 2;
        outputView.printDealerPopCount(16, dealerPopCount);

        printPlayersScore(players);
        printDealerGameResult(dealer, players);

        printPlayersGameResult(players, dealer);

    }

    private void printPlayersScore(Players players) {
        players.getPlayers().forEach(this::printPlayerScore);

    }

    private void printPlayerScore(Player player) {
        Score score = calculateScore(player);
        outputView.printPlayerScore(player, score);
    }

    private void printPlayersGameResult(Players players, Player dealer) {
        Judge judge = new Judge();
        players.getPlayers()
                .forEach(player -> outputView.printPlayerGameResult(player, judge.isPlayerWin(dealer, player)));
    }

    private void printDealerGameResult(Player dealer, Players players) {
        Judge judge = new Judge();
        DealerGameResult dealerGameResult = judge.calculateDealerResult(dealer, players);
        outputView.printDealerGameResult(dealerGameResult);
    }

    private void completeDealerHand(Player dealer, CardDeck cardDeck) {
        Score dealerScore = calculateScore(dealer);
        HitStrategy hitStrategy = new DealerHitStrategy();

        while (dealerScore.hitAllowed(hitStrategy)) {
            Card card = cardDeck.popCard();
            dealer.appendCard(card);
        }
    }

    private Score calculateScore(Player player) {
        ScoreCalculateStrategy scoreCalculateStrategy = new ScoreCalculateStrategy();
        return player.calculateHandScore(scoreCalculateStrategy);
    }

    private void completePlayersHand(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            completePlayerHand(player, cardDeck);
        }
    }

    private void completePlayerHand(Player player, CardDeck cardDeck) {
        Judge judge = new Judge();
        while (!judge.isBustedHand(player.getHand())) {
            String name = player.getName();
            InputMapper inputMapper = new InputMapper();
            DrawDecision drawDecision = inputMapper.mapToDrawDecision(inputView.readDrawPlan(name));

            if (drawDecision == DrawDecision.NO) {
                break;
            }

            Card card = cardDeck.popCard();
            player.appendCard(card);
            outputView.printPlayerHand(player);
        }
    }

    private CardDeck initCardDeck() {
        CardDeckCreator cardDeckCreator = new CardDeckCreator();
        return cardDeckCreator.create();
    }

    private Players initPlayers() {
        InputMapper inputMapper = new InputMapper();
        List<PlayerName> playerNames = inputMapper.mapToPlayers(inputView.readNames());
        return Players.from(playerNames);
    }

    private Player initDealer() {
        Hand hand = new Hand(new ArrayList<>());
        return new Player(new PlayerName("딜러"), hand);
    }

    private List<Card> issueInitialHand(CardDeck cardDeck) {
        return cardDeck.popCards(2);
    }

    private void addIssuedCardToHand(Hand hand, List<Card> cards) {
        cards.forEach(hand::append);
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
}
