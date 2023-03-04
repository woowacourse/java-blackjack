package blackjack.controller;

import blackjack.model.WinningResult;
import blackjack.model.card.HandCard;
import blackjack.model.participant.Name;
import blackjack.model.card.*;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
import blackjack.model.state.DealerInitialState;
import blackjack.model.state.InitialState;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameController {
    private static final int DEALER_FIRST_CARD = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = initializedPlayers();
        Dealer dealer = new Dealer(new InitialState(new HandCard()));
        CardDeck cardDeck = new CardDeck();

        distributeFirstCards(players, dealer, cardDeck);

        playHitOrStand(players, dealer, cardDeck);

        printDealerScoreResult(dealer);
        printPlayerScoreResult(players);

        printWinningResult(players, dealer);
    }

    private void printWinningResult(List<Player> players, Dealer dealer) {
        outputView.printWinningResultMessage();
        Map<String, WinningResult> results = dealer.winningResults(players);
        WinningResult dealerResult = results.remove(dealer.getName());
        outputView.printDealerWinningResult(dealerResult.getWin(), dealerResult.getDraw(), dealerResult.getLose());

        Map<String, List<Integer>> playerResults = new HashMap<>();
        for (Map.Entry<String, WinningResult> playerResult : results.entrySet()) {
            playerResults.put(playerResult.getKey(), playerResult.getValue().getResult());
        }
        outputView.printPlayersWinningResult(playerResults);
    }

    private void printPlayerScoreResult(List<Player> players) {
        for (Player player : players) {
            CardScore cardScore = player.cardScore();

            String result = Integer.toString(cardScore.getValidScore());
            if (player.isBlackjack()) {
                result += " (블랙잭!!)";
            }

            outputView.printScoreResult(playerNamesAndHands(List.of(player)), result);
        }
    }

    private void playHitOrStand(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        for (Player player : players) {
            hitOrStandByPlayer(cardDeck, player);
        }
        hitOrStandByDealer(cardDeck, dealer);
    }

    private void distributeFirstCards(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        distributeCards(players, dealer, cardDeck);
        printCardDistribution(players, dealer);
    }

    private void distributeCards(List<Player> players, Dealer dealer, CardDeck cardDeck) {
        dealer.play(cardDeck);
        for (Player player : players) {
            player.play(cardDeck);
        }
    }

    private void printCardDistribution(List<Player> players, Dealer dealer) {
        List<String> names = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        outputView.printDistributionMessage(names);
        outputView.printNameAndHand(dealerNameAndHand(dealer));
        outputView.printNameAndHand(playerNamesAndHands(players));
    }

    private void hitOrStandByPlayer(CardDeck cardDeck, Player player) {
        while (!player.isFinished()) {
            boolean isHit = inputView.readHitOrStand(player.getName());
            hitOrStand(cardDeck, player, isHit);
            outputView.printNameAndHand(playerNamesAndHands(List.of(player)));
        }
    }

    private void hitOrStand(CardDeck cardDeck, Player player, boolean isHit) {
        if (isHit) {
            player.play(cardDeck);
            return;
        }
        player.changeToStand();
    }

    private void hitOrStandByDealer(CardDeck cardDeck, Dealer dealer) {
        while (!dealer.isFinished()) {
            dealer.play(cardDeck);
            outputView.printDealerHitMessage();
        }
    }

    private void printDealerScoreResult(Dealer dealer) {
        String dealerResult = Integer.toString(dealer.getScore());
        if (dealer.isBlackjack()) {
            dealerResult += " (블랙잭!!)";
        }
        outputView.printScoreResult(singleNameAndHand(dealer), dealerResult);
    }

    private Map<String, List<String>> dealerNameAndHand(Dealer dealer) {
        String name = dealer.getName();
        Card card = dealer.getCards().get(DEALER_FIRST_CARD);

        Map<String, List<String>> nameAndHand = new HashMap<>();
        nameAndHand.put(name, List.of(cardUnit(card.getNumber(), card.getSuit())));
        return nameAndHand;
    }

    private List<Player> initializedPlayers() {
        List<String> playerNames = inputView.readNames();

        return playerNames.stream()
                .map(name -> new Player(new Name(name), new InitialState(new HandCard())))
                .collect(Collectors.toList());
    }

    public Map<String, List<String>> playerNamesAndHands(List<Player> players) {
        HashMap<String, List<String>> namesAndHands = new HashMap<>();
        for (Player player : players) {
            Map<String, List<String>> singleNameAndHand = singleNameAndHand(player);
            namesAndHands.putAll(singleNameAndHand);
        }
        return namesAndHands;
    }

    private Map<String, List<String>> singleNameAndHand(Participant participant) {
        HashMap<String, List<String>> nameAndHand = new HashMap<>();
        String name = participant.getName();
        List<String> hand = participantCardUnit(participant);
        nameAndHand.put(name, hand);
        return nameAndHand;
    }

    private List<String> participantCardUnit(Participant participant) {
        List<String> hand = participant.getCards()
                .stream()
                .map(card -> cardUnit(card.getNumber(), card.getSuit()))
                .collect(Collectors.toList());
        return hand;
    }

    private String cardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }
}
