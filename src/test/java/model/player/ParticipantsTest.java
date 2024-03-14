package model.player;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardDeck;
import model.card.CardNumber;
import model.card.CardShape;
import model.card.CardSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ParticipantsTest {

    @DisplayName("중복되는 이름 가진 참가자가 있으면 예외가 발생한다.")
    @Test
    void validate() {
        Assertions.assertThatThrownBy(() ->
                        new Participants(List.of(
                                new Participant("켬미",
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        100),
                                new Participant("켬미",
                                        List.of(new Card(CardShape.SPACE, CardNumber.NINE),
                                                new Card(CardShape.SPACE, CardNumber.FIVE)),
                                        200)
                        )))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createParticipants() {

        CardDeck cardDeck = new CardDeck(CardDeck.createCards());

        return Stream.of(Arguments.of(
                List.of(new Participant("배키", cardDeck.selectRandomCards(CardSize.TWO), 100)),
                List.of(
                        new Participant("도비", cardDeck.selectRandomCards(CardSize.TWO), 200),
                        new Participant("리사", cardDeck.selectRandomCards(CardSize.TWO), 300),
                        new Participant("명오", cardDeck.selectRandomCards(CardSize.TWO), 400),
                        new Participant("제우스", cardDeck.selectRandomCards(CardSize.TWO), 500),
                        new Participant("호티", cardDeck.selectRandomCards(CardSize.TWO), 600),
                        new Participant("초롱", cardDeck.selectRandomCards(CardSize.TWO), 700),
                        new Participant("조이썬", cardDeck.selectRandomCards(CardSize.TWO), 800),
                        new Participant("프람", cardDeck.selectRandomCards(CardSize.TWO), 900),
                        new Participant("폰드", cardDeck.selectRandomCards(CardSize.TWO), 1000))));
    }

    @DisplayName("참가자가 2명보다 작거나 8명보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createParticipants")
    void offerCardToParticipant(List<Participant> participants) {
        Assertions.assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
