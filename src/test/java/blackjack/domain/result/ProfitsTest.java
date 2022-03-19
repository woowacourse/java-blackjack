package blackjack.domain.result;

import java.util.*;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.player.*;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitsTest {


    @ParameterizedTest
    @CsvSource(value = {"100:-500:400", "100:100:-200", "-100:100:0"}, delimiter = ':')
    @DisplayName("딜러 수익 확인")
    void checkDealerProfit(int firstMoney, int secondMoney, int expected) {
        //플레이어 목록 초기화
        List<Player> playerList = new ArrayList<>();
        Dealer dealer = new Dealer();
        playerList.add(dealer);

        Set<PlayingCard> playingCards = new HashSet<>();
        playingCards.add(new PlayingCard(Suit.SPADE, Denomination.ACE));
        Guest firstGuest = new Guest("guest", new PlayingCards(playingCards), firstMoney);
        Set<PlayingCard> secondPlayingCards = new HashSet<>();
        secondPlayingCards.add(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest secondGuest = new Guest("guest2", new PlayingCards(secondPlayingCards), secondMoney);
        playerList.add(firstGuest);
        playerList.add(secondGuest);

        //플레이어 배팅 금액 초기화
        Players players = new Players(playerList);

        //수익 계산
        Profits profits = new Profits(players);

        assertThat(profits.getPlayersProfit().get(dealer).getValue()).isEqualTo(expected);
    }
}