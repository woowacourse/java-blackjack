package domain;

import java.util.List;
import java.util.Objects;

public class Player implements Gambler {
    private final PlayerName playerName;
    private final Cards cards;

    public Player(PlayerName playerName, Cards cards) {
        this.playerName = playerName;
        this.cards = cards;
        initialPick();
    }

    @Override
    public void initialPick() {
        if (cards.getCards().isEmpty()) {
            pickCard();
            pickCard();
        }
    }

    @Override
    public void pickCard() {
        cards.addCard(Deck.pickCard());
    }

    @Override
    public String getName() {
        return playerName.getName();
    }

    @Override
    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public boolean isBustedGambler() {
        return this.cards.isBusted(cards.calculateScore());
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerName, player.playerName) && Objects.equals(cards, player.cards);
    }

    //todo: hashcode 어디서 쓰이는지 공부하기
    //todo: 동일성, 동등성
    @Override
    public int hashCode() {
        return Objects.hash(playerName, cards);
    }
}
