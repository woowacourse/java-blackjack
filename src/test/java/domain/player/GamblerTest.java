package domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.MatchResult;
import domain.StubDeck;
import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import exception.BlackjackException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GamblerTest {
    @Test
    @DisplayName("이름이 숫자면 안된다.")
    void 이름이_숫자일_시() {
        //given
        String name = "121345";

        //when & then
        assertThatThrownBy(() -> new Gambler(name, 1000))
                .isInstanceOf(BlackjackException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"h", "tobiisverygoob"})
    @DisplayName("이름 길이가 범위를 벗어나면 예외가 발생한다.")
    void 이름_길이_검증(String name) {
        // given
        int bettingMoney = 1000;

        // when & then
        assertThatThrownBy(() -> new Gambler(name, bettingMoney))
                .isInstanceOf(BlackjackException.class);
    }

    @Test
    @DisplayName("승리 정상 판정")
    void 승리_정상_판정() {
        //given
        Dealer dealer = new Dealer();
        Gambler tobi = new Gambler("tobi", 1000);
        Gambler quda = new Gambler("quda", 1000);

        Card jack = new Card(CardRank.JACK, CardSuit.CLOVER); // 딜러
        Card eight = new Card(CardRank.EIGHT, CardSuit.DIAMOND); // tobi
        Card ten = new Card(CardRank.TEN, CardSuit.CLOVER); // quda

        StubDeck sd = new StubDeck(List.of(jack, eight, ten));
        dealer.deal(sd);
        tobi.deal(sd);
        quda.deal(sd);

        //when
        long tobiResult = tobi.calculateReward(MatchResult.of(tobi, dealer).getRate());
        long qudaResult = quda.calculateReward(MatchResult.of(quda, dealer).getRate());

        //then
        assertThat(tobiResult).isEqualTo(-1000L);
        assertThat(qudaResult).isEqualTo(0L);
    }

    @Test
    @DisplayName("도박사의 첫 카드 오픈은 2장이어야 한다.")
    void 도박사_오픈_카드_2장() {
        // given
        Gambler gambler = new Gambler("tobi", 10000);

        Card card = new Card(CardRank.TEN, CardSuit.CLOVER);
        StubDeck deck = new StubDeck(List.of(card, card, card));
        gambler.deal(deck);
        gambler.deal(deck);
        gambler.deal(deck);

        // when
        List<String> result = gambler.getOpenCards();

        // then
        assertThat(result).hasSize(2);
    }
}
