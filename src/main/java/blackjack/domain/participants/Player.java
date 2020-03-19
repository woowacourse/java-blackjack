package blackjack.domain.participants;

import java.util.Objects;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.exceptions.InvalidPlayerException;

public class Player implements Participant {
    private final Hand hand;
    private final String name;
    private Result result;

    public Player(final String name) {
        validate(name);
        this.name = name.trim();
        this.hand = new Hand();
    }

    private void validate(final String name) {
        if (isNullOrEmpty(name)) {
            throw new InvalidPlayerException("빈 칸 또는 null 값이 들어올 수 없습니다.");
        }
    }

    private boolean isNullOrEmpty(final String name) {
        return Objects.isNull(name) || name.trim().isEmpty();
    }

    @Override
    public int score() {
        return hand.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        draw(deck.pop());
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public String handStatus() {
        return hand.toString();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int countHand() {
        return hand.size();
    }

    public Result getResult() {
        return result;
    }

    @Override
    public void set(final Result result) {
        this.result = result;
    }

    @Override
    public void draw(final Card card) {
        hand.add(card);
    }
}
