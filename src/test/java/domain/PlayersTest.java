package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.CardDeckMaker;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    private CardDistributor distributor;

    @BeforeEach
    void setUp() {
        distributor = new CardDistributor(CardDeckMaker.generate());
    }


    @Test
    @DisplayName("참여자 수는 최대 9명이다.")
    void createPlayersCountFail() {
        List<String> playerNames = List.of("a", "b", "c", "d", "f", "q", "w", "e", "r", "z");

        assertThatThrownBy(() -> Players.from(playerNames, distributor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자는 최대 9명까지 가능합니다.");
    }

    @Test
    @DisplayName("참여자 이름은 중복을 허용하지 않는다.")
    void createPlayersDuplicateFail() {
        List<String> playerNames = List.of("a", "a", "a");

        assertThatThrownBy(() -> Players.from(playerNames, distributor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여자 이름은 중복될 수 없습니다.");
    }

}
