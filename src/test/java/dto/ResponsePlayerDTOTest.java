package dto;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuitSymbol;
import domain.player.Dealer;
import domain.player.Player;
import domain.player.Players;
import domain.player.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResponsePlayerDTOTest {
    @DisplayName("create 테스트")
    @Test
    void createTest() {
        Player player = new User("subway", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        ResponsePlayerDTO responsePlayerDTO = ResponsePlayerDTO.create(player);

        Assertions.assertThat(responsePlayerDTO.getName()).isEqualTo("subway");
        Assertions.assertThat(responsePlayerDTO.getCardNumbers()).containsSequence("5클로버", "10클로버");
        Assertions.assertThat(responsePlayerDTO.getScore()).isEqualTo("15");
    }

    @DisplayName("Players 로 ResponsePlayerDTO List 생성 테스트")
    @Test
    void createResponsePlayerDTOsTest() {
        Dealer dealer = new Dealer(Arrays.asList(Card.of(CardNumber.ACE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        User subway = new User("subway", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.CLUB),
                Card.of(CardNumber.JACK, CardSuitSymbol.CLUB)));
        User lavine = new User("lavine", Arrays.asList(Card.of(CardNumber.FIVE, CardSuitSymbol.HEART),
                Card.of(CardNumber.SIX, CardSuitSymbol.DIAMOND)));
        List<Player> playerList = new ArrayList<>(Arrays.asList(dealer, subway, lavine));
        Players players = new Players(playerList);
        List<ResponsePlayerDTO> responsePlayerDTOS = ResponsePlayerDTO.createResponsePlayerDTOs(players);

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
