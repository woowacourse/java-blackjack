package domain.dto;

import domain.Card;
import domain.Deck;
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
        Assertions.assertThatCode(() -> GamerDto.from(new Player(new Name("test"))))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("이름을 반환할 수 있다.")
    void getName() {
        GamerDto gamerDto = GamerDto.from(new Player(new Name("test")));
        Assertions.assertThat(gamerDto.getName()).isEqualTo("test");
    }


    @Test
    @DisplayName("카드들을 반환할 수 있다.")
    void getCards() {
        Player player = new Player(new Name("test"));
        Card card = new Card(CardType.SPADE, CardNumber.ACE);
        Deck deck = new Deck(card);
        player.hit(deck);
        GamerDto gamerDto = GamerDto.from(player);
        Assertions.assertThat(gamerDto.getCards())
                .isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("카드 점수 합계를 반환한다.")
    void getTotalScore() {
        Player player = new Player(new Name("test"));
        Deck deck = new Deck(
                new Card(CardType.SPADE, CardNumber.ACE),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        player.hit(deck);
        player.hit(deck);
        GamerDto gamerDto = GamerDto.from(player);
        Assertions.assertThat(gamerDto.getTotalScore())
                .isEqualTo(21);
    }
}
