package blackjack.domain;

import blackjack.dto.BettingMoneyDto;
import blackjack.dto.ParticipantsProfitDto;
import blackjack.dto.PersonStatusDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BlackjackGameTest {

    String[] names = {"encho", "glen"};
    MockDeckMaker mockDeckMaker = new MockDeckMaker(new ArrayList<>(List.of(
            new Card(Suit.DIAMOND, Rank.KING),
            new Card(Suit.CLOVER, Rank.FOUR),
            new Card(Suit.DIAMOND, Rank.JACK),
            new Card(Suit.HEART, Rank.TWO),
            new Card(Suit.SPADE, Rank.TEN),
            new Card(Suit.CLOVER, Rank.ACE),
            new Card(Suit.DIAMOND, Rank.TEN),
            new Card(Suit.SPADE, Rank.SIX)
    )));

    @Test
    @DisplayName("입력받은 플레이어의 수가 1명 미만이면 예외가 발생한다.")
    void createZeroPlayer() {
        // given
        String[] names = {};

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BlackjackGame(names, mockDeckMaker))
                .withMessage("[ERROR] 참여할 사람은 한 명 이상이어야 합니다.");
    }

    @Test
    @DisplayName("입력받은 플레이어 이름에 중복돤 이름이 없으면 예외가 발생하지 않는다.")
    void createParticipants_success() {
        // given
        String[] namesSuccess = {"encho", "glen"};
        // expect
        assertThatNoException()
                .isThrownBy(() -> new BlackjackGame(namesSuccess, mockDeckMaker));
    }

    @Test
    @DisplayName("입력받은 플레이어 이름에 중복된 이름이 있으면 예외가 발생한다")
    void createParticipants_fail() {
        // given
        String[] namesFail = {"encho", "encho"};

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new BlackjackGame(namesFail, mockDeckMaker))
                .withMessage("[ERROR] 중복된 이름이 있습니다.");
    }

    @Test
    @DisplayName("처음에는 각 참가자들(딜러와 플레이어)에게 카드를 2장씩 나누어준다.")
    void drawInitCard() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(names, mockDeckMaker);

        // when
        blackjackGame.drawInitCard();
        List<PersonStatusDto> participantsStatus = blackjackGame.getAllPersonStatus();

        // then
        for (PersonStatusDto personStatusDto : participantsStatus) {
            assertThat(personStatusDto.getCards())
                    .hasSize(2);
        }
    }

    @Test
    @DisplayName("가지고 있는 카드들의 점수 합이 21점 이하이면 카드를 하나 더 뽑는다.")
    void drawMoreCard_success() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(names, mockDeckMaker);
        blackjackGame.drawInitCard();

        // expect
        assertThatNoException()
                .isThrownBy(() -> blackjackGame.drawMoreCard("encho"));
    }

    @Test
    @DisplayName("가지고 있는 카드들의 점수 합이 21점 초과이면 예외가 발생한다.")
    void drawMoreCard_fail() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(names, mockDeckMaker);
        blackjackGame.drawInitCard();
        blackjackGame.drawMoreCard("encho");

        // expect
        assertThatIllegalArgumentException()
                .isThrownBy(() -> blackjackGame.drawMoreCard("encho"))
                .withMessage("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
    }

    @Test
    @DisplayName("딜러가 처음 받은 두 장의 카드의 점수 합이 16점 이하이면 한 장 더 뽑는다.")
    void drawDealerMoreCard() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(names, mockDeckMaker);
        blackjackGame.drawInitCard();

        // when
        blackjackGame.drawDealerMoreCard();
        PersonStatusDto dealer = blackjackGame.getPlayerStatus("딜러");

        // then
        assertThat(dealer.getCards())
                .hasSize(3);
    }

    @Test
    @DisplayName("베팅 금액과 참가자 정보로 참가자들의 수익과 딜러의 수익을 계산하여 반환할 수 있다.")
    void getParticipantsProfit() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(names, mockDeckMaker);
        blackjackGame.drawInitCard();
        blackjackGame.drawMoreCard("encho");
        blackjackGame.drawDealerMoreCard();

        Person encho = blackjackGame.getParticipantByName("encho");
        Person glen = blackjackGame.getParticipantByName("glen");

        Map<String, Integer> bettingMoney = new HashMap<>();
        bettingMoney.put(encho.getName(), 2000);
        bettingMoney.put(glen.getName(), 3000);
        BettingMoneyDto bettingMoneyDto = BettingMoneyDto.of(bettingMoney, blackjackGame.getParticipants());

        Person dealer = blackjackGame.getParticipantByName("딜러");

        Map<Person, Double> expected = new HashMap<>();
        expected.put(encho, -2000.0);
        expected.put(glen, 4500.0);
        expected.put(dealer, -2500.0);
        ParticipantsProfitDto expectedDto = ParticipantsProfitDto.of(expected);

        // when
        ParticipantsProfitDto participantsProfitDto = blackjackGame.getParticipantsProfitDto(bettingMoneyDto);

        // then
        assertThat(participantsProfitDto.getParticipantsProfit()).isEqualTo(expectedDto.getParticipantsProfit());
    }

    static class MockDeckMaker implements Shuffler {

        private final List<Card> cards;

        public MockDeckMaker(List<Card> cards) {
            this.cards = cards;
        }

        @Override
        public List<Card> createDeck() {
            return cards;
        }
    }
}