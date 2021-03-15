package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.state.DealerTurnOver;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Round {
    private static final int FIRST_INDEX = 0;

    private final Deck deck;
    private final Users users;

    private Round(Deck deck, Users users) {
        this.deck = deck;
        this.users = users;
    }

    public static Round valueOf(Deck deck, List<String> playerNames, List<BigDecimal> bettingMoneys) {
        List<AbstractUser> users = new ArrayList<>();
        users.add(new Dealer(drawTwoCard(deck)));

        List<AbstractUser> players = playerNames.stream()
                .map(playerName -> new Player(drawTwoCard(deck), playerName, bettingMoneys.remove(FIRST_INDEX)))
                .collect(Collectors.toList());

        users.addAll(players);

        return new Round(deck, new Users(users));
    }

    private static State drawTwoCard(Deck deck) {
        return StateFactory.draw(deck.makeOneCard(), deck.makeOneCard());
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public List<AbstractUser> getPlayers() {
        return Collections.unmodifiableList(users.getPlayers());
    }

    public List<Card> getDealerCards() {
        return getDealer().getCards();
    }

    public boolean isDealerCanDraw() {
        return getDealer().canDraw();
    }

    public int calculateDealerScore() {
        return getDealer().calculateScore();
    }

    public void addDealerCard() {
        AbstractUser findDealer = getDealer();
        State state = users.dealerDraw(deck.makeOneCard());
        if (!state.isFinish() && !findDealer.canDraw()) {
            findDealer.changeState(new DealerTurnOver(state.cards()));
        }
    }

    public void addPlayerCard(AbstractUser player) {
        AbstractUser findPlayer = findPlayer(player);
        State state = users.playerDraw(deck.makeOneCard(), player);
        findPlayer.changeState(state);
    }

    public void makePlayerStay(AbstractUser player) {
        AbstractUser findPlayer = findPlayer(player);
        State state = findPlayer.stay();
        findPlayer.changeState(state);
    }

    public Map<String, BigDecimal> findUsersProfit() {
        Map<String, BigDecimal> result = new LinkedHashMap<>();
        AbstractUser dealer = getDealer();
        List<AbstractUser> players = getPlayers();
        List<BigDecimal> profits = getProfits(dealer, players);
        result.put(dealer.getName(), BigDecimal.valueOf(getDealerProfit(profits)));

        players.forEach(player ->
                result.put(player.getName(), profits.remove(FIRST_INDEX))
        );
        return result;
    }

    private int getDealerProfit(List<BigDecimal> profits) {
        return profits.stream()
                .mapToInt(profit -> -profit.intValue())
                .sum();
    }

    private AbstractUser findPlayer(AbstractUser player) {
        return getPlayers().stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
    }

    private List<BigDecimal> getProfits(AbstractUser dealer, List<AbstractUser> players) {
        return players.stream()
                .map(player -> player.profit(dealer.getState(), player.getBettingMoney()))
                .collect(Collectors.toList());
    }

    private AbstractUser getDealer() {
        return users.getDealer();
    }
}
