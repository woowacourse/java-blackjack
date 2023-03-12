package domain.participant;

import domain.card.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantsTest {

    @ParameterizedTest
    @ValueSource(strings = {"가비", "연어,가비,애쉬,우가,럿고,비버,아코"})
    void 플레이어_수는_1에서_7명이다(String names) {
        //given

        //when

        //then
        assertDoesNotThrow(() -> Participants.of(List.of(names.split(",")), Deck.create(notShuffle())));
    }

    @ParameterizedTest
    @ValueSource(strings = {",", "연어,가비,애쉬,우가,럿고,비버,아코,네오"})
    void 플레이어_수는_1에서_7명이_아니면_예외가_발생한다(String names) {
        //given

        //when

        //then
        assertThatThrownBy(() -> Participants.of(List.of(names.split(",")), Deck.create(notShuffle())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 수는 1 ~ 7 이내여야 합니다");
    }

    @Test
    void 중복된_이름을_입력하면_예외가_발생한다() {
        //given
        List<String> names = List.of("도비", "   도비");

        //when

        //then
        assertThatThrownBy(() -> Participants.of(names, Deck.create(notShuffle())))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어의 이름은 중복될 수 없습니다");
    }

    @Test
    void 모든_참여자들은_카드_두_장을_받고_시작한다() {
        //given
        List<String> players = List.of("연어", "가비");

        //when
        Participants participants = Participants.of(players, Deck.create(notShuffle()));

        //then
        assertThat(participants.getDealer().getCards()).hasSize(2);
        assertThat(participants.getPlayers())
                .map(Participant::getCards)
                .allMatch(cards -> cards.size() == 2);
    }

    @Test
    void 플레이어가_버스트이면_다음_차례는_없다() throws Exception {
        //given
        List<String> players = List.of("연어");
        Participants participants = Participants.of(players, Deck.create(notShuffle()));

        //when
        participants.getPlayers().get(0).addCard(new Card(Rank.TEN, Suit.HEART));
        participants.getPlayers().get(0).addCard(new Card(Rank.TEN, Suit.HEART));

        //then
        assertThat(participants.getNextTurnPlayer()).isEmpty();
    }

    @Test
    void 플레이어가_스탠드이면_다음_차례는_없다() throws Exception {
        //given
        List<String> players = List.of("연어");
        Participants participants = Participants.of(players, Deck.create(notShuffle()));

        //when
        participants.getPlayers().get(0).stand();

        //then
        assertThat(participants.getNextTurnPlayer()).isEmpty();
    }

    @Test
    void 플레이어가_버스트가_아니고_스탠드도_아니면_다음_차례가_있다() throws Exception {
        //given
        List<String> players = List.of("연어");
        Participants participants = Participants.of(players, Deck.create(notShuffle()));

        //when

        //then
        assertThat(participants.getNextTurnPlayer()).isPresent();
    }

    @Test
    void 딜러의_턴이_지나면_딜러는_무조건_16_초과이다() throws Exception {
        //given
        List<String> players = List.of("연어");
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(players, deck);

        //when
        participants.playDealerTurn(deck);

        //then
        assertThat(participants.getDealer().calculateScore()).isGreaterThan(16);
    }

    @Test
    void 딜러가_버스트이면_스탠드_상태이다() throws Exception {
        //given
        String player = "연어";
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of(player), deck);

        //when
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.DIAMOND));
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.CLUB));

        //then
        assertThat(participants.isDealerStand()).isTrue();
    }

    @Test
    void 딜러가_17_21_사이면_스탠드_상태이다() throws Exception {
        //given
        String player = "연어";
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of(player), deck);

        //when
        participants.getDealer().addCard(new Card(Rank.FIVE, Suit.DIAMOND));

        //then
        assertThat(participants.isDealerStand()).isTrue();
    }

    @Test
    void 딜러가_16_이하면_스탠드_상태가_아니다() throws Exception {
        //given
        String player = "연어";
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of(player), deck);

        //when
        participants.getDealer().addCard(new Card(Rank.FOUR, Suit.DIAMOND));

        //then
        assertThat(participants.isDealerStand()).isFalse();
    }

    @Test
    void 딜러_버스트이면_모든_플레이어_승리() throws Exception {
        //given
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.DIAMOND));
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.CLUB));

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(1000);
    }

    @Test
    void 딜러_버스트X_플레이어_버스트() throws Exception {
        //given
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when
        participants.getPlayers().get(0).addCard(new Card(Rank.TEN, Suit.DIAMOND));
        participants.getPlayers().get(0).addCard(new Card(Rank.TEN, Suit.CLUB));

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(-1000);
    }

    @Test
    void 딜러_버스트X_플레이어와_동점() throws Exception {
        //given
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(0);
    }

    @Test
    void 플레이어가_딜러보다_크고_블랙잭() throws Exception {
        //given
        final Deck deck = Deck.create(makePlayerBlackjack());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(1500);
    }

    @Test
    void 딜러가_버스트이고_플레이어가_블랙잭() throws Exception {
        //given
        final Deck deck = Deck.create(makePlayerBlackjack());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.DIAMOND));
        participants.getDealer().addCard(new Card(Rank.TEN, Suit.CLUB));

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(1500);
    }

    @Test
    void 플레이어_버스트X_딜러보다_클_때() throws Exception {
        //given
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when
        participants.getPlayers().get(0).addCard(new Card(Rank.NINE, Suit.DIAMOND));

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(1000);
    }

    @Test
    void 플레이어_버스트X_딜러보다_작을_때() throws Exception {
        //given
        final Deck deck = Deck.create(notShuffle());
        Participants participants = Participants.of(List.of("연어"), deck);
        participants.getPlayers().forEach(player -> player.bet(1000));

        //when
        participants.getPlayers().get(0).addCard(new Card(Rank.TEN, Suit.DIAMOND));

        //then
        assertThat(participants.getParticipantsResult().get("연어")).isEqualTo(0);
    }

    private ShuffleStrategy notShuffle() {
        return cards -> {
        };
    }

    private ShuffleStrategy makePlayerBlackjack() {
        return cards -> {
            cards.set(1, new Card(Rank.JACK, Suit.DIAMOND));
        };
    }
}
