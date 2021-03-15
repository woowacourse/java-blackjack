package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void drawInitialCardsToPlayers(Deck deck) {
        this.players.forEach(player -> player.drawInitialCards(deck.popTwo()));
    }

    public List<Cards> showCardsByPlayers() {
        List<Cards> cards = new ArrayList<>();
        this.players.forEach(player -> cards.add(
                new Cards(player.cards().getCards())));
        return cards;
    }

    public boolean remainAnyPlayer() {
        return this.players.stream()
                .anyMatch(Player::isHit);
    }

    public Player currentPlayer() {
        return this.players.stream()
                .filter(Player::isHit)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("남은 플레이어가 없습니다."));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
