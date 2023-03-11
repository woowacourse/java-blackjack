package domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import domain.model.Card;
import domain.model.Cards;
import domain.model.Dealer;
import domain.model.Player;
import domain.type.Letter;
import domain.type.Suit;
import domain.vo.Bet;
import domain.vo.Profit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private final BlackJackGame blackJackGame = new BlackJackGame(new CardDistributor(new RandomCardGenerator()),
        new ProfitCalculator());

    @Test
    @DisplayName("플레이어 생성 테스트")
    public void testMakePlayers() {
        //given
        final List<String> names = new ArrayList<>();
        final List<Double> bets = new ArrayList<>();
        final int size = 10;
        for (int i = 0; i < size; i++) {
            names.add("test" + i);
            bets.add((double) (i * 1000));
        }

        //when
        blackJackGame.makePlayers(names, bets);

        //then
        assertThat(blackJackGame.getPlayers().size()).isEqualTo(size);
    }

    @Test
    @DisplayName("플레이어에게 카드 한장 주기 테스트")
    public void testGiveCardToPlayer() {
        //given
        final Cards cards = Cards.makeEmpty();
        final Player player = new Player(cards, "test", Bet.of(5));

        //when
        boolean result = blackJackGame.giveCard(player);

        //then
        assertThat(result).isTrue();
        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 카드를 받을 수 없을 때 카드 주기 테스트")
    public void testGiveCardToPlayerWhenCannotReceiveCard() {
        //given
        final Cards cards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.TEN),
            new Card(Suit.DIAMOND, Letter.TWO)
        ));
        final Player player = new Player(cards, "test", Bet.of(5));

        //when
        boolean result = blackJackGame.giveCard(player);

        //then
        assertThat(result).isFalse();
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("딜러에게 카드 한장 주기 테스트")
    public void testGiveCardToDealer() {
        //given
        final Cards cards = Cards.makeEmpty();
        final Dealer dealer = new Dealer(cards);

        //when
        boolean result = blackJackGame.giveCard(dealer);

        //then
        assertThat(result).isTrue();
        assertThat(dealer.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 카드를 받을 수 없을 때 카드 주기 테스트")
    public void testGiveCardToDealerWhenCannotReceiveCard() {
        //given
        final Cards cards = new Cards(Set.of(
            new Card(Suit.CLUB, Letter.TEN),
            new Card(Suit.SPADE, Letter.SEVEN)
        ));
        final Dealer dealer = new Dealer(cards);

        //when
        boolean result = blackJackGame.giveCard(dealer);

        //then
        assertThat(result).isFalse();
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들 손익 계산 테스트")
    public void testCalculatePlayersProfit() {
        //given
        final Player player = new Player(Cards.makeEmpty(), "test", Bet.of(1000D));
        player.addCard(new Card(Suit.CLUB, Letter.TEN));
        final List<Player> players = List.of(player);
        final Dealer dealer = new Dealer(Cards.makeEmpty());

        //when
        final List<Profit> profits = blackJackGame.calculatePlayersProfit(players, dealer);

        //then
        assertThat(profits.size()).isEqualTo(1);
        assertThat(profits.get(0).getValue()).isEqualTo(1000D);
    }
}
