package domain.game;

import static domain.FixtureCard.ACE_HEARTS;
import static domain.FixtureCard.TEN_HEARTS;
import static domain.FixtureCard.TWO_HEARTS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.card.Card;
import domain.player.Dealer;
import domain.player.Player;
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
                () -> assertEquals(Result.DEALER_WIN, Result.of(new Dealer(bustCards), new Player(bustCards))),
                () -> assertEquals(Result.DEALER_WIN, Result.of(new Dealer(loserCards), new Player(bustCards))),
                () -> assertEquals(Result.DEALER_WIN, Result.of(new Dealer(blackJackCards), new Player(loserCards))),
                () -> assertEquals(Result.PUSH, Result.of(new Dealer(blackJackCards), new Player(blackJackCards))),
                () -> assertEquals(Result.PLAYER_WIN, Result.of(new Dealer(bustCards), new Player(loserCards))),
                () -> assertEquals(Result.PLAYER_BLACK_JACK,
                        Result.of(new Dealer(loserCards), new Player(blackJackCards))),
                () -> assertEquals(Result.PUSH, Result.of(new Dealer(loserCards), new Player(loserCards)))
        );
    }
}
