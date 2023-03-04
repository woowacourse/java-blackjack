package blackjack.controller;

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

        //승패 결과 계산

        Map<String, String> playerResult = new HashMap<>();
        int win = 0;
        int same = 0;
        int lose = 0;
        for (Player player : players) {
            String name = player.getName();

            if (dealer.isBlackjack()) {
                if (player.isBlackjack()) {
                    same++;
                    playerResult.put(name, "무");
                    continue;
                }
                win++;
                playerResult.put(name, "패");
                continue;
            }
            if (dealer.isBust()) {
                if (player.isBust()) {
                    win++;
                    playerResult.put(name, "패");
                    continue;
                }
                lose++;
                playerResult.put(name, "승");
                continue;
            }
            if (dealer.isStand()) {
                if (player.isStand()) {
                    int dealerSum = dealer.getScore();
                    int playerSum = player.getScore();
                    if (dealerSum > playerSum) {
                        win++;
                        playerResult.put(name, "패");
                        continue;
                    }
                    if (dealerSum < playerSum) {
                        lose++;
                        playerResult.put(name, "승");
                        continue;
                    }
                    same++;
                    playerResult.put(name, "무");
                    continue;
                }
                if (player.isBlackjack()) {
                    lose++;
                    playerResult.put(name, "승");
                    continue;
                }
                win++;
                playerResult.put(name, "패");
                continue;
            }
        }

        outputView.printWinningResultMessage();
        outputView.printDealerWinningResult(List.of(win, same, lose));
        outputView.printPlayersWinningResult(playerResult);
    }

    private void printPlayerScoreResult(List<Player> players) {
        for (Player player : players) {
            CardScore cardScore = player.cardScore();

            String result = Integer.toString(cardScore.smallScore());
            if (player.isBlackjack()) {
                result = Integer.toString(cardScore.bigScore());
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
