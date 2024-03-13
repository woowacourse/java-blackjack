package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.card.TrumpCard;
import blackjack.domain.stategy.NoShuffleStrategy;
import blackjack.strategy.ShuffleStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static blackjack.fixture.PlayerFixture.playerChoco;
import static blackjack.fixture.PlayerFixture.playerClover;
import static blackjack.fixture.TrumpCardFixture.threeSpadeTrumpCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("플레이어")
public class PlayerTest {

    private final ShuffleStrategy shuffleStrategy = new NoShuffleStrategy();
    private Deck deck;
    private Dealer dealer;
    private Player choco;
    private Player clover;

    @BeforeEach
    void setUp() {
        deck = new Deck(shuffleStrategy);
        dealer = new Dealer(deck);
        choco = playerChoco(dealer);
        clover = playerClover(dealer);
    }

    @DisplayName("사용자의 이름이 형식에 맞지 않으면 예외가 발생한다.")
    @Test
    void validateName() {
        //given
        String name = "noValidName123";

        //when & then
        assertThatThrownBy(() -> new Player(name, dealer, "1000"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("배팅 금액이 형식에 맞지 않으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "1000a"})
    void validateBatting(String batting) {
        //when & then
        assertThatThrownBy(() -> new Player("name", dealer, batting))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("버스트 되지 않은 플레이어는 카드를 한장 더 뽑을 수 있다.")
    @Test
    void canReceiveCard() {
        // when
        choco.draw(dealer);

        //then
        assertThat(choco.canReceiveCard()).isTrue();
    }

    @DisplayName("버스트 된 플레이어는 카드를 한장 더 뽑을 수 없다.")
    @Test
    void cantReceiveCard() {
        // when
        for (int i = 0; i < 6; i++) {
            clover.draw(dealer);
        }

        //then
        assertThat(clover.canReceiveCard()).isFalse();
    }

    @DisplayName("플레이어는 한 장을 뽑아서 손패에 넣는다.")
    @Test
    void draw() {
        //given
        TrumpCard trumpCard = threeSpadeTrumpCard();

        //when
        choco.draw(dealer);

        //then
        assertThat(choco.getHandCards()).contains(trumpCard);
    }

    @DisplayName("플레이어는 배팅 금액과 결과를 토대로 수익률을 계산한다.")
    @Test
    void calculateProfit() {
        //given & when & then
        assertAll(
                () -> assertThat(clover.calculateProfit(GameResult.WIN)).isEqualTo(0),
                () -> assertThat(choco.calculateProfit(GameResult.LOSE)).isEqualTo(-2000)
        );
    }
}
