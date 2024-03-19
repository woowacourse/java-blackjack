package domain.dto;

import domain.Card;
import domain.Name;
import domain.Player;
import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerDtoTest {
    @Test
    @DisplayName("Gamer를 통해 생성할 수 있다.")
    void create() {
        Assertions.assertThatCode(() -> PlayerDto.from(new Player(new Name("test"))))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("이름을 반환할 수 있다.")
    void getName() {
        PlayerDto playerDto = PlayerDto.from(new Player(new Name("test")));
        Assertions.assertThat(playerDto.getName()).isEqualTo("test");
    }


    @Test
    @DisplayName("카드들을 반환할 수 있다.")
    void getCards() {
        Player player = new Player(new Name("test"));
        Card card = Card.getCard(CardType.SPADE, CardNumber.ACE);
        player.takeCard(card);
        PlayerDto playerDto = PlayerDto.from(player);
        Assertions.assertThat(playerDto.getCards())
                .isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("카드 점수 합계를 반환한다.")
    void getTotalScore() {
        Player player = new Player(new Name("test"));
        player.takeCard(Card.getCard(CardType.SPADE, CardNumber.ACE));
        player.takeCard(Card.getCard(CardType.SPADE, CardNumber.TEN));
        PlayerDto playerDto = PlayerDto.from(player);
        Assertions.assertThat(playerDto.getTotalScore())
                .isEqualTo(21);
    }
}
