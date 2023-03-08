package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int EXTRA_CARD_COUNT = 1;
    private static final int INIT_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackJackGame createByPlayerNames(List<String> names) {
        Players players = Players.create(names);
        Dealer dealer = new Dealer();
        Deck deck = Deck.create(Card.getAllCards());
        return new BlackJackGame(players, dealer, deck);
    }

    public void setUp() {
        deck.shuffle();

        List<Player> players = this.players.getPlayers();
        for (Player player : players) {
            drawCard(player, INIT_CARD_COUNT);
        }
        drawCard(dealer, INIT_CARD_COUNT);
    }

    public void passDealerCard() {
        if (dealer.canReceive()) {
            drawCard(dealer, EXTRA_CARD_COUNT);
        }
    }

    public void passPlayerCard(int playerIndex) {
        Player player = players.getPlayers().get(playerIndex);
        if (player.canReceive()) {
            drawCard(player, EXTRA_CARD_COUNT);
        }
    }

    private void drawCard(Participant participant, int count) {
        for (int cardCount = 0; cardCount < count; cardCount++) {
            passCard(participant);
        }
    }

    private void passCard(Participant participant) {
        if (participant.canReceive()) {
            Card card = deck.draw();
            participant.addCard(card);
        }
    }

    public String findPlayerName(int playerIndex) {
        return players.getPlayers().get(playerIndex).getName();
    }

    public boolean canPassDealerCard() {
        return dealer.canReceive();
    }

    public List<String> findAllPlayerNames() {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public CardDTO findDealerFirstCard() {
        Card card = dealer.getFirst();
        return new CardDTO(card.getSuit(), card.getDenomination());
    }

    public boolean canPassPlayerCard(int playerIndex) {
        return players.getPlayers().get(playerIndex).canReceive();
    }

    public PlayerNameHandResponse findPlayerNameHand(int playerIndex) {
        Player player = players.getPlayers().get(playerIndex);
        return convertNameHand(player);
    }

    public List<PlayerNameHandResponse> findAllPlayerNameHand() {
        List<PlayerNameHandResponse> allPlayerNameHand = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            allPlayerNameHand.add(convertNameHand(player));
        }
        return allPlayerNameHand;
    }

    public int getTotalPlayerCount() {
        return players.getPlayers().size();
    }

    public DealerHandScoreResponse findDealerHandScore() {
        return new DealerHandScoreResponse(
                convertCardDTO(dealer.getHand()),
                dealer.calculateScore().getValue()
        );
    }

    public List<PlayerNameHandScoreResponse> findAllPlayerNameHandScore() {
        List<PlayerNameHandScoreResponse> allPlayerNameHandScore = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            allPlayerNameHandScore.add(convertNameHandScore(player));
        }
        return allPlayerNameHandScore;
    }

    public DealerPlayerResultResponse findDealerPlayerResult() {
        Map<Result, Integer> dealerResult = calculateDealerResult();
        Map<String, Result> allPlayerResult = calculatePlayerResult();

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
        for (Player player : players.getPlayers()) {
            String name = player.getName();
            Result result = Result.calculate(player.calculateScore(), dealer.calculateScore());
            allPlayerResult.put(name, result);
        }
        return allPlayerResult;
    }

    private Map<Result, Integer> calculateDealerResult() {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Result result = Result.calculate(dealer.calculateScore(), player.calculateScore());
            int count = dealerResult.getOrDefault(result, 0);
            dealerResult.put(result, count + 1);
        }
        return dealerResult;
    }
}
