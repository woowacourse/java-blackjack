package service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Card;
import domain.CardDeck;
import domain.CardRank;
import domain.CardSuit;
import domain.HandCards;
import domain.Participant;
import domain.Participants;
import infra.CardShuffler;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    private final BlackjackService blackjackService = new BlackjackService(new FakeCardShuffler());

    @Test
    @DisplayName("카드덱에서 지정한 수만큼의 카드를 뽑는다.")
    public void 카드_뽑기_성공() throws Exception {
        // given
        CardDeck cardDeck = CardDeck.initCardDeck();
        int originCount = cardDeck.getDeckSize();
        int drawCount = 2;

        // when
        List<Card> cards = blackjackService.drawCard(cardDeck, drawCount);

        // then
        assertThat(cards).hasSize(drawCount);
        assertThat(cardDeck.getDeckSize()).isEqualTo(originCount - drawCount);
    }

    static class FakeCardShuffler implements CardShuffler {

        @Override
        public int shuffleCardDeck(int deckSize) {
            return 0;
        }
    }

    @Test
    @DisplayName("딜러와 플레이어들의 카드를 비교해서 딜러의 결과를 반환한다.")
    public void 딜러_결과_계산_성공() throws Exception {
        // given
        Participants participants = initParticipants();

        // when
        DealerFinalResult dealerFinalResult = blackjackService.getDealerFinalResult(participants.getDealer(),
                participants.getPlayers());

        // then
        assertThat(dealerFinalResult.win()).isEqualTo(1);
        assertThat(dealerFinalResult.draw()).isEqualTo(0);
        assertThat(dealerFinalResult.lose()).isEqualTo(1);
    }

    private static Participants initParticipants() {
        Participant pobi = new Participant("포비", new HandCards(new ArrayList<>()), false);
        pobi.addHandCard(new Card(CardSuit.HEART, CardRank.TWO));
        pobi.addHandCard(new Card(CardSuit.SPADE, CardRank.EIGHT));
        pobi.addHandCard(new Card(CardSuit.CLUB, CardRank.ACE));

        Participant jason = new Participant("제이슨", new HandCards(new ArrayList<>()), false);
        jason.addHandCard(new Card(CardSuit.CLUB, CardRank.SEVEN));
        jason.addHandCard(new Card(CardSuit.SPADE, CardRank.KING));

        Participants participants = new Participants(new ArrayList<>(List.of(pobi, jason)));
        Participant dealer = participants.getDealer();
        dealer.addHandCard(new Card(CardSuit.DIAMOND, CardRank.THREE));
        dealer.addHandCard(new Card(CardSuit.CLUB, CardRank.NINE));
        dealer.addHandCard(new Card(CardSuit.DIAMOND, CardRank.EIGHT));
        return participants;
    }
}