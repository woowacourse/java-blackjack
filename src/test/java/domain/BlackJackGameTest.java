package domain;

import java.util.List;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    @Test
    @DisplayName("GameStatusDto를 반환한다.")
    void getGameStatusDto() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        Assertions.assertThat(blackJackGame.getGameStatusDto()).isNotNull();
    }

    @Test
    @DisplayName("모든 Gamer들에게 카드를 2장씩 나눠준다")
    void initialDealing() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        blackJackGame.initialDealing();
        Assertions.assertThat(blackJackGame.getGameStatusDto()
                        .getGamerDtoFromName("test")
                        .getCards().size())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("이름이 들어오면 덱에서 해당 Gamer가 카드를 한 장 뽑는다.")
    void drawCardFromName() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        blackJackGame.initialDealing();
        blackJackGame.drawCardFromName("test");
        Assertions.assertThat(blackJackGame.getGameStatusDto()
                        .getGamerDtoFromName("test")
                        .getCards().size())
                .isEqualTo(3);
    }

    @Test
    @DisplayName("Gamer 이름이 존재하지 않을 경우 예외가 발생한다.")
    void drawCardFromNameException() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        blackJackGame.initialDealing();
        blackJackGame.drawCardFromName("test");
        Assertions.assertThatThrownBy(() -> blackJackGame.drawCardFromName("wrong"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust가 아니면 false를 반환한다.")
    void isBustFromNameFalse() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        Assertions.assertThat(blackJackGame.isBustFromName("test"))
                .isFalse();
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust이면 true를 반환한다.")
    void isBustFromNameTrue() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Gamer(new Name("test"))
        ));
        IntStream.range(0, 9)
                .forEach(ignored -> blackJackGame.drawCardFromName("test"));
        Assertions.assertThat(blackJackGame.isBustFromName("test"))
                .isTrue();
    }
}
