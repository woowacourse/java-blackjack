package blackjack.domain.game;

import static java.util.Collections.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.DrawCount;
import blackjack.domain.Name;
import blackjack.domain.state.Bet;

public final class Game {

    private static final String HIT_REQUEST = "y";

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardDeck cardDeck, Map<Name, Bet> betsByNames) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer();
        this.players = createPlayers(new LinkedHashMap<>(betsByNames));
        init();
    }

    private List<Player> createPlayers(Map<Name, Bet> betsByNames) {
        return betsByNames.entrySet().stream()
            .map(entry -> new Player(entry.getKey(), entry.getValue()))
            .collect(Collectors.toUnmodifiableList());
    }

    private void init() {
        dealer.init(cardDeck.drawCard(), cardDeck.drawCard());
        players.forEach(player -> player.init(cardDeck.drawCard(), cardDeck.drawCard()));
    }

    public void drawPlayerCard(Player player, String hitOrStay) {
        if (hitOrStay.equalsIgnoreCase(HIT_REQUEST)) {
            player.draw(cardDeck.drawCard());
            return;
        }
        player.stay();
    }

    public DrawCount drawDealerCards() {
        int count = 0;
        while (dealer.isDrawable()) {
            dealer.draw(cardDeck.drawCard());
            count++;
        }

        return DrawCount.of(count);
    }

    public Map<Name, Long> getRevenues() {
        Map<Name, Long> revenuesByNames = new LinkedHashMap<>();
        revenuesByNames.put(dealer.getName(), dealer.getRevenue(players));
        for (Player player : players) {
            revenuesByNames.put(player.getName(), player.getRevenue(dealer));
        }
        return revenuesByNames;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players);
        return unmodifiableList(participants);
    }
}