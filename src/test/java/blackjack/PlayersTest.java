package blackjack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @Test
    void create(){
        assertThatCode( () -> new Players("boni,gram,kilo")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - , 구분자 아닌경우")
    void create1(){
        assertThatThrownBy(() -> new Players("boni. gram, kilo")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create3(){
        assertThatCode( () -> new Players("Boni,Gram,Kilo")).doesNotThrowAnyException();
    }

    @Test
    void create4(){
        assertThatCode( () -> new Players("Boni, Gram,Kilo")).doesNotThrowAnyException();
    }

    @Test
    void create6(){
        assertThatThrownBy(() -> new Players("boni1,gram2,kilo3")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void create7(){
        assertThatThrownBy(() -> new Players("b oni,gr am,ki lo")).isInstanceOf(IllegalArgumentException.class);
    }
}
