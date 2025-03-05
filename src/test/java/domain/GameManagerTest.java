package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.Test;

class GameManagerTest {

    @Test
    void 게임_메니저를_생성한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );

        // when & then
        assertThatCode(() -> GameManager.of(cardDeck, participants))
                .doesNotThrowAnyException();
    }

    @Test
    void 게임을_시작하고_카드를_두_장씩_배부한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        GameManager gameManager = GameManager.of(cardDeck, participants);

        // when
        gameManager.distributeCards();

        // then
        assertThat(cardDeck.getCards()).hasSize(44);
    }

    @Test
    void 참가자들의_이름_목록을_가져온다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        GameManager gameManager = GameManager.of(cardDeck, participants);

        // when
        List<String> names = gameManager.getPlayersName();

        // then
        assertThat(names).hasSize(3).contains("pobi1", "pobi2", "pobi3");
    }

    @Test
    void 이름을_받아_해당_플레이어에게_카드를_준다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Player target = Player.of("pobi1");
        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        GameManager gameManager = GameManager.of(cardDeck, participants);

        // when
        gameManager.passCardTo("pobi1");

        // then
        assertThat(target.getOwnedCards()).hasSize(1);
    }

    @Test
    void 플레이어의_현재_카드_점수를_계산한다() {
        // given
        CardDeck cardDeck = CardDeck.of();
        Player target = Player.of("pobi1");
        target.receive(Card.of(TrumpNumber.NINE, TrumpShape.CLUB));

        Participants participants = Participants.of(
                Dealer.of(), List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        GameManager gameManager = GameManager.of(cardDeck, participants);

        // when
        int score = gameManager.getScoreOf("pobi1");

        // then
        assertThat(score).isEqualTo(9);
    }
}