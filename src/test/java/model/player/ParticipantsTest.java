package model.player;

import model.GameMoney;
import model.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class ParticipantsTest {

    @DisplayName("중복되는 이름 가진 참가자가 있으면 예외가 발생한다.")
    @Test
    void validate() {
        Assertions.assertThatThrownBy(() ->
                        new Participants(List.of(
                                new Participant(new Cards(List.of(
                                        Card.of(Suit.SPACE, Denomination.NINE),
                                        Card.of(Suit.SPACE, Denomination.FIVE))),
                                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                                new Participant(new Cards(List.of(
                                        Card.of(Suit.SPACE, Denomination.NINE),
                                        Card.of(Suit.SPACE, Denomination.FIVE))),
                                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000)))

                        )))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createParticipants() {

        CardDeck cardDeck = new CardDeck(Card.createCardDeck());

        return Stream.of(Arguments.of(
                List.of(new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("배키"), new GameMoney(1000)))),
                List.of(
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("도비"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("리사"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("명오"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("제우스"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("호티"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("초롱"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("조이썬"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("프람"), new GameMoney(1000))),
                        new Participant(cardDeck.selectRandomCards(CardSize.TWO),
                                new ParticipantProfile(new Name("폰드"), new GameMoney(1000)))
                )));
    }

    @DisplayName("참가자가 2명보다 작거나 8명보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createParticipants")
    void offerCardToParticipant(List<Participant> participants) {
        Assertions.assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
