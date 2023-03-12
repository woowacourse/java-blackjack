package blackjack.domain.user;

import blackjack.domain.result.GameResult;
import blackjack.domain.result.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.Deck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Users {

    private static final String NOT_CONTAIN_DEALER = "Users에 Dealer 객체가 없습니다.";
    private static final String NOT_CONTAIN_USER_BY_NAME = "해당 이름의 유저를 찾을 수 없습니다.";

    private final List<User> users;

    public Users(List<String> playerNames, Deck deck) {
        List<User> users = playerNames.stream()
                .map(name -> new Player(name, initCardGroup(deck)))
                .collect(Collectors.toList());
        users.add(new Dealer(initCardGroup(deck)));
        this.users = List.copyOf(users);
    }

    private CardGroup initCardGroup(Deck deck) {
        return new CardGroup(deck.draw(), deck.draw());
    }

    public List<Card> getHandholdingCards(final String name) {
        return getUser(name).getHandholdingCards();
    }

    public List<Card> getInitialHoldingCards(final String name) {
        return getUser(name).getInitialHoldingCards();
    }

    public boolean isDealerOverDrawLimit() {
        return getDealer().isOverDraw();
    }

    private Dealer getDealer() {
        return users.stream()
                .filter(user -> user instanceof Dealer)
                .map(user -> (Dealer) user)
                .findAny()
                .orElseThrow(() -> new IllegalStateException(NOT_CONTAIN_DEALER));
    }

    public List<String> getPlayerNames() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(User::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawDealer(final Deck deck) {
        getDealer().drawCard(deck);
    }

    public Map<String, GameResult> getGameResult() {
        final Dealer dealer = getDealer();
        final Map<String, GameResult> gameResult = new HashMap<>();
        for (final Player player : getPlayers()) {
            final Score playerScore = player.getScore();
            gameResult.put(player.getName(), dealer.judgePlayerGameResult(playerScore));
        }
        return gameResult;
    }

    private List<Player> getPlayers() {
        return users.stream()
                .filter(user -> user instanceof Player)
                .map(user -> (Player) user)
                .collect(Collectors.toUnmodifiableList());
    }

    public void drawCard(final String userName, final Deck deck) {
        getUser(userName).drawCard(deck);
    }

    public Score getScore(final String name) {
        return getUser(name).getScore();
    }

    private User getUser(final String name) {
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NOT_CONTAIN_USER_BY_NAME));
    }
}
