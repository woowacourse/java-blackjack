package blackjack.view.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamesDTOTest {

//    @DisplayName("Names 에서 Player 리스트 만들기")
//    @Test
//    void toPlayers() {
//        //given
//        String inputNames = "allen,bebop";
//        List<Player> result = Arrays.asList(
//                new Gambler(CardBundle.emptyBundle(), "allen"),
//                new Gambler(CardBundle.emptyBundle(), "bebop")
//        );
//
//        //when
//        NamesDTO namesDTO = new NamesDTO(inputNames);
//        List<Player> players = namesDTO.toPlayers();
//
//        //then
//        assertThat(players).isEqualTo(result);
//    }

    @DisplayName("Names 에 Null 혹은 비어있는 값 입력시 exception 발생")
    @ParameterizedTest
    @NullAndEmptySource
    void toPlayersException(String inputNames) {
        assertThatThrownBy(() -> new NamesDTO(inputNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력에 , 만 입력했을때 exception 발생")
    @Test
    void nameSizeEmptyException() {
        String input = ",";

        assertThatThrownBy(() -> new NamesDTO(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력한 이름이 없습니다.");
    }
}