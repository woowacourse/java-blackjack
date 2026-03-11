package domain.pariticipant;

import domain.card.*;
import infra.FakeCardShuffler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static constant.BlackjackConstant.DEALER_NAME;
import static constant.BlackjackConstant.INIT_DRAW_COUNT;

class ParticipantTest {
    @ParameterizedTest
    @MethodSource("참가자_초기_카드_뽑기_테스트_케이스")
    @DisplayName("참가자의 초기 카드를 INIT_DRAW_COUNT만큼 뽑는다.")
    public void drawInitialCards(Participant participant) throws Exception {
        Deck deck = Deck.initCardDeck();
        CardShuffler cardShuffler = new FakeCardShuffler();

        // when
        participant.drawInitialCards(deck, cardShuffler);

        // then
        Hand hand = participant.getHand();
        Assertions.assertThat(hand.getHandCards()).hasSize(INIT_DRAW_COUNT);
    }

    private static Stream<Participant> 참가자_초기_카드_뽑기_테스트_케이스() {

        return Stream.of(
                createPlayer("pobi", new ArrayList<>()),
                creteDealer(DEALER_NAME, new ArrayList<>())
        );
    }


    private static Player createPlayer(String name, List<Card> handCards) {
        return new Player(
                new Name(name),
                new Hand(handCards));
    }
    private static Dealer creteDealer(String name, List<Card> handCards) {
        return new Dealer(
                new Name(name),
                new Hand(handCards));
    }
    private static Card createCard(CardSuit cardSuit, CardRank cardRank) {
        return new Card(cardSuit, cardRank);
    }

}
