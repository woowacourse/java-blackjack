package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.state.DealerTurnOver;
import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import blackjack.domain.user.AbstractUser;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Users;

import java.util.*;
import java.util.stream.Collectors;

public class Round {
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

    public AbstractUser getDealer() {
        return users.getDealer();
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public List<AbstractUser> getPlayers() {
        return Collections.unmodifiableList(users.getPlayers());
    }

    public boolean isDealerCanDraw() {
        return getDealer().canDraw();
    }

    public void addDealerCard() {
        AbstractUser findDealer = getDealer();
        State state = findDealer.getState().draw(deck.makeOneCard());
        if (!state.isFinish() && !findDealer.canDraw()) {
            findDealer.changeState(new DealerTurnOver(state.cards()));
        }
    }

    public void addPlayerCard(AbstractUser player) {
        AbstractUser findPlayer = findPlayer(player);
        State state = findPlayer.getState().draw(deck.makeOneCard());
        findPlayer.changeState(state);
    }

    public void makePlayerStay(AbstractUser player) {
        AbstractUser findPlayer = findPlayer(player);
        State state = findPlayer.getState().stay();
        findPlayer.changeState(state);
    }

    private AbstractUser findPlayer(AbstractUser player) {
        return getPlayers().stream()
                .filter(p -> p.equals(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 유저입니다"));
    }

    private static State drawTwoCard(Deck deck) {
        return StateFactory.draw(deck.makeOneCard(), deck.makeOneCard());
    }
}
