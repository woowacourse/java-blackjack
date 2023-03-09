package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.dto.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Participants participants;
    private final Deck deck;

    private BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public static BlackJackGame createByPlayerNames(List<String> names) {
        Participants participants = Participants.create(names);
        Deck deck = Deck.create(Card.getAllCards());
        return new BlackJackGame(participants, deck);
    }

    public void setUp() {
        deck.shuffle();

        participants.drawInitialCard(deck);
    }

    public void passExtraCardToPlayer(String name) {
        Player player = participants.findPlayerByName(name);
        if (player.canReceive()) {
            Card card = deck.draw();
            player.addCard(card);
        }
    }

    public void passExtraCardToDealer() {
        Dealer dealer = participants.findDealer();
        if (dealer.canReceive()) {
            Card card = deck.draw();
            dealer.addCard(card);
        }
    }

    public List<String> findAllPlayerNames() {
        return participants.findAllPlayerNames();
    }

    public CardDTO findDealerFirstCard() {
        Card card = participants.findDealer().getFirst();
        return new CardDTO(card.getSuit(), card.getDenomination());
    }

    public boolean canPassCardToPlayer(String playerName) {
        Player player = participants.findPlayerByName(playerName);
        return player.canReceive();
    }

    public boolean canPassCardToDealer() {
        Dealer dealer = participants.findDealer();
        return dealer.canReceive();
    }

    public PlayerNameHandResponse findPlayerNameHand(String playerName) {
        Player player = participants.findPlayerByName(playerName);
        return convertNameHand(player);
    }

    public List<PlayerNameHandResponse> findAllPlayerNameHand() {
        List<String> allPlayerNames = participants.findAllPlayerNames();
        return allPlayerNames.stream()
                .map(playerName -> convertNameHand(participants.findPlayerByName(playerName)))
                .collect(Collectors.toList());
    }

    public DealerHandScoreResponse findDealerHandScore() {
        Dealer dealer = participants.findDealer();
        return new DealerHandScoreResponse(
                convertCardDTO(dealer.getHand()),
                dealer.calculateScore().getValue()
        );
    }

    public List<PlayerNameHandScoreResponse> findAllPlayerNameHandScore() {
        List<String> allPlayerNames = participants.findAllPlayerNames();
        return allPlayerNames.stream()
                .map(playerName -> convertNameHandScore(participants.findPlayerByName(playerName)))
                .collect(Collectors.toList());
    }

    public DealerPlayerResultResponse findDealerPlayerResult() {
        Map<String, Result> allPlayerResult = calculatePlayerResult();
        Map<Result, Integer> dealerResult = calculateDealerResult(allPlayerResult);

        return new DealerPlayerResultResponse(dealerResult, allPlayerResult);
    }

    private PlayerNameHandResponse convertNameHand(Player player) {
        return new PlayerNameHandResponse(
                player.getName(),
                convertCardDTO(player.getHand())
        );
    }

    private PlayerNameHandScoreResponse convertNameHandScore(Player player) {
        return new PlayerNameHandScoreResponse(
                player.getName(),
                convertCardDTO(player.getHand()),
                player.calculateScore().getValue()
        );
    }

    private List<CardDTO> convertCardDTO(Hand hand) {
        return hand.getHand().stream()
                .map(card -> new CardDTO(card.getSuit(), card.getDenomination()))
                .collect(Collectors.toList());
    }

    private Map<String, Result> calculatePlayerResult() {
        Map<String, Result> allPlayerResult = new LinkedHashMap<>();
        Dealer dealer = participants.findDealer();
        for (String playerName : participants.findAllPlayerNames()) {
            Player player = participants.findPlayerByName(playerName);
            Result result = Result.calculatePlayerResult(player.calculateScore(), dealer.calculateScore());
            allPlayerResult.put(playerName, result);
        }
        return allPlayerResult;
    }

    private Map<Result, Integer> calculateDealerResult(Map<String, Result> allPlayerResult) {
        Map<Result, Integer> dealerResults = new LinkedHashMap<>();
        for (String playerName : allPlayerResult.keySet()) {
            Result dealerResult = Result.oppositeResult(allPlayerResult.get(playerName));
            int count = dealerResults.getOrDefault(dealerResult, 0);
            dealerResults.put(dealerResult, count + 1);
        }
        return dealerResults;
    }
}
