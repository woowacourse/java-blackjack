package domain.cardtable;

import domain.area.CardArea;
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

class CardTableTest {

    CardArea participantCardArea;

    CardArea dealerCardArea;

    CardDeck cardDeck;

    @BeforeEach
    void initCardArea() {
        cardDeck = CardDeck.shuffledFullCardDeck();
        participantCardArea = new CardArea(new Card(CLOVER, TEN), new Card(CLOVER, NINE));
        dealerCardArea = new CardArea(new Card(DIAMOND, TEN), new Card(DIAMOND, NINE));
    }

    @Test
    @DisplayName("createCardArea() : 각 player의 카드 영역을 생성해준다.")
    void test_dealCard() throws Exception {
        //given
        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        //when
        final CardArea cardArea = cardTable.createCardArea();

        //then
        assertThat(cardArea.cards()).hasSize(2);
    }

    @Test
    @DisplayName("matchBetween() : 참여자가 bust 일 경우에는 참여자가 무조건 게임에서 진다.")
    void test_matchBetween_bust_participant_must_lose_participant() throws Exception {
        //given
        participantCardArea.addCard(new Card(SPADE, TEN));
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);

        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

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
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);
        dealerCardArea.addCard(new Card(SPADE, TEN));

        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

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
            final CardArea participantCardArea, final CardArea dealerCardArea,
            final ParticipantResult participantResult) throws Exception {

        //given
        final Participant participant = new Participant(new Name("name"), participantCardArea);
        final Dealer dealer = new Dealer(dealerCardArea);

        final CardTable cardTable = CardTable.readyToPlayBlackjack(cardDeck);

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
        final CardArea participantDrawCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        final CardArea dealerDrawCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, TEN)
        );

        //참여자가 이길 경우
        final CardArea participantWinCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, TEN)
        );

        final CardArea dealerLoseCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, NINE)
        );

        //딜러가 이길 경우
        final CardArea participantLoseCardArea = new CardArea(
                new Card(CardShape.SPADE, TEN),
                new Card(CardShape.DIAMOND, NINE)
        );

        final CardArea dealerWinCardArea = new CardArea(
                new Card(HEART, TEN),
                new Card(CLOVER, TEN)
        );

        return Stream.of(
                Arguments.of(participantDrawCardArea, dealerDrawCardArea, DRAWER),
                Arguments.of(participantWinCardArea, dealerLoseCardArea, WINNER),
                Arguments.of(participantLoseCardArea, dealerWinCardArea, LOSER)
        );
    }
}
