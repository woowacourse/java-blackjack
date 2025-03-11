package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackResultEvaluatorTest {

    @Test
    void 블랙잭_플레이어의_승패_결과를_계산한다() {
        // given
        List<TrumpCard> pobiCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> rookieCards = List.of(new TrumpCard(Suit.HEART, CardValue.K),
                new TrumpCard(Suit.HEART, CardValue.NINE));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.CLOVER, CardValue.J));

        List<String> names = List.of("포비", "루키");
        BlackjackParticipants participants = new BlackjackParticipants(names, new Dealer());

        for (int i = 0; i < pobiCards.size(); i++) {
            participants.addCard("포비", pobiCards.get(i));
        }

        for (int i = 0; i < rookieCards.size(); i++) {
            participants.addCard("루키", rookieCards.get(i));
        }

        for (int i = 0; i < dealerCards.size(); i++) {
            participants.addCard(participants.dealerName(), dealerCards.get(i));
        }

        // when
        Map<String, WinStatus> winStatuses = BlackjackResultEvaluator.calculateWinStatus(participants);

        // then
        assertThat(winStatuses.get("포비"))
                .isEqualTo(WinStatus.DRAW);
        assertThat(winStatuses.get("루키"))
                .isEqualTo(WinStatus.WIN);
    }

    @Test
    void 플레이어의_결과로_딜러의_승패횟수를_계산한다() {
        // given
        List<TrumpCard> pobiCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> rookieCards = List.of(new TrumpCard(Suit.HEART, CardValue.K),
                new TrumpCard(Suit.HEART, CardValue.NINE));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.TEN),
                new TrumpCard(Suit.CLOVER, CardValue.J));

        List<String> names = List.of("포비", "루키");
        BlackjackParticipants participants = new BlackjackParticipants(names, new Dealer());

        for (int i = 0; i < pobiCards.size(); i++) {
            participants.addCard("포비", pobiCards.get(i));
        }

        for (int i = 0; i < rookieCards.size(); i++) {
            participants.addCard("루키", rookieCards.get(i));
        }

        for (int i = 0; i < dealerCards.size(); i++) {
            participants.addCard(participants.dealerName(), dealerCards.get(i));
        }

        // when
        DealerWinStatus dealerWinStatus = BlackjackResultEvaluator.calculateDealerWinStatus(participants);

        // then
        DealerWinStatus expected = new DealerWinStatus(2, 0);
        assertThat(dealerWinStatus).isEqualTo(expected);
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다() {
        // given
        List<TrumpCard> pobiCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.SEVEN),
                new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.J),
                new TrumpCard(Suit.HEART, CardValue.SEVEN),
                new TrumpCard(Suit.HEART, CardValue.NINE));
        List<String> names = List.of("포비");

        BlackjackParticipants participants = new BlackjackParticipants(names, new Dealer());

        for (int i = 0; i < pobiCards.size(); i++) {
            participants.addCard("포비", pobiCards.get(i));
        }

        for (int i = 0; i < dealerCards.size(); i++) {
            participants.addCard(participants.dealerName(), dealerCards.get(i));
        }
        // when
        DealerWinStatus dealerWinStatus = BlackjackResultEvaluator.calculateDealerWinStatus(participants);

        // then
        assertThat(dealerWinStatus).isEqualTo(new DealerWinStatus(0, 0));
    }

    @Test
    void 딜러와_플레이어가_둘다_버스트면_무승부로_처리한다2() {
        // given
        List<TrumpCard> pobiCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J), new TrumpCard(Suit.CLOVER, CardValue.SEVEN));
        List<TrumpCard> tudaCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.HEART, CardValue.TWO), new TrumpCard(Suit.CLOVER, CardValue.THREE));
        List<TrumpCard> dealerCards = List.of(new TrumpCard(Suit.HEART, CardValue.SIX),
                new TrumpCard(Suit.CLOVER, CardValue.J), new TrumpCard(Suit.SPADE, CardValue.J));
        List<String> names = List.of("포비", "투다");

        BlackjackParticipants participants = new BlackjackParticipants(names, new Dealer());

        for (int i = 0; i < pobiCards.size(); i++) {
            participants.addCard("포비", pobiCards.get(i));
        }

        for (int i = 0; i < tudaCards.size(); i++) {
            participants.addCard("투다", tudaCards.get(i));
        }

        for (int i = 0; i < pobiCards.size(); i++) {
            participants.addCard(participants.dealerName(), dealerCards.get(i));
        }

        // when
        DealerWinStatus dealerWinStatus = BlackjackResultEvaluator.calculateDealerWinStatus(participants);

        //then
        assertThat(dealerWinStatus).isEqualTo(new DealerWinStatus(0, 1));
    }


}
