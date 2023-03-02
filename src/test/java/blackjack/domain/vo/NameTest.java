package blackjack.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class NameTest {
    @Test
    @DisplayName("이름 생성 테스트")
    void constructorTest(){
        assertThatNoException().isThrownBy(()->new Name("test"));
    }

    @Test
    @DisplayName("이름이 들어갔는지 확인하는 테스트")
    void insertNameTest(){
        String nameString = "test";
        Name name = new Name(nameString);

        assertThat(name.get()).isEqualTo(nameString);
    }
}
