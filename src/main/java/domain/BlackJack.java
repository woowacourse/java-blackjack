package domain;

import static domain.GameResult.LOSE;
import static domain.GameResult.PUSH;
import static domain.GameResult.WIN;

import domain.shuffler.CardShuffler;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJack {

    private final Users users;
    private final Deck deck;

    private BlackJack(final Users users, final Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJack of(final Users users, final CardShuffler cardShuffler) {
        Deck deck = Deck.from(cardShuffler);
        initCards(users, deck);
        return new BlackJack(users, deck);
    }

    private static void initCards(Users users, Deck deck) {
        for (Player player : users.getPlayers()) {
            hitTwoCards(player, deck);
        }
        hitTwoCards(users.getDealer(), deck);
    }

    private static void hitTwoCards(User user, Deck deck) {
        user.hit(deck.pickCard());
        user.hit(deck.pickCard());
    }

    public void giveCard(String playerName) {
        users.hitCardByName(playerName, deck.pickCard());
    }

    public void giveCardToDealer() {
        Dealer dealer = users.getDealer();
        dealer.hit(deck.pickCard());
    }

    public Map<String, GameResult> calculatePlayerResults() {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        int dealerScore = dealer.getScore();
        Map<String, GameResult> playerResults = new LinkedHashMap<>();
        for (Player player : players) {
            GameResult playerResult = GameResult.comparePlayerWithDealer(player.getScore(), dealerScore);
            playerResults.put(player.getName(), playerResult);
        }
        return Collections.unmodifiableMap(playerResults);
    }

    public Map<GameResult, Integer> calculateDealerResult() {
        Map<GameResult, Integer> dealerResult = new EnumMap<>(GameResult.class);
        Map<String, GameResult> playerResults = calculatePlayerResults();
        playerResults.values().forEach(result -> convertResult(result, dealerResult));
        return dealerResult;
    }

    private void convertResult(final GameResult playerResult, final Map<GameResult, Integer> dealerResult) {
        Map<GameResult, GameResult> converter = setUpResultConverter();
        GameResult convertedResult = converter.get(playerResult);
        dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
    }

    private Map<GameResult, GameResult> setUpResultConverter() {
        Map<GameResult, GameResult> converter = new EnumMap<>(GameResult.class);
        converter.put(WIN, LOSE);
        converter.put(LOSE, WIN);
        converter.put(PUSH, PUSH);
        return converter;
    }

    public Card getDealerCardWithHidden() {
        Dealer dealer = users.getDealer();
        return dealer.getCardWithHidden();
    }

    public Map<String, List<Card>> getPlayerToCard() {
        List<Player> players = users.getPlayers();
        Map<String, List<Card>> playerToCard = new LinkedHashMap<>();
        for (Player player : players) {
            playerToCard.put(player.getName(), player.getCards());
        }
        return Collections.unmodifiableMap(playerToCard);
    }

    public Map<String, Integer> getPlayerToScore() {
        List<Player> players = users.getPlayers();
        Map<String, Integer> playerToScore = new LinkedHashMap<>();
        for (Player player : players) {
            playerToScore.put(player.getName(), player.getScore());
        }
        return Collections.unmodifiableMap(playerToScore);
    }

    public List<Player> getHittablePlayers() {
        return users.getHittablePlayers();
    }

    public List<Card> getDealerCards() {
        return users.getDealer().getCards();
    }

    public int getDealerScore() {
        return users.getDealer().getScore();
    }

    public boolean isDealerHittable() {
        return users.isDealerHittable();
    }
}
