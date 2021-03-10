package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Users;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
    public static final int GAME_OVER_SCORE = 21;

    private final Deck deck;
    private final Users users;

    public Round(Deck deck, Users<?> users) {
        this.deck = deck;
        this.users = users;
    }

    public void initialize() {
        users.addFirstCards(deck);
    }

    public AbstractUser getDealer() {
        return users.getDealer();
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public List<AbstractUser> getPlayers() {
        return Collections.unmodifiableList(users.getPlayers());
    }

    public Map<String, Queue<Outcome>> findResults() {
        Map<String, Queue<Outcome>> results = new LinkedHashMap<>();
        Queue<Outcome> gameOutComes = makeRoundOutComes();
        results.put(getDealer().getName(), new ArrayDeque<>(gameOutComes));

        getPlayers().forEach(player -> results.put(player.getName(),
                new ArrayDeque<>(Collections.singletonList(Outcome.getPlayerOutcome(gameOutComes.poll())))));

        return results;
    }

    public void addDealerCard() {
        getDealer().addCard(deck.makeOneCard());
    }

    public void addPlayerCard(AbstractUser player) {
        AbstractUser findPlayer = getPlayers().stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
        findPlayer.addCard(deck.makeOneCard());
    }

    private Queue<Outcome> makeRoundOutComes() {
        return getPlayers().stream()
                .map(player -> Outcome.findOutcome(getDealer().getScore(), player.getScore()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public boolean isDealerGameOver() {
        return getDealer().isGameOver(GAME_OVER_SCORE);
    }
}
