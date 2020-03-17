package dto;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.User;
import domain.player.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponseUserDTOTest {
    @DisplayName("create 테스트")
    @Test
    void createTest() {
        User user = new Player("subway", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        ResponsePlayerDTO responsePlayerDTO = ResponsePlayerDTO.create(user);

        Assertions.assertThat(responsePlayerDTO.getName()).isEqualTo("subway");
        Assertions.assertThat(responsePlayerDTO.getCardNumbers()).containsSequence("5클로버", "10클로버");
        Assertions.assertThat(responsePlayerDTO.getScore()).isEqualTo("15");
    }

    @DisplayName("Users 로 ResponsePlayerDTO List 생성 테스트")
    @Test
    void createResponsePlayerDTOsTest() {
        Dealer dealer = new Dealer(Arrays.asList(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        Player subway = new Player("subway", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        Player lavine = new Player("lavine", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.HEART),
                Card.of(CardNumber.SIX, CardSuitSymbol.DIAMOND)));
        List<User> userList = new ArrayList<>(Arrays.asList(dealer, subway, lavine));
        Users users = new Users(userList);
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(users);

        Assertions.assertThat(responsePlayerDTOS.size()).isEqualTo(3);

        Assertions.assertThat(responsePlayerDTOS.get(0).getName()).isEqualTo("딜러");
        Assertions.assertThat(responsePlayerDTOS.get(0).getCardNumbers()).containsSequence("1클로버", "10클로버");
        Assertions.assertThat(responsePlayerDTOS.get(0).getScore()).isEqualTo("21");

        Assertions.assertThat(responsePlayerDTOS.get(1).getName()).isEqualTo("subway");
        Assertions.assertThat(responsePlayerDTOS.get(1).getCardNumbers()).containsSequence("5클로버", "10클로버");
        Assertions.assertThat(responsePlayerDTOS.get(1).getScore()).isEqualTo("15");

        Assertions.assertThat(responsePlayerDTOS.get(2).getName()).isEqualTo("lavine");
        Assertions.assertThat(responsePlayerDTOS.get(2).getCardNumbers()).containsSequence("5하트", "6다이아몬드");
        Assertions.assertThat(responsePlayerDTOS.get(2).getScore()).isEqualTo("11");
    }
}
