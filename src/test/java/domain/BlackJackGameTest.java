package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    private static final Name TEST_NAME = new Name("test");
    private static final Player TEST_PLAYER = new Player(TEST_NAME);
    private static final Players TEST_PLAYERS = new Players(List.of(TEST_PLAYER));

    @Test
    @DisplayName("모든 플레이어들에게 카드를 2장씩 나눠준다")
    void initialDealing() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        Assertions.assertThat(blackJackGame.getCardsFromName(TEST_NAME).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("이름이 들어오면 덱에서 해당 플레이어가 카드를 한 장 뽑는다.")
    void drawCardFromName() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        blackJackGame.hitFromName(TEST_NAME, deck);
        Assertions.assertThat(blackJackGame.getCardsFromName(TEST_NAME).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 이름이 존재하지 않을 경우 예외가 발생한다.")
    void drawCardFromNameException() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        blackJackGame.hitFromName(TEST_NAME, deck);
        Assertions.assertThatThrownBy(() -> blackJackGame.hitFromName(new Name("Wrong"), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust가 아니면 false를 반환한다.")
    void isBustFromNameFalse() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Assertions.assertThat(blackJackGame.isBustFromName(TEST_NAME))
                .isFalse();
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust이면 true를 반환한다.")
    void isBustFromNameTrue() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.HEART, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        IntStream.range(0, 3)
                .forEach(ignored -> blackJackGame.hitFromName(TEST_NAME, deck));
        Assertions.assertThat(blackJackGame.isBustFromName(TEST_NAME))
                .isTrue();
    }

    @Test
    @DisplayName("딜러가 17 이상이 될 때까지 카드를 뽑는다.")
    void drawDealerCard() {
        BlackJackGame blackJackGame = new BlackJackGame(TEST_PLAYERS);
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.ACE),
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.CLOVER, CardNumber.TEN));
        blackJackGame.hitDealer(deck);
        Assertions.assertThat(blackJackGame.getDealerScore()).isGreaterThan(16);
    }
}
