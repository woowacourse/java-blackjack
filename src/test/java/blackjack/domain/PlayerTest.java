package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 이름을_확인한다() {
        final Player player = new Player("dazzle");

        assertThat(player.getName()).isEqualTo("dazzle");
    }
}

class Player {

    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    public String getName() {
        return name.getValue();
    }
}
