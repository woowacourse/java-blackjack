package domain.cardtable;

import domain.card.Card;
import domain.card.CardShape;
import domain.deck.CardDeck;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.participant.Participant;
import domain.player.participant.ParticipantResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardShape.HEART;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.TEN;
import static domain.player.participant.ParticipantResult.DRAWER;
import static domain.player.participant.ParticipantResult.LOSER;
import static domain.player.participant.ParticipantResult.WINNER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTableTest {

    Participant participant;

    Dealer dealer;

    CardDeck cardDeck;

    CardTable cardTable;

    @BeforeEach
    void initCardArea() {
        cardDeck = CardDeck.shuffledFullCardDeck();
        cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        participant = new Participant(new Name("name"));
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(CLOVER, NINE));

        dealer = new Dealer();
        dealer.hit(new Card(DIAMOND, TEN));
        dealer.hit(new Card(DIAMOND, NINE));
    }

    @Test
    @DisplayName("matchBetween() : 참여자가 bust 일 경우에는 참여자가 무조건 게임에서 진다.")
    void test_matchBetween_bust_participant_must_lose_participant() throws Exception {
        //given
        participant.hit(new Card(SPADE, TEN));

        //when
        final Map<Participant, ParticipantResult> gameResult =
                cardTable.determineWinner(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResult).hasSize(1),
                () -> assertThat(gameResult).containsValue(LOSER)
        );
    }

    @Test
    @DisplayName("matchBetween() : 딜러는 bust 이면서 참여자가 bust 가 아니면 참여자가 무조건 게임에서 이긴다.")
    void test_matchBetween_bust_dealer_must_lose_dealer() throws Exception {
        //given
        dealer.hit(new Card(SPADE, TEN));

        //when
        final Map<Participant, ParticipantResult> gameResult =
                cardTable.determineWinner(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResult).hasSize(1),
                () -> assertThat(gameResult).containsValue(WINNER)
        );
    }

    @ParameterizedTest
    @MethodSource("makeBothNotBust")
    @DisplayName("matchBetween() : 딜러, 참여자 모두 버스트가 아닐 때 점수가 높은 쪽이 이기고, 같으면 무승부이다.")
    void test_matchBetween_not_bust_win_higher_score_or_draw_same_score(
            final Participant participant, final Dealer dealer,
            final ParticipantResult participantResult) throws Exception {

        //when & then
        final Map<Participant, ParticipantResult> gameResult = cardTable.determineWinner(
                List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResult).hasSize(1),
                () -> assertThat(gameResult).containsValue(participantResult)
        );
    }

    static Stream<Arguments> makeBothNotBust() {

        //무승부
        final Participant participant1 = new Participant(new Name("name1"));
        participant1.hit(new Card(CardShape.DIAMOND, TEN));
        participant1.hit(new Card(CardShape.SPADE, TEN));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(HEART, TEN));
        dealer1.hit(new Card(CLOVER, TEN));

        //참여자가 이길 경우
        final Participant participant2 = new Participant(new Name("name2"));
        participant2.hit(new Card(CardShape.SPADE, TEN));
        participant2.hit(new Card(CardShape.DIAMOND, TEN));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(HEART, TEN));
        dealer2.hit(new Card(CLOVER, NINE));

        //딜러가 이길 경우
        final Participant participant3 = new Participant(new Name("name3"));
        participant3.hit(new Card(CardShape.SPADE, TEN));
        participant3.hit(new Card(CardShape.DIAMOND, NINE));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(HEART, TEN));
        dealer3.hit(new Card(CLOVER, TEN));

        return Stream.of(
                Arguments.of(participant1, dealer1, DRAWER),
                Arguments.of(participant2, dealer2, WINNER),
                Arguments.of(participant3, dealer3, LOSER)
        );
    }

    @Test
    @DisplayName("dealCardTo() : Player에게 카드를 나눠줄 수 있다.")
    void test_dealCardTo() throws Exception {
        //when & then
        assertAll(
                () -> assertTrue(cardTable.dealCardTo(participant)),
                () -> assertFalse(cardTable.dealCardTo(dealer)),
                () -> assertThat(participant.cardArea().cards()).hasSize(3),
                () -> assertThat(dealer.cardArea().cards()).hasSize(2)
        );
    }
}
