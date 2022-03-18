package blackjack.domain.result;

import blackjack.domain.card.Denomination;
import blackjack.domain.card.PlayingCard;
import blackjack.domain.card.PlayingCards;
import blackjack.domain.card.Suit;
import blackjack.domain.player.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersBetMoneyTest {

    @ParameterizedTest
    @CsvSource(value = {"100:-500:-400", "100:100:200", "-100:100:0"}, delimiter = ':')
    @DisplayName("딜러 돈 합산 확인")
    void checkInitDealerMoney(int firstMoney, int secondMoney, int expected) {
        List<Player> playerList = new ArrayList<>();
        Dealer dealer = new Dealer();
        playerList.add(dealer);

        PlayingCards firstPlayingCards = new PlayingCards();
        firstPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest firstGuest = new Guest("guest", firstPlayingCards);

        PlayingCards secondPlayingCards = new PlayingCards();
        secondPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.ACE));
        Guest secondGuest = new Guest("guest2", secondPlayingCards);
        playerList.add(firstGuest);
        playerList.add(secondGuest);
        Players players = new Players(playerList);

        Map<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        playersMoney.put(dealer, new BetMoney(0));
        playersMoney.put(firstGuest, new BetMoney(firstMoney));
        playersMoney.put(secondGuest, new BetMoney(secondMoney));
        PlayersBetMoney playersBetMoney = new PlayersBetMoney(playersMoney);
        playersBetMoney.calcDealerMoney(players);

        assertThat(playersBetMoney.getPlayersMoney().get(dealer).getMoney()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"100:200", "50:100",}, delimiter = ':')
    @DisplayName("플레이어 일반 우승 돈 확인")
    void checkPlayerWinMoney(int firstMoney, int expected) {
        List<Player> playerList = new ArrayList<>();
        PlayingCards firstPlayingCards = new PlayingCards();
        firstPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest firstGuest = new Guest("guest", firstPlayingCards);
        playerList.add(firstGuest);

        Map<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        playersMoney.put(firstGuest, new BetMoney(firstMoney));
        PlayersBetMoney playersBetMoney = new PlayersBetMoney(playersMoney);

        playersBetMoney.getPlayersMoney().get(firstGuest).plusMoney();
        assertThat(playersBetMoney.getPlayersMoney().get(firstGuest).getMoney()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"100:-200", "50:-100",}, delimiter = ':')
    @DisplayName("플레이어 일반 패배 돈 확인")
    void checkPlayerLoseMoney(int firstMoney, int expected) {
        List<Player> playerList = new ArrayList<>();
        PlayingCards firstPlayingCards = new PlayingCards();
        firstPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest firstGuest = new Guest("guest", firstPlayingCards);
        playerList.add(firstGuest);

        Map<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        playersMoney.put(firstGuest, new BetMoney(firstMoney));
        PlayersBetMoney playersBetMoney = new PlayersBetMoney(playersMoney);

        playersBetMoney.getPlayersMoney().get(firstGuest).minusMoney();
        assertThat(playersBetMoney.getPlayersMoney().get(firstGuest).getMoney()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"100:250", "50:125",}, delimiter = ':')
    @DisplayName("플레이어 블랙잭 우승 돈 확인")
    void checkPlayerBlackjackMoney(int firstMoney, int expected) {
        List<Player> playerList = new ArrayList<>();
        PlayingCards firstPlayingCards = new PlayingCards();
        firstPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest firstGuest = new Guest("guest", firstPlayingCards);
        playerList.add(firstGuest);

        Map<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        playersMoney.put(firstGuest, new BetMoney(firstMoney));
        PlayersBetMoney playersBetMoney = new PlayersBetMoney(playersMoney);

        playersBetMoney.getPlayersMoney().get(firstGuest).plusBlackjackMoney();
        assertThat(playersBetMoney.getPlayersMoney().get(firstGuest).getMoney()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"100:-250", "50:-125",}, delimiter = ':')
    @DisplayName("플레이어 블랙잭 패배 돈 확인")
    void checkPlayerBlackjackLoseMoney(int firstMoney, int expected) {
        List<Player> playerList = new ArrayList<>();
        PlayingCards firstPlayingCards = new PlayingCards();
        firstPlayingCards.addCard(new PlayingCard(Suit.SPADE, Denomination.TWO));
        Guest firstGuest = new Guest("guest", firstPlayingCards);
        playerList.add(firstGuest);

        Map<Player, BetMoney> playersMoney = new LinkedHashMap<>();
        playersMoney.put(firstGuest, new BetMoney(firstMoney));
        PlayersBetMoney playersBetMoney = new PlayersBetMoney(playersMoney);

        playersBetMoney.getPlayersMoney().get(firstGuest).minusBlackjackMoney();
        assertThat(playersBetMoney.getPlayersMoney().get(firstGuest).getMoney()).isEqualTo(expected);
    }
}
