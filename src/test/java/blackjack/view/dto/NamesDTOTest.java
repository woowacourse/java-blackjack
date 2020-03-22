package blackjack.view.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName(", 기준으로 쪼갠 이름 리스트 가져오기")
    @Test
    void getNames() {
        //given
        String nameInput = "bebop,allen";
        NamesDTO namesDTO = new NamesDTO(nameInput);

        //when
        List<String> names = namesDTO.getNames();

        //then
        assertThat(names).containsExactly("bebop", "allen");
    }
}