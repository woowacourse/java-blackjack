package domain.game;

import domain.card.Card;
import domain.card.Number;
import domain.card.Shape;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackJackGameTest {
    @Test
    @DisplayName("게임 시작 시 플레이어들에게 카드를 2장씩 나눠준다.")
    void whenStartingGame_thenPerPlayerHavingTwoCard() {
        BlackJackGame blackJackGame = new BlackJackGame("여우,아벨", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();

        Map<Player, List<Card>> playersCards = blackJackGame.getPlayersCards();
        assertAll(
                () -> assertThat(playersCards.get(new Dealer()))
                        .containsExactly(new Card(Shape.DIAMOND, Number.JACK), new Card(Shape.DIAMOND, Number.QUEEN)),
                () -> assertThat(playersCards.get(new Participant("여우")))
                        .containsExactly(new Card(Shape.DIAMOND, Number.KING), new Card(Shape.DIAMOND, Number.TEN)),
                () -> assertThat(playersCards.get(new Participant("아벨")))
                        .containsExactly(new Card(Shape.DIAMOND, Number.NINE), new Card(Shape.DIAMOND, Number.EIGHT))
        );
    }

    @Test
    @DisplayName("플레이어에게 한 장을 추가한다.")
    void givenPlayer_thenGivesCard() {
        BlackJackGame blackJackGame = new BlackJackGame("여우,아벨", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();

        blackJackGame.giveCard("여우");

        List<Card> cards = blackJackGame.getCardsFrom("여우");

        assertThat(cards).containsExactly(
                new Card(Shape.DIAMOND, Number.KING),
                new Card(Shape.DIAMOND, Number.TEN),
                new Card(Shape.DIAMOND, Number.SEVEN)
        );
    }

    @Test
    @DisplayName("딜러의 총 점수가 16 이하인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrLessSixTeen() {
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(Shape.HEART, Number.SIX));
        deck.push(new Card(Shape.HEART, Number.FIVE));
        deck.push(new Card(Shape.HEART, Number.EIGHT));
        deck.push(new Card(Shape.HEART, Number.SEVEN));
        BlackJackGame blackJackGame = new BlackJackGame("여우", cards -> deck);
        blackJackGame.giveTwoCardToPlayers();

        assertThat(blackJackGame.shouldDealerGetCard()).isTrue();
    }

    @Test
    @DisplayName("딜러의 총 점수가 17 이상인 지 확인한다.")
    void givenDealerTotalScore_thenChecksOrMoreSevenTeen() {
        Stack<Card> deck = new Stack<>();
        deck.push(new Card(Shape.HEART, Number.SIX));
        deck.push(new Card(Shape.HEART, Number.FIVE));
        deck.push(new Card(Shape.HEART, Number.EIGHT));
        deck.push(new Card(Shape.HEART, Number.NINE));
        BlackJackGame blackJackGame = new BlackJackGame("여우", cards -> deck);
        blackJackGame.giveTwoCardToPlayers();

        assertThat(blackJackGame.shouldDealerGetCard()).isFalse();
    }

    @Test
    @DisplayName("딜러에게 한 장의 카드를 추가한다.")
    void thenGiveDealerCard() {
        BlackJackGame blackJackGame = new BlackJackGame("여우,아벨", cards -> cards);
        blackJackGame.giveTwoCardToPlayers();

        blackJackGame.giveDealerCard();

        assertThat(blackJackGame.getCardsFrom("딜러")).contains(new Card(Shape.DIAMOND, Number.SEVEN));
    }
}