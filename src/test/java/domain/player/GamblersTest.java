//package domain.player;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.util.List;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class GamblersTest {
//
//    @Test
//    @DisplayName("이름 리스트를 통해 player 객체들이 제대로 생성되는지 테스트")
//    void 이름_정상_저장_테스트() {
//        // given
//        List<String> names = List.of("pobi", "coco");
//
//        // when
//        Gamblers gamblers = new Gamblers(names);
//
//        // then
//        boolean allContainName = names.stream()
//                .allMatch(name -> gamblers.containGambler(name));
//
//        Assertions.assertThat(allContainName).isEqualTo(true);
//    }
//}
