package blackjack.domain.user;

import blackjack.domain.state.State;
import blackjack.domain.state.StateFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static blackjack.domain.state.CardFactory.SPADE_JACK;
import static blackjack.domain.state.CardFactory.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsersTest {

    private static final State DUMMY_STATE = StateFactory.draw(SPADE_JACK, SPADE_TEN);
    private static final List<AbstractUser> DUMMY_EIGHT_PLAYERS = new ArrayList<>(Arrays.asList(
            new Player(DUMMY_STATE, "pobi"), new Player(DUMMY_STATE, "sobi"), new Player(DUMMY_STATE, "aobi"),
            new Player(DUMMY_STATE, "bobi"), new Player(DUMMY_STATE, "xobi"), new Player(DUMMY_STATE, "dobi"),
            new Player(DUMMY_STATE, "eobi"), new Player(DUMMY_STATE, "fobi")));

    @DisplayName("보장된 인원수 (인원수는 딜러 포함 최소 2명, 최대 8명이다)면 객체 정상 생성된다.")
    @Test
    void userSizeTest() {
        //given
        assertThatCode(() -> new Users(DUMMY_EIGHT_PLAYERS))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 인원수 (인원수는 딜러 포함 최소 2명, 최대 8명이다)가 아니면 에러가 발생한다.")
    @Test
    void userSizeExceptionTest() {
        //given
        int minUserSize = 2;
        int maxUserSize = 8;
        AbstractUser dealer = new Dealer(DUMMY_STATE);

        assertThatThrownBy(() -> new Users(Collections.singletonList(dealer)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("인원수는 딜러포함 %d명 이상 %d이여하야 합니다. 현재 인원수: %d", minUserSize, maxUserSize, 1));

        DUMMY_EIGHT_PLAYERS.add(dealer);

        assertThatThrownBy(() -> new Users(DUMMY_EIGHT_PLAYERS))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("인원수는 딜러포함 %d명 이상 %d이여하야 합니다. 현재 인원수: %d", minUserSize, maxUserSize, DUMMY_EIGHT_PLAYERS.size()));
    }

    @DisplayName("중복되지 않은 이름이면 객 정상 생성된다.")
    @Test
    void userDuplicateTest() {
        //given
        AbstractUser dealer = new Dealer(DUMMY_STATE);

        //when
        DUMMY_EIGHT_PLAYERS.remove(0);
        DUMMY_EIGHT_PLAYERS.add(dealer);

        assertThatCode(() -> new Users(DUMMY_EIGHT_PLAYERS))
                .doesNotThrowAnyException();
    }

    @DisplayName("중복된 이름이면 에러가 발생한다.")
    @Test
    void userDuplicateExceptionTest() {
        //given
        AbstractUser dealer = new Dealer(DUMMY_STATE);

        //when
        List<AbstractUser> users = Arrays.asList(dealer, new Player(DUMMY_STATE, "pobi"), new Player(DUMMY_STATE, "pobi"));

        assertThatThrownBy(() -> new Users(users))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름을 중복될 수 없습니다."));
    }
}