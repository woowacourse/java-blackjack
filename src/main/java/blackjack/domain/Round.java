package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
    public static final int GAME_OVER_SCORE = 21;

    private final Deck deck;
    private final Users users;

    private Round(Deck deck, Users<?> users) {
        this.deck = deck;
        this.users = users;
    }

    public static Round valueOf(Deck deck, List<String> playerNames) {
        List<AbstractUser> users = new ArrayList<>();
        users.add(new Dealer(drawTwoCard(deck)));

        List<AbstractUser> players = playerNames.stream()
                .map(playerName -> new Player(drawTwoCard(deck), playerName))
                .collect(Collectors.toList());
        users.addAll(players);

        return new Round(deck, new Users<>(users));
    }

    private static State drawTwoCard(Deck deck) {
        return StateFactory.draw(deck.makeOneCard(), deck.makeOneCard());
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
        AbstractUser findDealer = getDealer();
        State state = findDealer.getState().draw(deck.makeOneCard());
        findDealer.changeState(state);
    }

    public void addPlayerCard(AbstractUser player) {
        AbstractUser findPlayer = getPlayers().stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
        State state = findPlayer.getState().draw(deck.makeOneCard());
        findPlayer.changeState(state);
    }

    private Queue<Outcome> makeRoundOutComes() {
        return getPlayers().stream()
                .map(player -> Outcome.findOutcome(getDealer().getState().calculateScore(), player.getState().calculateScore()))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    public boolean isDealerCanDraw() {
        return getDealer().canDraw();
    }
}
