package factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerFactoryTest {
    @Test
    @DisplayName("PlayerFactory 기능 확인")
    void create() {
        assertThat(PlayerFactory.create("PlayerA,PlayerB")).isInstanceOf(List.class);
        assertThat(PlayerFactory.create("PlayerA,PlayerB,PlayerC").size()).isEqualTo(3);
    }
}
