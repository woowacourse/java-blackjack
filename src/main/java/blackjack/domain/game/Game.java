package blackjack.domain.game;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import blackjack.domain.DrawCount;
import blackjack.domain.Name;

public final class Game {

    private static final String HIT_REQUEST = "y";

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public Game(CardDeck cardDeck, Map<Name, Betting> namesAndBettings) {
        this.cardDeck = cardDeck;
        this.dealer = new Dealer();
        this.players = namesAndBettings.entrySet().stream()
            .map(entry -> new Player(entry.getKey(), entry.getValue()))
            .collect(Collectors.toUnmodifiableList());
        init();
    }

    private void init() {
        dealer.init(cardDeck.drawCard(), cardDeck.drawCard());
        players.forEach(player -> player.init(cardDeck.drawCard(), cardDeck.drawCard()));
    }

    public Optional<Player> RunningPlayer() {
        return players.stream()
            .filter(Player::isDrawable)
            .findFirst();
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
        Map<Name, Long> revenues = new LinkedHashMap<>();
        revenues.put(dealer.getName(), dealer.getRevenue(players));
        for (Player player : players) {
            revenues.put(player.getName(), player.getRevenue(dealer));
        }
        return revenues;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<Participant> getParticipants() {
        List<Participant> participants = new ArrayList<>(List.of(dealer));
        participants.addAll(players);
        return participants;
    }
}