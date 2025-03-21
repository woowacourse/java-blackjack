package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.game.Participants;
import blackjack.domain.participant.Players;
import blackjack.exception.ErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantsTest {

    @Test
    @DisplayName("이름이 중복되는 참여자 예외 테스트")
    void duplicateParticipantNamesTest() {
        // given
        String name = "pobi,jason,pobi";
        List<String> names = Arrays.stream(name.split(",")).toList();
        // when & then
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR] 참여자 이름은 중복될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"pobi,THREE,true", "jason,ACE,true", "neo,KING,false"})
    @DisplayName("플레이어의 카드 추가 가능 여부 테스트")
    void canHitTest(String name, Rank rankName, boolean expected) {
        // given
        Participants participants = new Participants(List.of("pobi", "jason", "neo"));
        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        participants.addInitialCards(cards);
        participants.addCardTo(name, new Card(rankName, Suit.HEART));
        participants.addCardTo("neo", new Card(Rank.THREE, Suit.HEART));

        // when
        boolean actual = participants.isAbleToAddCard(name);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"JACK,SIX,true", "JACK,SEVEN,false", "JACK,ACE,false"})
    @DisplayName("딜러의 카드 추가 가능 여부 확인 테스트")
    void canHitDealerTest(Rank rank1, Rank rank2, boolean expected) {
        // given
        Participants participants = new Participants(List.of("pobi", "jason", "neo"));
        participants.hitDealer(new Card(rank1, Suit.HEART));
        participants.hitDealer(new Card(rank2, Suit.HEART));

        // when
        boolean actual = participants.canHitDealer();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"pobi,THREE,10000,-10000,30000", "jason,FIVE,20000,0,40000", "neo,SEVEN,30000,30000,30000"})
    @DisplayName("참여자들의 수익 계산 기능 테스트")
    void calculateProfitsTest(String name, Rank rankName, int bet, int playerProfit, int dealerProfit) {
        // given
        Players players = Players.of(List.of("pobi", "jason", "neo"));
        players.initializeBet((nickname) -> bet);
        Participants participants = new Participants(players);

        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        participants.addInitialCards(cards);

        participants.addCardTo(name, new Card(rankName, Suit.HEART));
        participants.hitDealer(new Card(Rank.FIVE, Suit.HEART));

        // when
        Map<String, Integer> profits = participants.calculatePlayerProfits();
        int actualPlayerProfit = profits.get(name);
        int actualDealerProfit = participants.calculateDealerProfit();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(actualPlayerProfit).isEqualTo(playerProfit);
            softly.assertThat(actualDealerProfit).isEqualTo(dealerProfit);
        });
    }
}
