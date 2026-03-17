package domain.player;

import static org.junit.jupiter.api.Assertions.assertThrows;

import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamblersTest {

    @Test
    @DisplayName("이름 리스트를 통해 player 객체들이 제대로 생성되는지 테스트")
    void 이름_정상_저장_테스트() {
        // given
        List<String> names = List.of("pobi", "coco");

        // when
        List<Gambler> list = names.stream()
                .map(nameInput -> {
                    Name name = new Name(nameInput);
                    Hand hand = new Hand();
                    Money money = new Money("10000");

                    return new Gambler(name, hand, money);
                })
                .toList();
        Gamblers gamblers = new Gamblers(list);

        // then
        boolean allContainName = names.stream()
                .allMatch(gamblers::containGambler);

        Assertions.assertThat(allContainName).isEqualTo(true);
    }

    @Test
    @DisplayName("이름 중복 테스트")
    void 이름_중복_테스트() {
        // given
        List<String> names = List.of("pobi", "coco", "coco", "kaiya");

        // then
        assertThrows(IllegalArgumentException.class, () -> {
            List<Gambler> list = names.stream()
                    .map(nameInput -> {
                        Name name = new Name(nameInput);
                        Hand hand = new Hand();
                        Money money = new Money("10000");

                        return new Gambler(name, hand, money);
                    })
                    .toList();
            Gamblers gamblers = new Gamblers(list);
        });
    }
}
