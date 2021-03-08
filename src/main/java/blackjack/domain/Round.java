package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.AbstractUser;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
    public static final int GAME_OVER_SCORE = 21;

    private final Deck deck;
    private final AbstractUser dealer;
    private final List<AbstractUser> players;

    public Round(final Deck deck, final AbstractUser dealer, final List<AbstractUser> players) {
        this.deck = deck;
        this.dealer = dealer;
        this.players = new ArrayList<>(players);
    }

    public void initialize() {
        dealer.addFirstCards(deck.makeTwoCards());
        players.forEach(player -> player.addFirstCards(deck.makeTwoCards()));
    }

    public AbstractUser getDealer() {
        return dealer;
    }

    public String getDealerName() {
        return dealer.getName();
    }

    public List<AbstractUser> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Queue<Outcome>> findResults() {
        Map<String, Queue<Outcome>> results = new LinkedHashMap<>();
        Queue<Outcome> gameOutComes = makeRoundOutComes();
        results.put(dealer.getName(), new ArrayDeque<>(gameOutComes));

        players.forEach(player -> results.put(player.getName(),
                new ArrayDeque<>(Collections.singletonList(Outcome.getPlayerOutcome(gameOutComes.poll())))));

        return results;
    }

    public void addDealerCard() {
        dealer.addCard(deck.makeOneCard());
    }

    public void addPlayerCard(AbstractUser player) {
        AbstractUser findPlayer = players.stream()
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

    public boolean isDealerGameOver() {
        return dealer.isGameOver(GAME_OVER_SCORE);
    }
}
