package blackjack.domain;

import java.util.Objects;

import blackjack.exceptions.InvalidPlayerException;

public class Player implements Participant {
    private final Hand hand;
    private final String name;
    private Result result;

    public Player(final String name) {
        validate(name);
        this.name = name;
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
        draw(deck);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isBusted() {
        return score() > 21;
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
    public String gameResult() {
        return result.getValue();
    }

    @Override
    public void draw(final Deck deck) {
        hand.add(deck.pop());
    }

    // 테스트용
    void draw(final Card card) {
        hand.add(card);
    }
}
