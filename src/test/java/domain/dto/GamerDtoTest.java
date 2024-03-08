package domain.dto;

import domain.Card;
import domain.Gamer;
import domain.Name;
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
        Assertions.assertThatCode(() -> GamerDto.from(new Gamer(new Name("test"))))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("이름을 반환할 수 있다.")
    void getName() {
        GamerDto gamerDto = GamerDto.from(new Gamer(new Name("test")));
        Assertions.assertThat(gamerDto.getName()).isEqualTo("test");
    }


    @Test
    @DisplayName("카드들을 반환할 수 있다.")
    void getCards() {
        Gamer gamer = new Gamer(new Name("test"));
        Card card = new Card(CardType.SPADE, CardNumber.ACE);
        gamer.takeCard(card);
        GamerDto gamerDto = GamerDto.from(gamer);
        Assertions.assertThat(gamerDto.getCards())
                .isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("카드 점수 합계를 반환한다.")
    void getTotalScore() {
        Gamer gamer = new Gamer(new Name("test"));
        gamer.takeCard(new Card(CardType.SPADE, CardNumber.ACE));
        gamer.takeCard(new Card(CardType.SPADE, CardNumber.TEN));
        GamerDto gamerDto = GamerDto.from(gamer);
        Assertions.assertThat(gamerDto.getTotalScore())
                .isEqualTo(21);
    }
}
