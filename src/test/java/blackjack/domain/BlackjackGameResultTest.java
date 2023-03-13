package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.participant.Amount;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantName;
import blackjack.util.CardPickerGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackGameResultTest {

    @Test
    @DisplayName("플래이어가 1000,2000배팅 후 승리했을때 딜러의 수익금 -3000원 리턴")
    void getDealerLosePrize() {
        //given
        List<Integer> testData = settingTestData();
        BlackjackGame game = BlackjackGame.of(
                List.of(new ParticipantName("ako")),
                List.of(new Amount("2000")),
                Deck.create(new TestCardPickerGenerator(testData))
        );
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }
        BlackjackGameResult blackjackGameResult = game.generatePlayersResult(new BlackJackReferee());

        //when
        blackjackGameResult.calculatePlayersPrizeByGameResult();
        BigDecimal result = blackjackGameResult.calculateDealerPrizeByGameResult();

        //then

        assertThat(result).isEqualTo(new BigDecimal(-2000));
    }

    @Test
    @DisplayName("플래이어가 1000배팅 후 패배 했을 때 딜러의 수익금 1000원 리턴")
    void getDealerWinPrize() {
        //given
        List<Integer> testData = settingTestData2();
        BlackjackGame game = BlackjackGame.of(
                List.of(new ParticipantName("ako")),
                List.of(new Amount("2000")),
                Deck.create(new TestCardPickerGenerator(testData)
                ));
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }
        BlackjackGameResult blackjackGameResult = game.generatePlayersResult(new BlackJackReferee());

        //when
        blackjackGameResult.calculatePlayersPrizeByGameResult();
        BigDecimal result = blackjackGameResult.calculateDealerPrizeByGameResult();

        //then
        assertThat(result).isEqualTo(new BigDecimal(2000));
    }

    @Test
    @DisplayName("플래이어가 1000배팅 후 패배 했을 때 딜러의 수익금 1000원 리턴")
    void getDealerBlackjackWinPrize() {
        //given
        List<Integer> testData = settingTestData3();
        BlackjackGame game = BlackjackGame.of(
                List.of(new ParticipantName("ako")),
                List.of(new Amount("2000")),
                Deck.create(new TestCardPickerGenerator(testData)
                ));
        for (Participant participant : game.getParticipants()) {
            game.getTwoHitCards(participant);
        }
        BlackjackGameResult blackjackGameResult = game.generatePlayersResult(new BlackJackReferee());

        //when
        blackjackGameResult.calculatePlayersPrizeByGameResult();
        BigDecimal result = blackjackGameResult.calculateDealerPrizeByGameResult();

        //then
        assertThat(result).isEqualTo(new BigDecimal(-3000));
    }
    private static List<Integer> settingTestData() {
        List<Integer> testData = new ArrayList<>();
        testData.add(1);
        testData.add(1);
        testData.add(0);
        testData.add(1);
        testData.add(3);
        testData.add(4);
        return testData;
    }
    private static List<Integer> settingTestData2() {
        List<Integer> testData = new ArrayList<>();
        testData.add(0);
        testData.add(0);
        testData.add(0);
        testData.add(0);
        return testData;
    }

    private static List<Integer> settingTestData3() {
        List<Integer> testData = new ArrayList<>();
        testData.add(1);
        testData.add(1);
        testData.add(0);
        testData.add(6);
        return testData;
    }

    class TestCardPickerGenerator implements CardPickerGenerator {

        List<Integer> randomIndex;

        TestCardPickerGenerator(List<Integer> randomIndex) {
            this.randomIndex = randomIndex;
        }

        @Override
        public int generator(final int number) {
            return randomIndex.remove(0);
        }
    }
}
