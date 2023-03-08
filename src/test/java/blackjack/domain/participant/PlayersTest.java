package blackjack.domain.participant;

import static blackjack.domain.participant.Participant.INIT_CARD_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PlayersTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Players players;

    @BeforeEach
    void setUp() {
        players = new Players(List.of("1", "2", "3"));
        player1 = players.getPlayers().get(0);
        player2 = players.getPlayers().get(1);
        player3 = players.getPlayers().get(2);
    }

    @Test
    @DisplayName("플레이어는 게임 시작 시 두 장의 카드를 받는다.")
    void receiveSettingCards() {
        List<Card> settingCards = List.of(
                new Card(Number.THREE, Suit.DIAMOND),
                new Card(Number.J, Suit.DIAMOND),
                new Card(Number.Q, Suit.DIAMOND),
                new Card(Number.K, Suit.DIAMOND),
                new Card(Number.ACE, Suit.HEART),
                new Card(Number.J, Suit.DIAMOND)
        );

        players.receiveSettingCards(settingCards);

        assertAll(
                () -> assertThat(player1.getCards().size()).isEqualTo(2),
                () -> assertThat(player2.getCards().size()).isEqualTo(2),
                () -> assertThat(player3.getCards().size()).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("SettingCards가 인원수의 " + INIT_CARD_COUNT + "배가 아닌 경우 예외가 발생한다.")
    void receiveSettingCards_Exception() {
        List<Card> cards = List.of(
                new Card(Number.TWO, Suit.CLOVER),
                new Card(Number.J, Suit.SPADE),
                new Card(Number.ACE, Suit.HEART)
        );

        assertThatIllegalArgumentException().isThrownBy(
                () -> players.receiveSettingCards(cards)
        ).withMessage("[ERROR] 초기 세팅 카드의 개수는 인원수의 2배여야 합니다. 입력값:" + cards);
    }
}
