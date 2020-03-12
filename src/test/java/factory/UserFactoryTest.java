package factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFactoryTest {
    @Test
    @DisplayName("UserFactory 기능 확인")
    void create() {
        assertThat(UserFactory.create("UserA,UserB")).isInstanceOf(List.class);
        assertThat(UserFactory.create("UserA,UserB,UserC").size()).isEqualTo(3);
    }
}
