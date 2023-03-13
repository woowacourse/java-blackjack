package domain.game;

import domain.Exchanger;
import domain.bettingMoney.BettingMoneyTable;
import domain.bettingMoney.Money;
import domain.card.Card;
import domain.card.Hand;
import domain.deck.Deck;
import domain.dto.CardNames;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {
    private final Users users;
    private final Deck deck;
    private final Exchanger exchanger;

    private Blackjack(final Users users, final Deck deck, final Exchanger exchanger) {
        this.users = users;
        this.deck = deck;
        this.exchanger = exchanger;
    }

    public static Blackjack of(final Users users, final Deck deck, final BettingMoneyTable bettingMoneyTable) {
        initCards(users, deck);
        Exchanger exchanger = new Exchanger(bettingMoneyTable);
        return new Blackjack(users, deck, exchanger);
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
            Hand playerHand = player.getHand();
            playerToCard.put(player.getName(), new CardNames(playerHand.getCardNames()));
        }
        return playerToCard;
    }

    public CardNames getDealerCardNames() {
        Hand dealerHand = users.getDealer().getHand();
        return new CardNames(dealerHand.getCardNames());
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

    public Map<String, Money> calculateWinningMoneyOfPlayers() {
        Map<String, Money> winningMoneyOfPlayers = new HashMap<>();
        Dealer dealer = users.getDealer();
        for (Player player : users.getPlayers()) {
            Money winningMoney = exchanger.getPlayerWinningMoney(player, dealer);
            winningMoneyOfPlayers.put(player.getName(), winningMoney);
        }
        return winningMoneyOfPlayers;
    }

    public Money calculateWinningMoneyOfDealer(Map<String, Money> winningMoneyOfPlayers) {
        List<Money> playerMonies = winningMoneyOfPlayers.values().stream().collect(Collectors.toList());
        return exchanger.getDealerWinningMoney(playerMonies);
    }
}
