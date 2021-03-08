package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
    public static final int GAME_OVER_SCORE = 21;

    private final Deck deck;
    private final Dealer dealer;
    private final List<Player> players;

    public Round(final Deck deck, final Dealer dealer, final List<Player> players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public void initialize() {
        dealer.addFirstCards(deck.makeTwoCards());
        players.forEach(player -> player.addFirstCards(deck.makeTwoCards()));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Queue<Outcome>> findResults() {
        Map<String, Queue<Outcome>> results = new LinkedHashMap<>();
        Queue<Outcome> gameOutComes = makeRoundOutComes();
        results.put(dealer.getName(), new ArrayDeque<>(gameOutComes));

        players.forEach(player -> results.put(player.getName(),
                new ArrayDeque<>(Collections.singletonList(Outcome.getPlayerOutcomes(gameOutComes.poll())))));

        return results;
    }

    public boolean addDealerCard() {
        if (!dealer.isGameOver(GAME_OVER_SCORE)) {
            dealer.addCard(deck.makeOneCard());
            return true;
        }
        return false;
    }

    public void addPlayerCard(Player player) {
        Player findPlayer = players.stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
        findPlayer.addCard(deck.makeOneCard());
    }

    private Queue<Outcome> makeRoundOutComes() {
        return players.stream()
                .map(player -> Outcome.findOutcome(dealer.getScore(), player.getScore()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
