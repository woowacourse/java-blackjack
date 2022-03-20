package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import blackjack.domain.card.Card;
import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.GameResult;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackTest {
    @Test
    @DisplayName("카드를 2장씩 나눈다.")
    void divideCard() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        Player ash = new Player("애쉬", initializeBettingMoney());
        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup, new Dealer());
        blackJack.divideCards();

        List<Integer> cardSizes = List.of(pepper.getCardsSize(),
                ash.getCardsSize(),
                blackJack.getDealer().getCardsSize());

        assertThat(cardSizes)
                .containsExactly(2, 2, 2)
                .doesNotContain(0);
    }

    @Test
    @DisplayName("카드가 1장 더 추가되는지 테스트 한다.")
    void addCard() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper));
        BlackJack blackJack = new BlackJack(playerGroup, new Dealer());
        int pepperCardsSize = pepper.getCardsSize();
        blackJack.addCardTo(pepper);

        assertThat(pepper.getCardsSize()).isEqualTo(pepperCardsSize + 1);
    }

    @Test
    @DisplayName("페퍼는 블랙잭으로 승리, 애쉬는 승리, 포비는 무승부, 제이슨은 패배한다.")
    void getGameResult() {
        GameResult gameResult = initializeGameResult();
        Map<String, Integer> profitResults = gameResult.getProfitResults();

        assertThat(profitResults)
                .containsExactly(entry("딜러", -15), entry("페퍼", 15), entry("애쉬", 10), entry("포비", 0), entry("제이슨", -10));
    }

    @Test
    @DisplayName("딜러가 버스트인 경우, 버스트 되지 않은 플레이어만 베팅 금액을 받는다.")
    void getDealerBustGameResult() {
        GameResult gameResult = initializeDealerBustGameResult();
        Map<String, Integer> profitResults = gameResult.getProfitResults();

        assertThat(profitResults)
                .containsExactly(entry("딜러", 0), entry("페퍼", -10), entry("애쉬", 10));
    }

    private GameResult initializeGameResult() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        pepper.addCard(Card.of(CardShape.HEART, CardNumber.QUEEN));
        pepper.addCard(Card.of(CardShape.SPADE, CardNumber.ACE));

        Player ash = new Player("애쉬", initializeBettingMoney());
        ash.addCard(Card.of(CardShape.CLUB, CardNumber.JACK));
        ash.addCard(Card.of(CardShape.SPADE, CardNumber.KING));
        ash.addCard(Card.of(CardShape.SPADE, CardNumber.ACE));

        Player pobi = new Player("포비", initializeBettingMoney());
        pobi.addCard(Card.of(CardShape.CLUB, CardNumber.SEVEN));
        pobi.addCard(Card.of(CardShape.SPADE, CardNumber.KING));

        Player jason = new Player("제이슨", initializeBettingMoney());
        jason.addCard(Card.of(CardShape.CLUB, CardNumber.SEVEN));
        jason.addCard(Card.of(CardShape.SPADE, CardNumber.KING));
        jason.addCard(Card.of(CardShape.SPADE, CardNumber.JACK));

        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash, pobi, jason));

        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.SEVEN));
        dealer.addCard(Card.of(CardShape.CLUB, CardNumber.KING));

        BlackJack blackJack = new BlackJack(playerGroup, dealer);
        return blackJack.getGameResult();
    }

    private GameResult initializeDealerBustGameResult() {
        Player pepper = new Player("페퍼", initializeBettingMoney());
        pepper.addCard(Card.of(CardShape.CLUB, CardNumber.KING));
        pepper.addCard(Card.of(CardShape.SPADE, CardNumber.JACK));
        pepper.addCard(Card.of(CardShape.DIAMOND, CardNumber.JACK));

        Player ash = new Player("애쉬", initializeBettingMoney());
        ash.addCard(Card.of(CardShape.CLUB, CardNumber.SEVEN));
        ash.addCard(Card.of(CardShape.SPADE, CardNumber.KING));

        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));

        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(Card.of(CardShape.CLUB, CardNumber.TEN));
        dealer.addCard(Card.of(CardShape.DIAMOND, CardNumber.JACK));

        BlackJack blackJack = new BlackJack(playerGroup, dealer);
        return blackJack.getGameResult();
    }

    private BettingMoney initializeBettingMoney() {
        return BettingMoney.of(10);
    }
}
