package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.card.shuffler.CardShuffler;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Users users;
    private final Deck deck;

    private BlackJackGame(final Users users, final Deck deck) {
        this.users = users;
        this.deck = deck;
    }

    public static BlackJackGame of(final Users users, final CardShuffler cardShuffler) {
        Deck deck = Deck.from(cardShuffler);
        initCards(users, deck);
        return new BlackJackGame(users, deck);
    }

    private static void initCards(Users users, Deck deck) {
        for (User user : users.getUsers()) {
            hitTwoCards(user, deck);
        }
    }

    private static void hitTwoCards(User user, Deck deck) {
        user.hit(deck.pickCard());
        user.hit(deck.pickCard());
    }

    public void stay(String playerName) {
        users.stayByName(playerName);
    }

    public void stayDealerIfRunning() {
        users.stayDealerIfRunning();
    }

    public void giveCard(String playerName) {
        users.hitCardByName(playerName, deck.pickCard());
    }

    public void giveCardToDealer() {
        Dealer dealer = users.getDealer();
        if (dealer.isDrawable()) {
            dealer.hit(deck.pickCard());
        }

    }

    public GameResult getResult() {
        return GameResult.of(users);
    }

    public Card getDealerCardWithHidden() {
        Dealer dealer = users.getDealer();
        return dealer.getFirstCard();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
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
