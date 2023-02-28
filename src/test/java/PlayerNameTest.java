import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerNameTest {

    @DisplayName("이름은 null을 허용하지 않는다.")
    @Test
    void createNameFailTestByNull(){
        Assertions.assertThatThrownBy(() -> new PlayerName(null))
                .isInstanceOf(NullPointerException.class);
    }

}
