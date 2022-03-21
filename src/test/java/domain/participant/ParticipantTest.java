package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class ParticipantTest {

    Card card1 = Card.getCard(Rank.RANK_K, Suit.HEART);
    Card card2 = Card.getCard(Rank.RANK_A, Suit.SPADE);
    Card card3 = Card.getCard(Rank.RANK_A, Suit.CLOVER);
    Card card4 = Card.getCard(Rank.RANK_8, Suit.CLOVER);

    @Test
    @DisplayName("자신의 손패를 문자열로 반환하는 기능")
    void showHand() {
        // when
        Participant participant = new Player(new Name("pobi"), new ArrayList<>(List.of(card1, card2)));

        // then
        assertThat(participant.showHand()).isEqualTo("K♥️, A♠️");
    }

    @Test
    @DisplayName("손패에 새로운 카드를 추가하는 기능")
    void addCard() {
        // given
        Participant participant = new Player(new Name("pobi"), new ArrayList<>(List.of(card1, card2)));

        // when
        participant.addCard(card4);

        // then
        assertThat(participant.showHand()).isEqualTo("K♥️, A♠️, 8♣️");
    }

    @ParameterizedTest(name = "손패가 버스트 되었는지 확인하는 기능 - case : {0}")
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RANK_A"})
    void isBurst(Rank rank) {
        // given
        Card card = Card.getCard(rank, Suit.HEART);

        // when
        Participant participant = new Player(new Name("pobi"), new ArrayList<>(List.of(card, card1, card1)));

        // then
        assertThat(participant.isBust()).isTrue();
    }

    @ParameterizedTest(name = "손패가 블랙잭인지 확인하는 기능 - case : {0}, {1}, {2}")
    @CsvSource(value = {"RANK_A, RANK_A, RANK_9", "RANK_K, RANK_Q, RANK_A", "RANK_K, RANK_8, RANK_3"})
    void isBlackJack(String input1, String input2, String input3) {
        // given
        Card card1 = Card.getCard(Rank.valueOf(input1), Suit.HEART);
        Card card2 = Card.getCard(Rank.valueOf(input2), Suit.SPADE);
        Card card3 = Card.getCard(Rank.valueOf(input3), Suit.CLOVER);

        // when
        Participant participant = new Player(new Name("pobi"), new ArrayList<>(List.of(card1, card2, card3)));

        // then
        assertThat(participant.isUpperBoundScore()).isTrue();
    }

    @Test()
    @DisplayName("베스트 스코어 계산하는 기능")
    void getBestScore() {
        // when
        Participant participant = new Player(
                new Name("pobi"), new ArrayList<>(List.of(card1, card2, card3, card4))
        );

        // then
        assertThat(participant.calculateBestScore()).isEqualTo(20);
    }
}
