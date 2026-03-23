package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {
    public static final int INITIAL_CARD_COUNT = 2;
    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;


    public BlackjackGame() {
        this.players = new ArrayList<>();
        this.dealer = new Dealer(new Name("딜러"));
        this.deck = new Deck();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void registPlayers(List<String> names, List<Integer> betAmounts) {
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            int money = betAmounts.get(i);

            players.add(new Player(new Name(name), new Money(money)));

        }
    }

    public void giveHand() {
        for (Player player : players) {
            giveCards(player, INITIAL_CARD_COUNT);
        }
        giveCards(dealer, INITIAL_CARD_COUNT);
    }

    public void giveCards(Participant participant, int quantity) {
        for (int i = 0; i < quantity; i++) {
            giveCard(participant);
        }
    }

    public void giveCard(Participant participant) {
        Card card = deck.pull();
        participant.add(card);
    }

    public boolean dealerHitsStand() {
        if (dealer.decideHitStand()) {
            giveCard(dealer);
            return true;
        }
        return false;
    }

    public Map<String, Integer> calculatePlayerProits() {
        Map<String, Integer> playerProfits = new HashMap<>();

        for (Player player : players) {
            GameResult result = GameResult.judge(player, dealer);
            int profit = result.calculateProfit(player.getMoneyValue());

            playerProfits.put(player.getName(), profit);
        }

        return playerProfits;
    }

    public Map<String, Integer> calculateDealerProit(Map<String, Integer> playerProfits) {
        int totalPlayerProfit = playerProfits.values().stream()
                        .mapToInt(Integer::intValue)
                                .sum();

        return Map.of(dealer.getName(), totalPlayerProfit * -1);
    }
}
