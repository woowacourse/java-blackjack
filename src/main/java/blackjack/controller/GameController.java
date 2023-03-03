package blackjack.controller;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.participant.*;
import blackjack.model.result.Result;
import blackjack.model.state.InitialState;
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
        CardDeck cardDeck = new CardDeck();
        Participants participants = initializedParticipants();

        participants.distributeTwoCardsToEach(cardDeck);
        printInitialCardStatus(participants);

        hitOrStand(cardDeck, participants);
        printFinalCardStatus(participants);

        printWinningResult(participants.playerResult());
    }

    private Participants initializedParticipants() {
        List<Player> players = inputView.readNames().stream()
                .map(name -> new Player(new Name(name), new InitialState(new Hand())))
                .collect(Collectors.toList());
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        return new Participants(dealer, players);
    }

    private void printInitialCardStatus(Participants participants) {
        List<String> players = participants.getPlayers().stream()
                .map(player -> player.getName().getName())
                .collect(Collectors.toList());
        outputView.printDistributionMessage(players);
        outputView.printNameAndHand(dealerNameAndHand(participants.getDealer()));
        outputView.printNameAndHand(playerNamesAndHands(participants.getPlayers()));
    }

    private Map<String, List<String>> dealerNameAndHand(Dealer dealer) {
        String name = dealer.getName().getName();
        Card card = dealer.getFirstCard();

        Map<String, List<String>> nameAndHand = new HashMap<>();
        nameAndHand.put(name, List.of(cardUnit(card.getNumber(), card.getSuit())));
        return nameAndHand;
    }

    private Map<String, List<String>> playerNamesAndHands(List<Player> players) {
        HashMap<String, List<String>> namesAndHands = new HashMap<>();
        for (Player player : players) {
            namesAndHands.put(player.getName().getName(), participantCardUnits(player));
        }
        return namesAndHands;
    }

    private List<String> participantCardUnits(Participant participant) {
        return participant.getHand().getCards().stream()
                .map(card -> cardUnit(card.getNumber(), card.getSuit()))
                .collect(Collectors.toList());
    }

    private String cardUnit(CardNumber number, CardSuit suit) {
        return number.getSymbol() + suit.getSuit();
    }

    private void hitOrStand(CardDeck cardDeck, Participants participants) {
        while (participants.hasNextPlayer()) {
            Player player = participants.nextPlayer();
            boolean isHit = inputView.readHitOrStand(player.getName().getName());
            participants.hitOrStandByPlayer(cardDeck, player, isHit);
            outputView.printNameAndHand(participantNamesAndHands(player));
        }
        outputView.printDealerHitMessage(participants.hitOrStandByDealer(cardDeck));
    }

    private void printFinalCardStatus(Participants participants) {
        for (Participant participant : participants.getParticipants()) {
            int score = participant.getScore();
            boolean isBlackjack = participant.isBlackjack();
            outputView.printScoreResult(participantNamesAndHands(participant), score, isBlackjack);
        }
    }

    private Map<String, List<String>> participantNamesAndHands(Participant participant) {
        HashMap<String, List<String>> nameAndHand = new HashMap<>();
        String name = participant.getName().getName();
        List<String> hand = participantCardUnits(participant);
        nameAndHand.put(name, hand);
        return nameAndHand;
    }

    private void printWinningResult(Map<Name, Result> playerResult) {
        outputView.printWinningResultMessage();

        long dealerWin = playerResult.values().stream().filter(value -> value.equals(Result.LOSE)).count();
        long dealerTie = playerResult.values().stream().filter(value -> value.equals(Result.TIE)).count();
        long dealerLose = playerResult.values().stream().filter(value -> value.equals(Result.WIN)).count();
        outputView.printDealerWinningResult(dealerWin, dealerTie, dealerLose);

        outputView.printPlayersWinningResult(namesAndResults(playerResult));
    }

    private Map<String, String> namesAndResults(Map<Name, Result> playerResult) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Name, Result> entry : playerResult.entrySet()) {
            result.put(entry.getKey().getName(), entry.getValue().getResult());
        }
        return result;
    }

}
