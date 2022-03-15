package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Players {

    private static final String CANNOT_FIND_DEALER_MESSAGE = "딜러를 찾을 수 없습니다.";
    private static final int DEALER_SIZE = 1;

    private final List<Player> blackjackPlayers;
    private Turn turn;

    public Players(List<String> playerNames) {
        this.blackjackPlayers = new ArrayList<>();
        blackjackPlayers.add(new Dealer());
        for (String playerName : playerNames) {
            blackjackPlayers.add(new Guest(playerName));
        }
    }

    public void startWithTwoCards(Cards cards) {
        for (Player blackjackPlayer : blackjackPlayers) {
            blackjackPlayer.addCard(cards.assignCard());
            blackjackPlayer.addCard(cards.assignCard());
        }
    }

    public List<String> getNames() {
        return blackjackPlayers.stream().map(Player::getName).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return blackjackPlayers;
    }

    public List<Player> getGuests() {
        return blackjackPlayers.stream()
                .filter(player -> !player.isDealer())
                .collect(Collectors.toList());
    }

    public Player getDealer() {
        return blackjackPlayers.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(CANNOT_FIND_DEALER_MESSAGE));
    }

    public boolean hasNextGuest() {
        if (turn == null) {
            turn = new Turn(blackjackPlayers.size() - DEALER_SIZE);
        }
        return turn.isEndOfTurn();
    }

    public void turnNextGuest() {
        turn.next();
    }

    public Player getTurnPlayer() {
        return blackjackPlayers.get(turn.getNow() + DEALER_SIZE);
    }
}
