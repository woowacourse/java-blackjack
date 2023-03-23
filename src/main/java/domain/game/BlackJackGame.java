package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.card.shuffler.CardShuffler;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;
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

    public void hitOrStay(final boolean isHit, final Player player) {
        if (isHit) {
            giveCard(player);
            return;
        }
        player.stay();
    }

    private void giveCard(Player player) {
        if (!player.isHittable()) {
            throw new IllegalStateException("해당 플레이어는 더이상 카드를 받을 수 없습니다.");
        }
        player.hit(deck.pickCard());
    }

    public void stayDealer() {
        users.stayDealer();
    }

    public void giveCardToDealer() {
        if (!users.isDealerHittable()) {
            throw new IllegalStateException("딜러는 더이상 카드를 받을 수 없습니다.");
        }
        users.hitCardToDealer(deck.pickCard());
    }

    public GameResult getResult() {
        return GameResult.from(users);
    }

    public Card getDealerFirstCard() {
        return users.getDealerFirstCard();
    }

    public List<String> getPlayerNames() {
        return users.getPlayerNames();
    }

    public Map<String, List<Card>> getPlayerToCard() {
        return users.getPlayerToCard();
    }

    public Map<String, Integer> getPlayerToScore() {
        return users.getPlayerToScore();
    }

    public List<Player> getPlayers() {
        return users.getPlayers();
    }

    public List<Card> getDealerCards() {
        return users.getDealerCards();
    }

    public int getDealerScore() {
        return users.getDealerScore();
    }

    public boolean isDealerHittable() {
        return users.isDealerHittable();
    }
}
