package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import java.util.List;
import org.junit.jupiter.api.Test;

class ParticipantsTest {
    @Test
    void 참가자_정보_모델을_생성한다() {
        // given
        Players players = Players.of(
                List.of(Player.of("pobi1"),
                        Player.of("pobi2")
                )
        );
        Dealer dealer = Dealer.of();

        // when & then
        assertThatCode(() -> Participants.of(dealer, players))
                .doesNotThrowAnyException();
    }

    @Test
    void 라운드가_시작하면_플레이어와_딜러에게_카드를_나누어준다() {
        // given
        CardDeck cardDeck = CardDeck.of(CardDeckGenerator.generateCardDeck());

        Players players = Players.of(
                List.of(Player.of("pobi1"),
                        Player.of("pobi2")
                )
        );
        Participants participants = Participants.of(Dealer.of(), players);

        // when
        participants.distributeCards(cardDeck);

        // then
        assertThat(cardDeck.getCards()).hasSize(46);
    }

    @Test
    void 플레이어_이름으로_카드를_나누어준다() {
        // given
        Players players = Players.of(
                List.of(Player.of("pobi1"),
                        Player.of("pobi2"))
        );
        Participants participants = Participants.of(Dealer.of(), players);

        // when
        participants.passCardToPlayer("pobi1", Card.of(TrumpNumber.SIX, TrumpShape.HEART));

        // then
        Player pobi1 = participants.getPlayerByName("pobi1");
        assertThat(pobi1.getScore()).isEqualTo(6);
    }

    @Test
    void 딜러에게_카드를_나누어준다() {
        // given
        Players players = Players.of(
                List.of(Player.of("pobi1"),
                        Player.of("pobi2"))
        );
        Participants participants = Participants.of(Dealer.of(), players);

        // when
        participants.passCardToDealer(Card.of(TrumpNumber.SIX, TrumpShape.HEART));

        // then
        int score = participants.getScoreOfDealer();
        assertThat(score).isEqualTo(6);
    }

    @Test
    void 플레이어의_이름을_입력받아_스코어를_반환한다() {
        // given
        Player target = Player.of("pobi1");
        target.receive(Card.of(TrumpNumber.NINE, TrumpShape.CLUB));

        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        target,
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        Participants participants = Participants.of(dealer, players);

        // when
        int score = participants.getScoreOf("pobi1");

        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    void 딜러의_스코어를_반환한다() {
        // given

        Dealer dealer = Dealer.of();
        dealer.receive(Card.of(TrumpNumber.NINE, TrumpShape.CLUB));
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2")
                )
        );
        Participants participants = Participants.of(dealer, players);

        // when
        int score = participants.getScoreOfDealer();

        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    void 플레이어들의_이름을_반환한다() {
        // given
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        Participants participants = Participants.of(dealer, players);

        // when
        List<String> names = participants.getPlayersName();

        // then
        assertThat(names).hasSize(3).contains("pobi1", "pobi2", "pobi3");
    }

    @Test
    void 이름으로_플레이어의_이름을_반환한다() {
        // given
        Dealer dealer = Dealer.of();
        Players players = Players.of(
                List.of(
                        Player.of("pobi1"),
                        Player.of("pobi2"),
                        Player.of("pobi3")
                )
        );
        Participants participants = Participants.of(dealer, players);

        // when
        Player pobi1 = participants.getPlayerByName("pobi1");

        // then
        assertThat(pobi1).isEqualTo(Player.of("pobi1"));
    }
}
