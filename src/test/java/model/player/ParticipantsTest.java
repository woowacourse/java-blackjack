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
import java.util.Map;
import java.util.stream.Stream;

class ParticipantsTest {

    @DisplayName("중복되는 이름 가진 참가자가 있으면 예외가 발생한다.")
    @Test
    void validate() {
        CardDeck cards = new CardDeck(Card.createCardDeck());

        Assertions.assertThatThrownBy(() ->
                        new Participants(List.of(
                                new Participant(cards.selectRandomCards(CardSize.TWO),
                                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                                new Participant(cards.selectRandomCards(CardSize.TWO),
                                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))))))
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
                                new ParticipantProfile(new Name("폰드"), new GameMoney(1000))))));
    }

    @DisplayName("참가자가 2명보다 작거나 8명보다 크면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("createParticipants")
    void offerCardToParticipant(List<Participant> participants) {
        Assertions.assertThatThrownBy(() -> new Participants(participants))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자들의 이름을 알 수 있다.")
    @Test
    void findParticipantsName() {
        CardDeck cards = new CardDeck(Card.createCardDeck());

        Participants participants = new Participants(List.of(
                new Participant(cards.selectRandomCards(CardSize.TWO),
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(cards.selectRandomCards(CardSize.TWO),
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        List<Name> names = participants.findParticipantsName();
        Assertions.assertThat(names).containsExactly(new Name("켬미"), new Name("배키"));
    }

    @DisplayName("참가자들의 이름과 카드들을 알 수 있다.")
    @Test
    void matchParticipantNameAndCards() {
        Cards firstCards = new Cards(
                List.of(Card.of(Suit.SPACE, Denomination.TWO),
                        Card.of(Suit.SPACE, Denomination.THREE)));
        Cards secondCards = new Cards(
                List.of(Card.of(Suit.SPACE, Denomination.FOUR),
                        Card.of(Suit.SPACE, Denomination.FIVE)));

        Participants participants = new Participants(List.of(
                new Participant(firstCards,
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(secondCards,
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        Map<Name, Cards> result = participants.matchParticipantNameAndCards();
        Map<Name, Cards> expected = Map.of(new Name("켬미"), firstCards, new Name("배키"), secondCards);
        Assertions.assertThat(result).isEqualTo(expected);

    }

    @DisplayName("참가자만 블랙잭일 경우 이익은 배팅금액의 1.5배이다.")
    @Test
    void matchNameAndRevenuesBlackJackWin() {
        Participants participants = new Participants(List.of(
                new Participant(new Cards(
                        List.of(Card.of(Suit.SPACE, Denomination.ACE),
                                Card.of(Suit.SPACE, Denomination.TEN))),
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(new Cards(
                        List.of(Card.of(Suit.HEART, Denomination.ACE),
                                Card.of(Suit.HEART, Denomination.TEN))),
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.JACK),
                        Card.of(Suit.CLOVER, Denomination.KING))));

        Map<Name, Integer> result = participants.matchNameAndRevenues(dealer);
        Map<Name, Integer> expected = Map.of(new Name("켬미"), 1500, new Name("배키"), 1500);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("참가자와 딜러 모두 블랙잭일 경우 이익은 없다.")
    @Test
    void matchNameAndRevenueBlackJackDraw() {
        Participants participants = new Participants(List.of(
                new Participant(new Cards(
                        List.of(Card.of(Suit.SPACE, Denomination.ACE),
                                Card.of(Suit.SPACE, Denomination.TEN))),
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(new Cards(
                        List.of(Card.of(Suit.HEART, Denomination.ACE),
                                Card.of(Suit.HEART, Denomination.TEN))),
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.ACE),
                        Card.of(Suit.CLOVER, Denomination.TEN))));

        Map<Name, Integer> result = participants.matchNameAndRevenues(dealer);
        Map<Name, Integer> expected = Map.of(new Name("켬미"), 0, new Name("배키"), 0);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    static Stream<Arguments> createDealer() {
        Dealer underThresholdDealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.NINE),
                        Card.of(Suit.CLOVER, Denomination.TEN))));

        Dealer overThresholdDealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.DIAMOND, Denomination.TEN),
                        Card.of(Suit.DIAMOND, Denomination.KING))));
        overThresholdDealer.addCard(Card.of(Suit.DIAMOND, Denomination.TWO));

        return Stream.of(org.junit.jupiter.params.provider.Arguments.of(
                underThresholdDealer,
                overThresholdDealer
        ));
    }

    @DisplayName("참가자가 승일 경우 이익은 배팅금액의 1배이다.")
    @ParameterizedTest
    @MethodSource("createDealer")
    void matchNameAndRevenuesWin(Dealer dealer) {
        Participants participants = new Participants(List.of(
                new Participant(new Cards(
                        List.of(Card.of(Suit.SPACE, Denomination.JACK),
                                Card.of(Suit.SPACE, Denomination.TEN))),
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(new Cards(
                        List.of(Card.of(Suit.HEART, Denomination.JACK),
                                Card.of(Suit.HEART, Denomination.TEN))),
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        Map<Name, Integer> result = participants.matchNameAndRevenues(dealer);
        Map<Name, Integer> expected = Map.of(new Name("켬미"), 1000, new Name("배키"), 1000);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("참가자가 패일 경우 이익은 배팅금액의 -1배이다.")
    @Test
    void matchNameAndRevenuesLose() {
        Participant underThresholdParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.TEN))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));

        Participant overThresholdParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.KING),
                Card.of(Suit.CLOVER, Denomination.KING))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        overThresholdParticipant.addCard(Card.of(Suit.HEART, Denomination.TWO));

        Participants participants = new Participants(List.of(underThresholdParticipant, overThresholdParticipant));

        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.ACE),
                        Card.of(Suit.CLOVER, Denomination.KING))));

        Map<Name, Integer> result = participants.matchNameAndRevenues(dealer);
        Map<Name, Integer> expected = Map.of(new Name("켬미"), -1000, new Name("배키"), -1000);
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @DisplayName("참가자와 무승부일 경우 이익은 없다.")
    @Test
    void matchNameAndRevenuesDraw() {
        Participants participants = new Participants(List.of(
                new Participant(new Cards(
                        List.of(Card.of(Suit.SPACE, Denomination.JACK),
                                Card.of(Suit.SPACE, Denomination.TEN))),
                        new ParticipantProfile(new Name("켬미"), new GameMoney(1000))),
                new Participant(new Cards(
                        List.of(Card.of(Suit.HEART, Denomination.JACK),
                                Card.of(Suit.HEART, Denomination.TEN))),
                        new ParticipantProfile(new Name("배키"), new GameMoney(1000)))));

        Dealer dealer = new Dealer(
                new Cards(List.of(
                        Card.of(Suit.CLOVER, Denomination.JACK),
                        Card.of(Suit.CLOVER, Denomination.TEN))));

        Map<Name, Integer> result = participants.matchNameAndRevenues(dealer);
        Map<Name, Integer> expected = Map.of(new Name("켬미"), 0, new Name("배키"), 0);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}
