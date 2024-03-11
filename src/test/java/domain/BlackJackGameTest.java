package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {
    @Test
    @DisplayName("모든 플레이어들에게 카드를 2장씩 나눠준다")
    void initialDealing() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Player(name)));
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        Assertions.assertThat(blackJackGame.getCardsFromName(name).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("이름이 들어오면 덱에서 해당 플레이어가 카드를 한 장 뽑는다.")
    void drawCardFromName() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(
                new Player(name)
        ));
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        blackJackGame.hitFromName(name, deck);
        Assertions.assertThat(blackJackGame.getCardsFromName(name).size()).isEqualTo(3);
    }

    @Test
    @DisplayName("플레이어 이름이 존재하지 않을 경우 예외가 발생한다.")
    void drawCardFromNameException() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Player(name)));
        Deck deck = Deck.withFullCards();
        blackJackGame.initialDealing(deck);
        blackJackGame.hitFromName(name, deck);
        Assertions.assertThatThrownBy(() -> blackJackGame.hitFromName(new Name("Wrong"), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust가 아니면 false를 반환한다.")
    void isBustFromNameFalse() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Player(name)));
        Assertions.assertThat(blackJackGame.isBustFromName(name))
                .isFalse();
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust이면 true를 반환한다.")
    void isBustFromNameTrue() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Player(name)));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.HEART, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        IntStream.range(0, 3)
                .forEach(ignored -> blackJackGame.hitFromName(name, deck));
        Assertions.assertThat(blackJackGame.isBustFromName(name))
                .isTrue();
    }

    @Test
    @DisplayName("딜러가 17 이상이 될 때까지 카드를 뽑는다.")
    void drawDealerCard() {
        Name name = new Name("test");
        BlackJackGame blackJackGame = new BlackJackGame(List.of(new Player(name)));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.ACE),
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.CLOVER, CardNumber.TEN));
        blackJackGame.hitDealer(deck);
        Assertions.assertThat(blackJackGame.getDealerScore()).isGreaterThan(16);
    }

    @Test
    @DisplayName("플레이어가 8명을 초과하면 예외를 발생한다.")
    void validatePlayerCountException() {
        List<Player> gamers = IntStream.range(0, 9)
                .mapToObj(number -> new Player(new Name("test%d".formatted(number))))
                .collect(Collectors.toList());

        Assertions.assertThatThrownBy(() -> new BlackJackGame(gamers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최대 8명 입니다.");
    }
}
