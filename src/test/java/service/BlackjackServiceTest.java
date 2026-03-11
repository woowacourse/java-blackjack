package service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import constant.BlackjackConstant;
import domain.Card;
import domain.CardDeck;
import domain.CardRank;
import domain.CardSuit;
import domain.HandCards;
import domain.Name;
import domain.Participant;
import domain.Participants;
import infra.CardShuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// TODO: 경계값 및 예외 테스트 등 추가 필요
class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService(new FakeCardShuffler());

    @Test
    @DisplayName("카드덱에서 지정한 수만큼의 카드를 뽑는다.")
    public void 카드_뽑기_성공() {
        // given
        final CardDeck cardDeck = CardDeck.initCardDeck();
        final int originCount = cardDeck.getDeckSize();
        final int drawCount = 2;

        // when
        final List<Card> cards = blackjackService.drawCard(cardDeck, drawCount);

        // then
        assertThat(cards).hasSize(drawCount);
        assertThat(cardDeck.getDeckSize()).isEqualTo(originCount - drawCount);
    }

    static class FakeCardShuffler implements CardShuffler {

        @Override
        public int shuffleCardDeck(final int deckSize) {
            return 0;
        }
    }

    @Test
    @DisplayName("딜러와 플레이어들의 카드를 비교해서 딜러의 결과를 반환한다.")
    public void 딜러_결과_계산_성공() {
        // given
        final Participants participants = initParticipants();

        // when
        final List<FinalResult> finalResults = blackjackService.getFinalResults(participants.getDealer(),
                participants.getPlayers());

        // then
        assertThat(finalResults).extracting(
                FinalResult::name,
                FinalResult::win,
                FinalResult::draw,
                FinalResult::lose,
                FinalResult::isDealer
        ).containsExactlyInAnyOrder(
                tuple(BlackjackConstant.DEALER_NAME, 1, 0, 1, true),
                tuple("포비", 1, 0, 0, false),
                tuple("제이슨", 0, 0, 1, false)
        );
    }

    private static Participants initParticipants() {
        final Participant pobi = new Participant(new Name("포비"), new HandCards(new ArrayList<>()), false);
        pobi.addHandCard(new Card(CardSuit.HEART, CardRank.TWO));
        pobi.addHandCard(new Card(CardSuit.SPADE, CardRank.EIGHT));
        pobi.addHandCard(new Card(CardSuit.CLUB, CardRank.ACE));

        final Participant jason = new Participant(new Name("제이슨"), new HandCards(new ArrayList<>()), false);
        jason.addHandCard(new Card(CardSuit.CLUB, CardRank.SEVEN));
        jason.addHandCard(new Card(CardSuit.SPADE, CardRank.KING));

        final Participants participants = new Participants(new ArrayList<>(List.of(pobi, jason)));
        final Participant dealer = participants.getDealer();
        dealer.addHandCard(new Card(CardSuit.DIAMOND, CardRank.THREE));
        dealer.addHandCard(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.addHandCard(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        return participants;
    }
}