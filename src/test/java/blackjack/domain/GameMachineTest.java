package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeckGenerator;
import blackjack.domain.card.ShuffleDeckGenerator;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameMachineTest {

    private final CardDeckGenerator cardDeckGenerator = new ShuffleDeckGenerator();
    private final GameMachine gameMachine = new GameMachine(cardDeckGenerator.createCardDeck());

    @Test
    @DisplayName("유저들을 생성을 확인한다.")
    void createUsers() {
        final List<String> users = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        final int expected = 7;

        final int actual = gameMachine.createUsers(users).getUsers().size();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저 생성 인원이 7명이 넘을 시 에러를 확인한다.")
    void createMoreThanEightUsers() {
        final List<String> users = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");
        assertThatThrownBy(() ->
                gameMachine.createUsers(users))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임에 참여할 수 있는 유저는 최대 7명입니다.");
    }
}
