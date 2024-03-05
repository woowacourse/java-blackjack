import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    @DisplayName("이름을 가진다.")
    void hasName() {
        Name name = new Name("poby");
        User user = new User(name);

        assertThat(user.getName()).isEqualTo(name);
    }
}
