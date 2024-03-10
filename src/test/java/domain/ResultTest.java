package domain;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("게임의 결과를 반환한다.")
    @Test
    void getGameResult() {
        List<Card> bustCards = List.of(TEN_HEARTS, TEN_HEARTS, TWO_HEARTS);
        List<Card> blackJackCards = List.of(ACE_HEARTS, TEN_HEARTS);
        List<Card> loserCards = List.of(TWO_HEARTS);

        assertAll(
                () -> assertThat(Result.of(new Dealer(bustCards), new Player(bustCards))).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(loserCards), new Player(bustCards))).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(blackJackCards), new Player(loserCards))).isEqualTo(
                        Result.DEALER_WIN),
                () -> assertThat(Result.of(new Dealer(blackJackCards), new Player(blackJackCards))).isEqualTo(
                        Result.PUSH),
                () -> assertThat(Result.of(new Dealer(bustCards), new Player(loserCards))).isEqualTo(Result.PLAYER_WIN),
                () -> assertThat(Result.of(new Dealer(loserCards), new Player(blackJackCards))).isEqualTo(
                        Result.PLAYER_WIN)
        );
    }
}
