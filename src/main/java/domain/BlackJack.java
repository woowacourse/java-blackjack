package domain;

import static domain.GameResult.LOSE;
import static domain.GameResult.PUSH;
import static domain.GameResult.WIN;
import static domain.GameResult.comparePlayerWithDealer;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJack {

    private final Users users;
    private final Deck deck;

    private BlackJack(final Users users, final Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJack of(final Users users, final CardIndexGenerator cardIndexGenerator) {
        Deck deck = Deck.from(cardIndexGenerator);
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
        return players.stream()
            .collect(Collectors.toMap
                (Player::getName,
                    player -> comparePlayerWithDealer(player.getScore(), dealerScore),
                    (oldValue, newValue) -> newValue,
                    LinkedHashMap::new));
    }

    public Map<GameResult, Integer> calculateDealerResult() {
        Map<GameResult, Integer> dealerResult = new HashMap<>();
        Map<String, GameResult> playerResults = calculatePlayerResults();
        playerResults.values().forEach(result -> convertResult(result, dealerResult));
        return dealerResult;
    }

    private void convertResult(final GameResult playerResult, final Map<GameResult, Integer> dealerResult) {
        Map<GameResult, GameResult> converter = Map.of(WIN, LOSE, LOSE, WIN, PUSH, PUSH);
        GameResult convertedResult = converter.get(playerResult);
        dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
    }

    public Card getDealerCardWithHidden() {
        Dealer dealer = users.getDealer();
        return dealer.getCards().get(0);
    }

    public Map<String, List<Card>> getPlayerToCard() {
        List<Player> players = users.getPlayers();
        return players.stream().collect(Collectors.toMap(
            Player::getName,
            Player::getCards,
            (oldValue, newValue) -> newValue,
            LinkedHashMap::new
        ));
    }

    public List<Player> getHittablePlayers() {
        return users.getHittablePlayers();
    }

    public boolean isDealerHittable() {
        return users.isDealerHittable();
    }
}
