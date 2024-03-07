package domain;

import java.util.List;
import java.util.Objects;

public class Player {
    private final String name;
    private final Hands hands;

    public Player(final String name, final Hands hands) {
        validate(name);
        this.name = name;
        this.hands = hands;
    }

    public void add(final Card card) {
        hands.add(card);
    }

    public Result isWin(final Dealer dealer) {
        return this.hands.calculateResult(dealer.getHands());
    }

    //TODO 메서드 명 변경

    private void validate(final String name) {
        validateNull(name);
        validateBlank(name);
    }

    private void validateNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 참여자 이름입니다.");
        }
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 참여자 이름에 공백을 입력할 수 없습니다.");
        }
    }

    //TODO: 메서드 handsSize 수정
    public int getPacketSize() {
        return hands.size();
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return hands.getCards()
                .stream()
                .map(Card::toString)
                .toList();
    }

    public boolean isBust() {
        return hands.isBust();
    }

    public int getTotalSum() {
        return hands.sum();
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Player player)) {
            return true;
        }
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
