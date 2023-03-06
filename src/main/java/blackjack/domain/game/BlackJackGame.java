package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.dto.DealerCardsScoreResponse;
import blackjack.dto.DealerPlayerResultResponse;
import blackjack.dto.PlayerNameCardsResponse;
import blackjack.dto.PlayerNameCardsScoreResponse;

import java.util.*;
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
        Deck deck = Deck.create(CardFactory.createShuffledCard());
        return new BlackJackGame(players, dealer, deck);
    }

    public void setUp() {
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

    public List<String> findAllPlayerNames() {
        return players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Card findDealerFirstCard() {
        return dealer.getCards().getCards().get(0);
    }

    public String findPlayerName(int playerIndex) {
        return players.getPlayers().get(playerIndex).getName();
    }

    public int getTotalPlayerCount() {
        return players.getPlayers().size();
    }

    public boolean canPassDealerCard() {
        return dealer.canReceive();
    }

    public boolean canPassPlayerCard(int playerIndex) {
        return players.getPlayers().get(playerIndex).canReceive();
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

    public PlayerNameCardsResponse findPlayerNameAndCards(int playerIndex) {
        Player player = players.getPlayers().get(playerIndex);
        return new PlayerNameCardsResponse(player);
    }

    public List<PlayerNameCardsResponse> findAllPlayerNameAndCards() {
        List<PlayerNameCardsResponse> allPlayerNameCards = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            allPlayerNameCards.add(new PlayerNameCardsResponse(player));
        }
        return allPlayerNameCards;
    }

    public DealerCardsScoreResponse findDealerCardsScore() {
        return new DealerCardsScoreResponse(dealer);
    }

    public List<PlayerNameCardsScoreResponse> findAllPlayerNameCardsScore() {
        List<PlayerNameCardsScoreResponse> allPlayerNameCardsScore = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            allPlayerNameCardsScore.add(new PlayerNameCardsScoreResponse(player));
        }
        return allPlayerNameCardsScore;
    }

    public DealerPlayerResultResponse findDealerPlayerResult() {
        Map<Result, Integer> dealerResult = calculateDealerResult();
        Map<String, Result> allPlayerResult = calculatePlayerResult();

        return new DealerPlayerResultResponse(dealerResult, allPlayerResult);
    }

    private Map<String, Result> calculatePlayerResult() {
        Map<String, Result> allPlayerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            String name = player.getName();
            Result result = Result.calculate(player, dealer);
            allPlayerResult.put(name, result);
        }
        return allPlayerResult;
    }

    private Map<Result, Integer> calculateDealerResult() {
        Map<Result, Integer> dealerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            Result result = Result.calculate(dealer, player);
            int count = dealerResult.getOrDefault(result, 0);
            dealerResult.put(result, count + 1);
        }
        return dealerResult;
    }
}
