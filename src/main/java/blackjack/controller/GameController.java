package blackjack.controller;

import blackjack.Hand;
import blackjack.model.Name;
import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Participant;
import blackjack.model.participant.Player;
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

    public void run(){
        List<Player> players = initializedPlayers(inputView);
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        CardDeck cardDeck = new CardDeck();

        //카드 나누기
        distributeFirstCards(players, dealer, cardDeck);

        //플레이어 HitOrStand
        for (Player player : players) {
            hitOrStandByPlayer(cardDeck, player);
        }
        //딜러 HitOrStand
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

    private Map<String, List<String>> dealerNameAndHand(Dealer dealer) {
        String name = dealer.getName();
        Card card = dealer.getCards().get(DEALER_FIRST_CARD);

        Map<String, List<String>> nameAndHand = new HashMap<>();
        nameAndHand.put(name, List.of(cardUnit(card.getNumber(), card.getSuit())));
        return nameAndHand;
    }

    private List<Player> initializedPlayers(InputView inputView) {
        List<String> playerNames = inputView.readNames();

        return playerNames.stream()
                .map(name -> new Player(new Name(name), new InitialState(new Hand())))
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
