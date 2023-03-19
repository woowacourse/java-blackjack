package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Denomination;
import domain.card.Suit;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Participants는 ")
class ParticipantsTest {

    @ParameterizedTest(name = "player가 총 1~7명 일때만 생성된다 name = {0}")
    @ValueSource(strings = {"가비", "연어,가비,애쉬,우가,럿고,비버,아코"})
    void player_가_총_1에서_7명이다(String names) {
        //given

        //when

        //then
        assertDoesNotThrow(() -> Participants.of(List.of(names.split(","))));
    }

    @ParameterizedTest(name = "player가 총 1~7명이 아니면 예외가 발생한다 name = {0}")
    @ValueSource(strings = {",", "연어,가비,애쉬,우가,럿고,비버,아코,네오"})
    void player_가_총_1에서_7명이_아니면_예외가_발생한다(String names) {
        //given

        //when

        //then
        assertThatThrownBy(() -> Participants.of(List.of(names.split(","))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1 ~ 7 이내여야 합니다");
    }

    @Test
    void 중복된_이름을_입력하면_예외가_발생한다() {
        //given
        List<String> names = List.of("도비", "도비");

        //when

        //then
        assertThatThrownBy(() -> Participants.of(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 중복될 수 없습니다");
    }

    @Test
    void 카드_두_장을_받고_시작한다() {
        //given
        List<String> players = List.of("연어", "가비");
        Participants participants = Participants.of(players);

        //when
        participants.initHand(Deck.create());

        //then
        assertThat(participants.getDealer().getCards()).hasSize(2);
        assertThat(participants.getPlayers())
                .map(Participant::getCards)
                .allMatch(cards -> cards.size() == 2);
    }

    @Test
    void 딜러보다_점수가_크다면_배팅_금만큼_수익이다() {
        // given
        Participants participants = Participants.of(List.of("둘리"));
        Player player = participants.getPlayers().get(0);
        Card card = new Card(Denomination.NINE, Suit.SPADE);
        player.addCard(card);

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 0 vs 둘리 9 -> 딜러 승
                Map.entry("둘리", 10_000)
        );
    }

    @Test
    void 딜러와_점수가_같다면_수익은_없다() {
        // given
        Participants participants = Participants.of(List.of("둘리"));
        Player player = participants.getPlayers().get(0);
        Card card = new Card(Denomination.NINE, Suit.SPADE);
        participants.getDealer().addCard(card);
        player.addCard(card);

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 9 vs 둘리 9 -> 무승부
                Map.entry("둘리", 0)
        );
    }

    @Test
    void 딜러보다_점수가_낮으면_배팅금액만큼_잃는다() {
        // given
        Participants participants = Participants.of(List.of("패배자이름"));
        Dealer dealer = participants.getDealer();
        Player player = participants.getPlayers().get(0);
        dealer.addCard(new Card(Denomination.NINE, Suit.SPADE));
        player.addCard(new Card(Denomination.TWO, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 9 vs 패배자 1
                Map.entry("패배자이름", -10_000)
        );
    }

    @Test
    void 플레이어_카드_합이_블랙잭이면_150_퍼센트_수익이다() {
        // given
        Participants participants = Participants.of(List.of("둘리"));
        Player player = participants.getPlayers().get(0);
        player.addCard(new Card(Denomination.ACE, Suit.SPADE));
        player.addCard(new Card(Denomination.TEN, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 0 vs 둘리 21
                Map.entry("둘리", 15_000)
        );
    }

    @Test
    void 딜러가_버스트면_플레이어는_본인패와_상관없이_수익이다() {
        // given
        Participants participants = Participants.of(List.of("포비"));
        Player player = participants.getPlayers().get(0);
        Dealer dealer = participants.getDealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.SPADE));
        dealer.addCard(new Card(Denomination.TEN, Suit.SPADE));
        dealer.addCard(new Card(Denomination.QUEEN, Suit.SPADE));
        player.addCard(new Card(Denomination.JACK, Suit.SPADE));
        player.addCard(new Card(Denomination.TEN, Suit.SPADE));
        player.addCard(new Card(Denomination.QUEEN, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 버스트 vs 플레이어 버스트
                Map.entry("포비", 10_000)
        );
    }

    @Test
    void 딜러와_플레이어_동시에_블랙잭이면_수익은_없다() {
        // given
        Participants participants = Participants.of(List.of("포비"));
        Player player = participants.getPlayers().get(0);
        Dealer dealer = participants.getDealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.SPADE));
        dealer.addCard(new Card(Denomination.ACE, Suit.SPADE));
        player.addCard(new Card(Denomination.JACK, Suit.SPADE));
        player.addCard(new Card(Denomination.ACE, Suit.SPADE));

        // when
        player.betPlayer(10_000);

        // then
        assertThat(participants.getPlayerBettingResult()).contains(
                // 딜러 블랙잭 vs 플레이어 블랙잭
                Map.entry("포비", 0)
        );
    }
}
