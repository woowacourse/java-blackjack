package blackjack.domain.human;

import static blackjack.domain.Fixtures.ACE;
import static blackjack.domain.Fixtures.TEN;
import static blackjack.domain.Fixtures.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.Fixtures;
import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.human.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    private final Fixtures fx = new Fixtures();

    @Test
    @DisplayName("플레이어 모음 생성 기능 테스트")
    public void createTest() {
        Players players = new Players(List.of(fx.POBI));
        assertThat(players.get().size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어 모음 생성 기능 예외처리 테스트")
    public void createErrorTest() {
        assertThatThrownBy(() -> new Players(List.of(fx.POBI, fx.POBI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어들 이름 반환 기능 테스트")
    public void getPlayerNamesTest() {
        // given
        Players players = new Players(List.of(fx.POBI, fx.JASON));
        // then
        assertThat(players.getNames())
                .contains("pobi", "jason");
    }

    @Test
    @DisplayName("카드 주는 기능 테스트")
    public void giveCardTest() {
        // given
        Players players = new Players(List.of(fx.POBI, fx.JASON));
        CardDeck cardDeck = new CardDeck();

        // when
        players.giveCard(cardDeck);
        players.giveCard(cardDeck);

        // then
        assertAll(
                () -> assertThat(players.get().get(0).getRawCards().size())
                        .isEqualTo(2),
                () -> assertThat(players.get().get(1).getRawCards().size())
                        .isEqualTo(2)
        );
    }

    @Test
    @DisplayName("페이아웃 반환 테스트")
    public void getPayoutsTest() {
        Fixtures fx = new Fixtures();
        // given
        Players players = new Players(List.of(fx.POBI));
        Dealer dealer = new Dealer(List.of(TWO, TWO));

        // when
        fx.POBI.addCard(TEN);
        fx.POBI.addCard(ACE);

        // then
        assertThat(players.getPayouts(dealer))
                .containsEntry(fx.POBI, 15000);

    }
}
