package blackjack.domain.participant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 8;
    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "[ERROR] 플레이어 수는 1~8명 사이여야 합니다.";

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
        dealer.initCards(List.of(deck.pickCard(), deck.pickCard()));
        for (Player player : players) {
            List<Card> cards = List.of(deck.pickCard(), deck.pickCard());
            player.initCards(cards);
        }
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public boolean isDealerMustHit() {
        return dealer.checkMustHit();
    }

    public void dealToDealer(Card card) {
        dealer.addCard(card);
    }

    public boolean isAllPlayerTurnEnd() {
        try {
            getCurrentPlayer();
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return players.stream()
            .filter(Player::canHit)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("플레이어 턴이 모두 종료되었습니다."));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
