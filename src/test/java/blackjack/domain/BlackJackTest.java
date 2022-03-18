package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.property.CardNumber;
import blackjack.domain.card.property.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayerGroup;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.Match;
import blackjack.domain.result.PlayerResult;

class BlackJackTest {
    @Test
    @DisplayName("카드를 2장씩 나눈다.")
    void divideCard() {
        Player pepper = new Player("페퍼");
        Player ash = new Player("애쉬");
        PlayerGroup playerGroup = new PlayerGroup(Arrays.asList(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup);
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
        Player pepper = new Player("페퍼");
        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper));
        BlackJack blackJack = new BlackJack(playerGroup);
        int pepperCardsSize = pepper.getCardsSize();
        blackJack.addCardTo(pepper);

        assertThat(pepper.getCardsSize()).isEqualTo(pepperCardsSize + 1);
    }

    @Test
    @DisplayName("딜러의 게임 결과가 1승 1패 0무가 나와야 한다.")
    void getDealerResult() {
        BlackJack blackJack = initializeBlackJack();
        GameResult gameResult = blackJack.getGameResult();
        DealerResult dealerResult = gameResult.getDealerResult();
        Map<Match, Integer> dealerResults = dealerResult.getMatchResult();

        assertThat(dealerResults)
                .containsExactly(entry(Match.WIN, 1), entry(Match.LOSE, 1), entry(Match.DRAW, 0));
    }

    @Test
    @DisplayName("페퍼는 승리, 애쉬는 패배해야 한다.")
    void getPlayerResult() {
        BlackJack blackJack = initializeBlackJack();
        GameResult gameResult = blackJack.getGameResult();
        PlayerResult playerResult = gameResult.getPlayerResult();
        Map<String, Match> playerResults = playerResult.get();
        assertThat(playerResults)
                .containsExactly(entry("페퍼", Match.WIN), entry("애쉬", Match.LOSE));
    }

    @Test
    @DisplayName("페퍼는 bust로 패배, 애쉬도 패배해야 한다.")
    void getBustPlayerResult() {
        GameResult gameResult = initializeBustGameResult();
        PlayerResult playerResult = gameResult.getPlayerResult();
        Map<String, Match> playerResults = playerResult.get();
        assertThat(playerResults)
                .containsExactly(entry("페퍼", Match.LOSE), entry("애쉬", Match.LOSE));
    }

    @Test
    @DisplayName("딜러는 10000, 페퍼는 10000, 애쉬 -20000 의 수익을 얻어야한다.")
    void finalProfitTest() {
        BlackJack blackJack = initializeBlackJack();

        Dealer dealer = blackJack.getDealer();
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(new Card(CardShape.CLUB, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        ProfitResult profitResult = blackJack.getProfitResult();
        Map<String, Integer> profitResults = profitResult.get();
        List<Integer> results = List.of(profitResults.get(dealer.getName()),
            profitResults.get("페퍼"),
            profitResults.get("애쉬"));

        assertThat(results)
            .containsExactly(10000, 10000, -20000);
    }

    private BlackJack initializeBlackJack() {
        Player pepper = new Player("페퍼");
        pepper.addCard(new Card(CardShape.HEART, CardNumber.THREE));
        pepper.addCard(new Card(CardShape.SPADE, CardNumber.EIGHT));
        pepper.addCard(new Card(CardShape.CLUB, CardNumber.Q));
        pepper.addMoney(10000);

        Player ash = new Player("애쉬");
        ash.addCard(new Card(CardShape.CLUB, CardNumber.SEVEN));
        ash.addCard(new Card(CardShape.SPADE, CardNumber.K));
        ash.addMoney(20000);

        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup);

        Dealer dealer = blackJack.getDealer();
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(new Card(CardShape.CLUB, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        return blackJack;
    }

    private GameResult initializeBustGameResult() {
        Player pepper = new Player("페퍼");
        pepper.addCard(new Card(CardShape.CLUB, CardNumber.K));
        pepper.addCard(new Card(CardShape.SPADE, CardNumber.J));
        pepper.addCard(new Card(CardShape.DIAMOND, CardNumber.J));

        Player ash = new Player("애쉬");
        ash.addCard(new Card(CardShape.CLUB, CardNumber.SEVEN));
        ash.addCard(new Card(CardShape.SPADE, CardNumber.K));

        PlayerGroup playerGroup = new PlayerGroup(List.of(pepper, ash));
        BlackJack blackJack = new BlackJack(playerGroup);

        Dealer dealer = blackJack.getDealer();
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        dealer.addCard(new Card(CardShape.CLUB, CardNumber.NINE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        return blackJack.getGameResult();
    }
}
