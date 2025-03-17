package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.CardRank;
import blackjack.card.CardSymbol;
import blackjack.state.State;
import blackjack.state.started.finished.Blackjack;
import fixture.BettingFixture;
import fixture.HandFixture;
import fixture.NicknameFixture;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GamerTest {

    public static Stream<Arguments> provideInstancesOfGamer() {
        return Stream.of(
                Arguments.of(
                        new Player(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)),
                                NicknameFixture.createNickname("ad"),
                                BettingFixture.createBetting(1000)),
                        new Dealer(HandFixture.createHand(Card.of(CardSymbol.SPADE, CardRank.ACE),
                                Card.of(CardSymbol.SPADE, CardRank.JACK)))
                ));
    }

    @DisplayName("Gamer는 생성시에 블랙잭이라면 블랙잭 상태를가진다")
    @ParameterizedTest
    @MethodSource("provideInstancesOfGamer")
    void isBlackjack(Gamer gamer) {
        // when
        State actual = gamer.state;

        // then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }
}
