package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Deck;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 8;
    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "플레이어 수는 1~8명 사이여야 합니다.";
    public static final String PLAYER_TURN_ACCESS_ERROR_MESSAGE = "플레이어 턴이 모두 종료되어 접근 할 수 없습니다.";

    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    public Participants(List<Player> players) {
        validatePlayerNumber(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayerNumber(List<Player> players) {
        if (players.size() < MIN_COUNT || players.size() > MAX_COUNT) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
    }

    public void dealInitialCards(Deck deck) {
        deck.dealInitialCards(dealer);
        for (Player player : players) {
            deck.dealInitialCards(player);
        }
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public boolean isAllPlayerTurnEnd() {
        try {
            getCurrentPlayer();
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public void dealToPlayer(Deck deck) {
        deck.dealCard(getCurrentPlayer());
    }

    public void stayCurrentPlayer() {
        getCurrentPlayer().stay();
    }

    public boolean isDealerMustHit() {
        return dealer.checkMustHit();
    }

    public void dealToDealer(Deck deck) {
        deck.dealCard(dealer);
    }

    public Player getCurrentPlayer() {
        return players.stream()
            .filter(Player::canHit)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(PLAYER_TURN_ACCESS_ERROR_MESSAGE));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
