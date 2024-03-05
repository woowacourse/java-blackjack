package domain;

import java.util.Objects;

public class Player {
    private final String name;
    private final Packet packet;

    public Player(final String name, final Packet packet) {
        validate(name);
        this.name = name;
        this.packet = packet;
    }

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

    public int getPacketSize() {
        return 2;
    }
}
