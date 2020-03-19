package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.*;

public class BlackjackGame {
    public static final int INITIAL_CARDS = 2;

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, List<Player> players, Deck deck) {
        Objects.requireNonNull(dealer, "dealer가 null일 수 없습니다");
        Objects.requireNonNull(players, "players가 null일 수 없습니다");
        Objects.requireNonNull(deck, "deck이 null일 수 없습니다");
        this.dealer = dealer;
        this.players = Collections.unmodifiableList(players);
        this.deck = deck;
    }

    public BlackjackGame(List<Player> players, Deck deck) {
        this(new Dealer(), players, deck);
    }

    public void distributeInitialCards() {
        dealer.receiveInitialCards(deck.draw(INITIAL_CARDS));
        players.forEach(t -> t.receiveInitialCards(deck.draw(INITIAL_CARDS)));
    }

    public boolean dealerHitsAdditionalCard() {
        if (dealer.isUnderThreshold()) {
            dealer.receiveCard(deck.draw());
            return true;
        }
        return false;
    }

    public void hitCard(Player player) {
        player.receiveCard(deck.draw());
    }

    public boolean dealerIsBlackJack() {
        return dealer.isBlackJack();
    }

    public TotalResult calculateResultsPerPlayer() {
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        players.forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        return new TotalResult(totalResult);
    }

    public void updateUserMoney() {
        TotalResult totalResult = calculateResultsPerPlayer();
        totalResult.getResult().forEach((player, result) -> player.addMoney(player.getProfitByResult(result)));
        dealer.addMoney(totalResult.calculateDealerProfit());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
