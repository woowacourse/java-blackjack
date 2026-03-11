package domain.pariticipant;

import domain.card.*;
import infra.FakeCardShuffler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static constant.BlackjackConstant.DEALER_NAME;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;
import static test_util.TestUtil.createPlayer;
import static test_util.TestUtil.creteDealer;

class ParticipantTest {
    @ParameterizedTest
    @MethodSource("참가자_초기_카드_뽑기_테스트_케이스")
    @DisplayName("참가자의 초기 카드를 INIT_DRAW_COUNT만큼 뽑는다.")
    public void drawInitialCards(Participant participant) throws Exception {
        Deck deck = Deck.initCardDeck(new FakeCardShuffler());
        CardShuffler cardShuffler = new FakeCardShuffler();

        // when
        participant.drawInitialCards(deck);

        // then
        Hand hand = participant.getHand();
        Assertions.assertThat(hand.getHands()).hasSize(INIT_DRAW_COUNT);
    }

    private static Stream<Participant> 참가자_초기_카드_뽑기_테스트_케이스() {

        return Stream.of(
                createPlayer("pobi", new ArrayList<>()),
                creteDealer(DEALER_NAME, new ArrayList<>())
        );
    }
}
