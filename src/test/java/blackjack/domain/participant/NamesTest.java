package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static blackjack.domain.participant.Names.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamesTest {

    @Test
    @DisplayName("이름들을 담은 Names 생성된다.")
    void createNamesTest() {
        /*give*/
        List<String> inputNames = Arrays.asList(
            "pobi",
            "json",
            "min"
        );

        /*when*/
        Names names = Names.of(inputNames);

        /*then*/
        assertThat(names).isInstanceOf(Names.class);
    }

    @Test
    @DisplayName("이름들에 중복이 있으면 예외 발생한다.")
    void inputDuplicateNameTest() {
        assertThatThrownBy(() -> {
            /*give*/
            List<String> inputNames = Arrays.asList(
                    "pobi",
                    "pobi",
                    "min"
            );

            /*when*/
            Names names = Names.of(inputNames);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("중복된 이름은 사용할 수 없습니다.");
    }

    @Test
    @DisplayName("참가자 이름들이 7개를 초과하면 예외가 발생한다.")
    void inputOverSevenNamesTest() {
        assertThatThrownBy(() -> {
            /*give*/
            List<String> inputNames = Arrays.asList(
                    "pobi",
                    "gobi",
                    "dobi",
                    "eobi",
                    "robi",
                    "win",
                    "lobi",
                    "kobi",
                    "nin"
            );

            /*when*/
            Names names = Names.of(inputNames);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("최대 참여 플레이어는 " + MAX_PLAYER + "명입니다.");
    }

    @Test
    @DisplayName("Names의 컬렉션 사이즈 반환된다.")
    void namesSizeTest() {
        /*give*/
        List<String> inputNames = Arrays.asList(
                "pobi",
                "gobi",
                "dobi",
                "eobi",
                "robi",
                "win",
                "lobi",
                "kobi"
        );

        /*when*/
        Names names = Names.of(inputNames);

        /*then*/
        assertThat(names.size()).isEqualTo(7);
    }

    @Test
    @DisplayName("Names 내부의 값 가져올 수 있다.")
    void getNameTest() {
        /*give*/
        List<String> inputNames = Arrays.asList(
                "pobi",
                "gobi",
                "dobi",
                "eobi",
                "robi",
                "win",
                "lobi"
        );

        /*when*/
        Names names = Names.of(inputNames);
        Name name = names.get(1);

        /*then*/
        assertThat(name).isEqualTo(new Name("gobi"));
    }
}