package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ResultEvaluatorTest {

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
        Participants participants = new Participants(names, new Dealer());

        for (TrumpCard pobiCard : pobiCards) {
            participants.addCard(new ParticipantName("포비"), pobiCard);
        }

        for (TrumpCard rookieCard : rookieCards) {
            participants.addCard(new ParticipantName("루키"), rookieCard);
        }

        for (TrumpCard dealerCard : dealerCards) {
            participants.addCard(participants.dealerName(), dealerCard);
        }

        // when
        Map<ParticipantName, WinStatus> winStatuses = ResultEvaluator.calculateWinStatus(participants);

        // then
        assertThat(winStatuses.get(new ParticipantName("포비")))
                .isEqualTo(WinStatus.DRAW);
        assertThat(winStatuses.get(new ParticipantName("루키")))
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
        Participants participants = new Participants(names, new Dealer());

        for (TrumpCard pobiCard : pobiCards) {
            participants.addCard(new ParticipantName("포비"), pobiCard);
        }

        for (TrumpCard rookieCard : rookieCards) {
            participants.addCard(new ParticipantName("루키"), rookieCard);
        }

        for (TrumpCard dealerCard : dealerCards) {
            participants.addCard(participants.dealerName(), dealerCard);
        }

        // when
        DealerWinStatus dealerWinStatus = ResultEvaluator.calculateDealerWinStatus(participants);

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

        Participants participants = new Participants(names, new Dealer());

        for (TrumpCard pobiCard : pobiCards) {
            participants.addCard(new ParticipantName("포비"), pobiCard);
        }

        for (TrumpCard dealerCard : dealerCards) {
            participants.addCard(participants.dealerName(), dealerCard);
        }
        // when
        DealerWinStatus dealerWinStatus = ResultEvaluator.calculateDealerWinStatus(participants);

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

        Participants participants = new Participants(names, new Dealer());

        for (TrumpCard pobiCard : pobiCards) {
            participants.addCard(new ParticipantName("포비"), pobiCard);
        }

        for (TrumpCard tudaCard : tudaCards) {
            participants.addCard(new ParticipantName("투다"), tudaCard);
        }

        for (TrumpCard dealerCard : dealerCards) {
            participants.addCard(participants.dealerName(), dealerCard);
        }

        // when
        DealerWinStatus dealerWinStatus = ResultEvaluator.calculateDealerWinStatus(participants);

        //then
        assertThat(dealerWinStatus).isEqualTo(new DealerWinStatus(0, 1));
    }


}
