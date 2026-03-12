package service;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Deck;
import domain.card.Hand;
import domain.card.Shuffler;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// TODO: 경계값 및 예외 테스트 등 추가 필요
class BlackjackServiceTest {

    private final BlackjackGame blackjackGame = new BlackjackGame(initParticipants(), new StubShuffler());


    @Test
    @DisplayName("카드덱에서 지정한 수만큼의 카드를 뽑는다.")
    public void 카드_뽑기_성공() {
        // given
        final Deck deck = new Deck();

        // when
//        final List<Card> cards = blackjackService.drawCard(deck, INIT_DRAW_COUNT);

        // then
//        assertThat(cards).hasSize(INIT_DRAW_COUNT);
//        assertThat(deck).extracting(Deck::getDeck).has;
    }

    static class StubShuffler implements Shuffler {

        @Override
        public void shuffle(final List<Card> list) {
        }
    }

//    @Test
//    @DisplayName("딜러와 플레이어들의 카드를 비교해서 딜러의 결과를 반환한다.")
//    public void 딜러_결과_계산_성공() {
//        // given
//        final Participants participants = initParticipants();
//
//        // when
//        final List<FinalResult> finalResults = blackjackGame.getGameResults(participants.getDealer(),
//                participants.getPlayers());
//
//        // then
//        assertThat(finalResults).extracting(
//                FinalResult::name,
//                FinalResult::winCount,
//                FinalResult::drawCount,
//                FinalResult::loseCount,
//                FinalResult::isDealer
//        ).containsExactlyInAnyOrder(
//                tuple(DEALER_NAME, 1, 0, 1, true),
//                tuple("포비", 1, 0, 0, false),
//                tuple("제이슨", 0, 0, 1, false)
//        );
//    }

    private static Participants initParticipants() {
        final Participant pobi = new Participant(new Name("포비"), new Hand());
        pobi.draw(new Card(CardSuit.HEART, CardRank.TWO));
        pobi.draw(new Card(CardSuit.SPADE, CardRank.EIGHT));
        pobi.draw(new Card(CardSuit.CLUB, CardRank.ACE));

        final Participant jason = new Participant(new Name("제이슨"), new Hand());
        jason.draw(new Card(CardSuit.CLUB, CardRank.SEVEN));
        jason.draw(new Card(CardSuit.SPADE, CardRank.KING));

        final Participants participants = new Participants(new ArrayList<>(List.of(pobi, jason)));
        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.THREE));
        dealer.draw(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        return participants;
    }
}
