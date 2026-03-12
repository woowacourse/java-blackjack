package domain;

import static domain.BlackjackGame.HIT_DRAW_COUNT;
import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static domain.participant.Dealer.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Hand;
import domain.card.Shuffler;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장(INIT_DRAW_COUNT)을 뽑는다.")
    public void 첫_드로우_성공() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(
                new Participants(List.of(new Player(new Name("zzaekkii"), new Hand()))));

        // when
        blackjackGame.initDraw();

        // then
        final Player zakie = blackjackGame.getParticipants().getPlayers().getFirst();
        final Dealer dealer = blackjackGame.getParticipants().getDealer();
        assertThat(zakie.getHand()).hasSize(INIT_DRAW_COUNT);
        assertThat(dealer.getHand()).hasSize(INIT_DRAW_COUNT);
    }

    @Test
    @DisplayName("Hit할 경우, 1장(HIT_DRAW_COUNT)을 뽑는다.")
    public void 히트_드로우_성공() {
        // given
        final Player player = new Player(new Name("zzaekkii"), new Hand());
        final BlackjackGame blackjackGame = new BlackjackGame(
                new Participants(List.of(player)));
        blackjackGame.initDraw();

        // when
        blackjackGame.hit(player);
        final Participant dealer = blackjackGame.getParticipants().getDealer();
        blackjackGame.hit(dealer);

        // then
        assertThat(player.getHand()).hasSize(INIT_DRAW_COUNT + HIT_DRAW_COUNT);
        assertThat(dealer.getHand()).hasSize(INIT_DRAW_COUNT + HIT_DRAW_COUNT);
    }

    @Test
    @DisplayName("딜러와 플레이어들의 카드를 비교해서 딜러의 결과를 반환한다.")
    public void 딜러_결과_계산_성공() {
        // given
        final Participants participants = initParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(participants);

        // when
        final List<FinalResult> finalResults = blackjackGame.getGameResults();

        // then
        assertThat(finalResults).extracting(
                FinalResult::name,
                FinalResult::winCount,
                FinalResult::drawCount,
                FinalResult::loseCount,
                FinalResult::isDealer
        ).containsExactlyInAnyOrder(
                tuple(DEALER_NAME, 1, 0, 1, true),
                tuple("포비", 1, 0, 0, false),
                tuple("제이슨", 0, 0, 1, false)
        );
    }

    private static Participants initParticipants() {
        final Player pobi = new Player(new Name("포비"), new Hand());
        pobi.draw(new Card(CardSuit.HEART, CardRank.TWO));
        pobi.draw(new Card(CardSuit.SPADE, CardRank.EIGHT));
        pobi.draw(new Card(CardSuit.CLUB, CardRank.ACE));

        final Player jason = new Player(new Name("제이슨"), new Hand());
        jason.draw(new Card(CardSuit.CLUB, CardRank.SEVEN));
        jason.draw(new Card(CardSuit.SPADE, CardRank.KING));

        final Participants participants = new Participants(new ArrayList<>(List.of(pobi, jason)));
        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.THREE));
        dealer.draw(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        return participants;
    }

    static class StubShuffler implements Shuffler {

        @Override
        public void shuffle(final List<Card> list) {
        }
    }
}
