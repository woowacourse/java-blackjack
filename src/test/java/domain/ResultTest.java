package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ResultTest {

    @DisplayName("게임의 결과를 반환한다.")
    @Test
    void getGameResult() {
        List<Card> bustCards = List.of(TEN_HEART, TEN_HEART, TWO_HEART);
        List<Card> blackJackCards = List.of(ACE_HEART, TEN_HEART);
        List<Card> loserCards = List.of(TWO_HEART);

        assertAll(
                () -> assertThat(Result.of(new Dealer(bustCards), new Player(bustCards))).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(loserCards), new Player(bustCards))).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(blackJackCards), new Player(loserCards))).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(blackJackCards), new Player(blackJackCards))).isEqualTo(Result.PUSH),
                () -> assertThat(Result.of(new Dealer(bustCards), new Player(loserCards))).isEqualTo(Result.PLAYER_WIN),
                () -> assertThat(Result.of(new Dealer(loserCards), new Player(blackJackCards))).isEqualTo(Result.PLAYER_WIN)
        );
    }
}
