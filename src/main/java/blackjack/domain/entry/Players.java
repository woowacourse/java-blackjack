package blackjack.domain.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final int MAXIMUM_PLAYERS_SIZE = 25;

    private final Dealer dealer;
    private final List<Player> players;

    public Players(Dealer dealer, Map<Name, BettingMoney> bettingPlayers) {
        validateSize(bettingPlayers);
        this.dealer = dealer;
        this.players = toPlayers(bettingPlayers);
    }

    public boolean isFinishedDealer() {
        return !dealer.canHit();
    }

    public void hitDealer(Card card) {
        dealer.addCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void divideCards(Deck deck) {
        players.forEach(player -> player.ready(deck.draw(), deck.draw()));
    }

    public List<Card> hitBy(Name name, Card card) {
        Player player = findBy(name);
        player.draw(card);
        return player.getCards();
    }

    public List<Card> stayBy(Name name) {
        Player player = findBy(name);
        player.stay();
        return player.getCards();
    }

    public boolean isFinishedBy(Name name) {
        return players.stream()
            .filter(player -> player.equalsName(name))
            .anyMatch(Player::isFinished);
    }

    private void validateSize(Map<Name, BettingMoney> players) {
        if (players.size() > MAXIMUM_PLAYERS_SIZE) {
            throw new IllegalArgumentException("최대 참가자 수는 25명입니다.");
        }
    }

    private List<Player> toPlayers(Map<Name, BettingMoney> bettingPlayers) {
        return bettingPlayers.keySet().stream()
            .map(name -> new Player(name, bettingPlayers.get(name)))
            .collect(Collectors.toList());
    }

    private Player findBy(Name name) {
        return players.stream()
            .filter(player -> player.equalsName(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 이름의 플에이어가 존재하지 않습니다."));
    }

    public List<Name> getNames() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

    public List<Participant> getAllPlayers() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public Map<Participant, Double> getAllProfit() {
        Map<Participant, Double> playerProfits = new LinkedHashMap<>();
        playerProfits.put(dealer, sumDealerProfit(dealer));
        for (Player player : players) {
            playerProfits.put(player, player.profit(dealer));
        }
        return playerProfits;
    }

    private double sumDealerProfit(Dealer dealer) {
        return players.stream()
            .mapToDouble(player -> player.profit(dealer))
            .sum() * -1;
    }
}
