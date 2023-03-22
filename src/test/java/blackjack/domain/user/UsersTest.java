package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.result.CardResult;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UsersTest {

    private static final Name TEST_PLAYER_NAME1 = new PlayerName("필립");
    private static final Name TEST_PLAYER_NAME2 = new PlayerName("홍실");
    private static final Name TEST_PLAYER_NAME3 = new PlayerName("제이미");
    private static final Name DEALER_NAME = new DealerName();

    final static List<Card> testCards = List.of(new Card(CardShape.SPADE, CardNumber.ACE),
            new Card(CardShape.CLOVER, CardNumber.TEN),
            new Card(CardShape.CLOVER, CardNumber.NINE),
            new Card(CardShape.HEART, CardNumber.JACK),
            new Card(CardShape.HEART, CardNumber.NINE),
            new Card(CardShape.DIAMOND, CardNumber.FOUR));

    Queue<CardGroup> generateCardGroups() {
        final Queue<CardGroup> cardGroups = new LinkedList<>();
        for (int i = 0; i < testCards.size(); i = i + 2) {
            cardGroups.add(new CardGroup(testCards.get(i), testCards.get(i + 1)));
        }
        return cardGroups;
    }

    @Nested
    class validation {

        @Test
        @DisplayName("플레이어의 이름이 6개 이상 입력되는 경우 예외처리")
        void throwExceptionIfPlayerNamesLengthOver5() {
            final List<String> playerNames = List.of(TEST_PLAYER_NAME1.getValue(), TEST_PLAYER_NAME2.getValue()
                    , TEST_PLAYER_NAME3.getValue(), "네오", "솔라", "다니");

            final Runnable initialUsersByPlayerNamesLengthOver5 =
                    () -> new Users(playerNames, new LinkedList<>());

            assertThatThrownBy(initialUsersByPlayerNamesLengthOver5::run)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어의 이름은 5개까지만 입력해야 합니다.");
        }

        @Test
        @DisplayName("조건을 만족하는 경우 정상적으로 Users가 생성된다.")
        void initialUsersSuccess() {
            final List<String> playerNames = List.of(TEST_PLAYER_NAME2.getValue()
                    , TEST_PLAYER_NAME3.getValue(), TEST_PLAYER_NAME1.getValue());

            final Runnable initialUsers =
                    () -> new Users(playerNames, generateCardGroups());

            assertDoesNotThrow(initialUsers::run);
        }
    }

    @Test
    @DisplayName("유저들의 이름을 받고 CardGroup을 반환하는 기능 테스트")
    void getUsersStatus() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue(), TEST_PLAYER_NAME2.getValue())
                , generateCardGroups());

        final CardGroup philipCardGroup = users.getCardGroupBy(TEST_PLAYER_NAME1);

        assertThat(philipCardGroup.getCards())
                .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
    }

    @Test
    @DisplayName("유저들의 첫 패를 반환하는 기능 테스트")
    void getUsersInitialStatus() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue(), TEST_PLAYER_NAME2.getValue())
                , generateCardGroups());

        final Map<Name, CardGroup> userNameAndFirstOpenCardGroups = users.getUserNameAndFirstOpenCardGroups();

        assertSoftly(softly -> {
            softly.assertThat(userNameAndFirstOpenCardGroups.get(TEST_PLAYER_NAME1).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(userNameAndFirstOpenCardGroups.get(TEST_PLAYER_NAME2).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(4, 6));
            softly.assertThat(userNameAndFirstOpenCardGroups.get(DEALER_NAME).getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 1));
        });
    }

    @Test
    @DisplayName("딜러의 스코어가 16이 넘는지 확인하는 기능 테스트")
    void isDealerOverDrawLimitTest() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue()),
                generateCardGroups());

        boolean dealerOverDrawLimit = users.isDealerUnderDrawLimit();

        assertThat(dealerOverDrawLimit).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드를 하나 추가하는 기능 테스트")
    void drawDealerTest() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue()), generateCardGroups());

        users.drawDealer(new Card(CardShape.HEART, CardNumber.NINE));
        final CardResult cardResult = users.getUserNameAndCardResults().get(DEALER_NAME);

        assertThat(cardResult.getCards().getCards())
                .containsExactly(new Card(CardShape.SPADE, CardNumber.ACE)
                        , new Card(CardShape.CLOVER, CardNumber.TEN)
                        , new Card(CardShape.HEART, CardNumber.NINE));
    }

    @Test
    @DisplayName("플레이어 리스트를 반환하는 기능 테스트")
    void getPlayersTest() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue()), generateCardGroups());

        final List<Name> players = users.getPlayerNames();

        assertThat(players).containsExactly(TEST_PLAYER_NAME1);
    }

    @Test
    @DisplayName("유저(플레이어+딜러)의 이름과 카드목록 점수를 반환하는 기능 테스트")
    void getUserNamesAndResultsTest() {
        final Users users = new Users(List.of(TEST_PLAYER_NAME1.getValue()),
                generateCardGroups());

        final Map<Name, CardResult> userNameAndCardResults = users.getUserNameAndCardResults();

        assertSoftly(softly -> {
            softly.assertThat(userNameAndCardResults.get(DEALER_NAME).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(0, 2));
            softly.assertThat(userNameAndCardResults.get(DEALER_NAME).getScore().getValue())
                    .isEqualTo(21);
            softly.assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME1).getCards().getCards())
                    .containsExactlyInAnyOrderElementsOf(testCards.subList(2, 4));
            softly.assertThat(userNameAndCardResults.get(TEST_PLAYER_NAME1).getScore().getValue())
                    .isEqualTo(19);
        });
    }
}
