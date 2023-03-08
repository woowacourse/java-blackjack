package domain.game;

import domain.card.Card;
import domain.card.Cards;
import domain.deck.Deck;
import domain.dto.CardNames;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
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

    public static BlackJack of(final Users users, final Deck deck) {
        initCards(users, deck);
        return new BlackJack(users, deck);
    }

    private static void initCards(final Users users, final Deck deck) {
        for (Player player : users.getPlayers()) {
            hitTwoCards(player, deck);
        }
        hitTwoCards(users.getDealer(), deck);
    }

    private static void hitTwoCards(final User user, final Deck deck) {
        user.hit(deck.pickCard());
        user.hit(deck.pickCard());
    }

    public void giveCard(final String playerName) {
        users.hitCardByName(playerName, deck.pickCard());
    }

    public void giveCardToDealer() {
        Dealer dealer = users.getDealer();
        dealer.hit(deck.pickCard());
    }

    public Card getDealerCardWithHidden() {
        Dealer dealer = users.getDealer();
        return dealer.getVisibleCard();
    }

    public Map<String, CardNames> getPlayerToCardNames() {
        List<Player> players = users.getPlayers();
        Map<String, CardNames> playerToCard = new LinkedHashMap<>();
        for (Player player : players) {
            Cards playerCards = player.getCards();
            playerToCard.put(player.getName(), new CardNames(playerCards.getCardNames()));
        }
        return playerToCard;
    }

    public CardNames getDealerCardNames() {
        Cards dealerCards = users.getDealer().getCards();
        return new CardNames(dealerCards.getCardNames());
    }

    public List<Player> getHittablePlayers() {
        return users.getHittablePlayers();
    }

    public boolean isHittablePlayer(final Player player) {
        return player.isHittable();
    }

    public boolean isHittableDealer() {
        return users.isHittableDealer();
    }

    public Map<String, Integer> getPlayerToScore() {
        List<Player> players = users.getPlayers();
        Map<String, Integer> playerToScore = new LinkedHashMap<>();
        for (Player player : players) {
            playerToScore.put(player.getName(), player.getScore().getValue());
        }
        return playerToScore;
    }

    public int getDealerScore() {
        return users.getDealer().getScore().getValue();
    }

    public Map<String, Result> calculateTotalPlayerResults() {
        List<Player> players = users.getPlayers();
        Dealer dealer = users.getDealer();
        return Referee.judgeTotalPlayerResult(players, dealer);
    }

    public Map<Result, Integer> calculateTotalDealerResult(final Map<String, Result> totalPlayerResult) {
        return Referee.judgeTotalDealerResult(totalPlayerResult);
    }
}
