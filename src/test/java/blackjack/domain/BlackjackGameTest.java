package blackjack.domain;

import blackjack.dto.ParticipantsProfitDto;
import blackjack.dto.PersonStatusDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class BlackjackGameTest {

    Players players = new Players(List.of("encho", "glen"));
    BettingMoney bettingMoney = new BettingMoney(
            Map.of(players.getPlayers().get(0), 2000,
                    players.getPlayers().get(1), 3000));

    MockDeckMaker mockDeckMaker = new MockDeckMaker(new ArrayList<>(List.of(
            Card.of(Suit.DIAMOND, Rank.KING),
            Card.of(Suit.CLOVER, Rank.FOUR),
            Card.of(Suit.DIAMOND, Rank.JACK),
            Card.of(Suit.HEART, Rank.TWO),
            Card.of(Suit.SPADE, Rank.TEN),
            Card.of(Suit.CLOVER, Rank.ACE),
            Card.of(Suit.DIAMOND, Rank.TEN),
            Card.of(Suit.SPADE, Rank.SIX)
    )));

    BlackjackGame blackjackGame = new BlackjackGame(players, mockDeckMaker, bettingMoney);

    @Test
    @DisplayName("처음에는 각 참가자들(딜러와 플레이어)에게 카드를 2장씩 나누어준다.")
    void drawInitCard() {
        // given
        blackjackGame.drawInitCard();
        List<PersonStatusDto> participantsStatus = blackjackGame.getAllPersonStatus();

        // expect
        for (PersonStatusDto personStatusDto : participantsStatus) {
            assertThat(personStatusDto.getCards())
                    .hasSize(2);
        }
    }

    @Nested
    @DisplayName("가지고 있는 카드들의 점수 합이")
    class whenScoreIs {
        @Test
        @DisplayName("21점 이하이면 카드를 하나 더 뽑는다.")
        void drawMoreCard_success() {
            // given
            blackjackGame.drawInitCard();

            // expect
            assertThatNoException()
                    .isThrownBy(() -> blackjackGame.drawMoreCard("encho"));
        }

        @Test
        @DisplayName("21점 초과이면 예외가 발생한다.")
        void drawMoreCard_fail() {
            // given
            blackjackGame.drawInitCard();
            blackjackGame.drawMoreCard("encho");

            // expect
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> blackjackGame.drawMoreCard("encho"))
                    .withMessage("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    @Test
    @DisplayName("딜러가 처음 받은 두 장의 카드의 점수 합이 16점 이하이면 한 장 더 뽑는다.")
    void drawDealerMoreCard() {
        // given
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
        blackjackGame.drawInitCard();
        blackjackGame.drawMoreCard("encho");
        blackjackGame.drawDealerMoreCard();

        Person encho = blackjackGame.getParticipantByName("encho");
        Person glen = blackjackGame.getParticipantByName("glen");

        Person dealer = blackjackGame.getParticipantByName("딜러");

        Map<Person, Double> expected = new HashMap<>();
        expected.put(encho, -2000.0);
        expected.put(glen, 4500.0);
        expected.put(dealer, -2500.0);
        ParticipantsProfitDto expectedDto = ParticipantsProfitDto.of(expected);

        // when
        ParticipantsProfitDto participantsProfitDto = blackjackGame.getParticipantsProfitDto();

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