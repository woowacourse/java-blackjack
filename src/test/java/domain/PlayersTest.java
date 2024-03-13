package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("플레이어들은 플레이어 리스트로 생성 된다.")
    void createPlayers() {
        List<Player> players = List.of(new Player(new Name("test1")), new Player(new Name("test2")));
        Assertions.assertThatCode(() -> new Players(players)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("플레이어가 8명을 초과하면 예외를 발생한다.")
    void validatePlayerCountException() {
        List<Player> players = IntStream.range(0, 9)
                .mapToObj(number -> new Player(new Name("test%d".formatted(number))))
                .collect(Collectors.toList());

        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최대 8명 입니다.");
    }

    @Test
    @DisplayName("플레이어 이름 중 \"딜러\"가 있으면 예외를 발생한다.")
    void validatePlayerNameException() {
        List<Player> players = List.of(new Player(new Name("딜러")), new Player(new Name("test2")));
        Assertions.assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름을 \"딜러\"로 생성할 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어의 이름이 중복되면 예외를 발생한다.")
    void validateDuplicatedNameException() {
        Name test = new Name("test");
        Player expected = new Player(test);

        Assertions.assertThatThrownBy(() -> new Players(List.of(expected, expected)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어 전체에게 설정한 개수만큼 카드를 나눠줄 수 있다.")
    void pickCardsToPlayer() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withFullCards();

        players.pickCardsToPlayer(deck, 2);

        List<Card> cards = player.getCards();
        Assertions.assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("존재하는 플레이어 이름으로 덱에서 해당 플레이어가 카드를 한 장 뽑는다.")
    void hitFromName() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withFullCards();

        players.hitFromName(name, deck);

        Assertions.assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("입력으로 받은 이름이 존재하지 않을 경우 예외를 발생한다.")
    void hitFromNameException() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withFullCards();
        Assertions.assertThatThrownBy(() -> players.hitFromName(new Name("wrong"), deck))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("존재하는 플레이어 이름으로 카드를 반환할 수 있다.")
    void getCardsFromName() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Card card = new Card(CardType.CLOVER, CardNumber.ACE);
        Deck deck = Deck.withCustomCards(card);

        players.hitFromName(name, deck);
        List<Card> cards = players.getCardsFromName(name);

        Assertions.assertThat(cards).isEqualTo(List.of(card));
    }

    @Test
    @DisplayName("카드를 반환할 때 입력으로 받은 이름이 존재하지 않을 경우 예외를 발생한다.")
    void getCardsFromNameException() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Card card = new Card(CardType.CLOVER, CardNumber.ACE);
        Deck deck = Deck.withCustomCards(card);

        players.hitFromName(name, deck);

        Assertions.assertThatThrownBy(() -> players.getCardsFromName(new Name("wrong")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust가 아니면 false를 반환한다.")
    void isBustFromNameFalse() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Assertions.assertThat(players.isBustFromName(name)).isFalse();
    }

    @Test
    @DisplayName("입력으로 받은 이름이 bust이면 true를 반환한다.")
    void isBustFromNameTrue() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.HEART, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        IntStream.range(0, 3)
                .forEach(ignored -> players.hitFromName(name, deck));
        Assertions.assertThat(players.isBustFromName(name))
                .isTrue();
    }

    @Test
    @DisplayName("Bust인지 확인할 때 입력으로 받은 이름이 존재하지 않을 경우 예외를 발생한다.")
    void isBustFromNameException() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN),
                new Card(CardType.HEART, CardNumber.TEN),
                new Card(CardType.DIAMOND, CardNumber.TEN));
        Assertions.assertThatThrownBy(() -> players.isBustFromName(new Name("wrong")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("입력으로 받은 이름으로 점수를 반환할 수 있다.")
    void getTotalScore() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN));
        players.hitFromName(name, deck);
        Assertions.assertThat(players.getTotalScore(name)).isEqualTo(10);
    }

    @Test
    @DisplayName("점수를 반환할 때 입력으로 받은 이름이 존재하지 않을 경우 예외를 발생한다.")
    void getTotalScoreException() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Assertions.assertThatThrownBy(() -> players.getTotalScore(new Name("wrong")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 참여자 입니다.");
    }

    @Test
    @DisplayName("플레이어 별 점수를 반환할 수 있다.")
    void getPlayersTotalScores() {
        Name name = new Name("test");
        Player player = new Player(name);
        Players players = new Players(List.of(player));
        Deck deck = Deck.withCustomCards(
                new Card(CardType.CLOVER, CardNumber.TEN));
        players.hitFromName(name, deck);
        Map<Name, Integer> playersTotalScores = players.getPlayersTotalScores();
        int score = playersTotalScores.get(name);
        Assertions.assertThat(score).isEqualTo(10);
    }
}