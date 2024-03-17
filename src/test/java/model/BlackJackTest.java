package model;

import model.card.*;
import model.player.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackTest {

    @DisplayName("참가자가 null일 시 예외가 발생한다.")
    @Test
    void validateParticipantIsNotNull() {
        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.TWO))));

        Assertions.assertThatThrownBy(() ->
                        new BlackJack(null, dealer, new CardDeck(Card.createCardDeck())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참가자 없으면 게임이 진행되지 않습니다.");
    }

    @DisplayName("딜러가 null일 시 예외가 발생한다.")
    @Test
    void validateDealerIsNotNull() {
        Participant firstParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.NINE),
                Card.of(Suit.SPACE, Denomination.FIVE))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));

        Participant secondParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.NINE),
                Card.of(Suit.HEART, Denomination.FIVE))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));

        Assertions.assertThatThrownBy(
                        () -> new BlackJack(new Participants(List.of(firstParticipant, secondParticipant)),
                                null
                                , new CardDeck(Card.createCardDeck())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러가 없으면 게임이 진행되지 않습니다.");
    }

    @DisplayName("딜러가 패했을 시, 딜러의 이익은 참가자들의 배팅금액의 -1배이다.")
    @Test
    void findDealerRevenueLose() {
        Participant firstParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.JACK))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        Participant secondParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.JACK))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        Participants participants = new Participants(List.of(firstParticipant, secondParticipant));

        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.CLOVER, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.NINE))));

        BlackJack blackJack = new BlackJack(participants, dealer, new CardDeck(Card.createCardDeck()));

        assertThat(blackJack.findDealerRevenue()).isEqualTo(-2000);
    }

    @DisplayName("딜러가 승했을 시, 딜러의 이익은 참가자들의 배팅금액의 1배이다.")
    @Test
    void findDealerRevenueWin() {
        Participant firstParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.NINE))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        Participant secondParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.NINE))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        Participants participants = new Participants(List.of(firstParticipant, secondParticipant));

        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.CLOVER, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.JACK))));

        BlackJack blackJack = new BlackJack(participants, dealer, new CardDeck(Card.createCardDeck()));

        assertThat(blackJack.findDealerRevenue()).isEqualTo(2000);
    }

    @DisplayName("딜러가 무했을 시, 딜러의 이익은 참가자들의 배팅금액의 0배이다.")
    @Test
    void findDealerRevenueDraw() {
        Participant firstParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.JACK))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        Participant secondParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.JACK))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        Participants participants = new Participants(List.of(firstParticipant, secondParticipant));

        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.CLOVER, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.JACK))));

        BlackJack blackJack = new BlackJack(participants, dealer, new CardDeck(Card.createCardDeck()));

        assertThat(blackJack.findDealerRevenue()).isEqualTo(0);
    }

    @DisplayName("참가자만 블랙잭일 경우, 딜러의 이익은 참가자들의 배팅금액의 -1.5배이다.")
    @Test
    void findDealerRevenueBlackJack() {
        Participant firstParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.SPACE, Denomination.TEN),
                Card.of(Suit.SPACE, Denomination.ACE))),
                new ParticipantProfile(new Name("배키"), new GameMoney(1000)));
        Participant secondParticipant = new Participant(new Cards(List.of(
                Card.of(Suit.HEART, Denomination.TEN),
                Card.of(Suit.HEART, Denomination.ACE))),
                new ParticipantProfile(new Name("켬미"), new GameMoney(1000)));
        Participants participants = new Participants(List.of(firstParticipant, secondParticipant));

        Dealer dealer = new Dealer(new Cards(List.of(
                Card.of(Suit.CLOVER, Denomination.TEN),
                Card.of(Suit.CLOVER, Denomination.JACK))));

        BlackJack blackJack = new BlackJack(participants, dealer, new CardDeck(Card.createCardDeck()));

        assertThat(blackJack.findDealerRevenue()).isEqualTo(-3000);
    }
}
