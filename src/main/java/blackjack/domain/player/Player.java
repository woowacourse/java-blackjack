package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.GameStatus;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private final Cards cards;
    private GameStatus gameStatus;

    private Player(final String name, final Cards cards, final GameStatus gameStatus) {
        Objects.requireNonNull(name, "이름이 null이 들어올 수 없습니다.");
        validateBlankName(name);
        this.name = name;
        this.cards = cards;
        this.gameStatus = gameStatus;
    }

    public static Player createNewPlayer(final String name, final List<Card> cards) {
        return new Player(name, new Cards(cards), GameStatus.RUNNING);
    }

    private void validateBlankName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름이 공백이 들어올 수 없습니다.");
        }
    }

    public void changeStatusFinished() {
        validateFinishedStatus();
        gameStatus = GameStatus.FINISHED;
    }

    private void validateFinishedStatus() {
        if (gameStatus.isFinishedGame()) {
            throw new IllegalStateException("이미 종료된 게임은 종료요청을 할 수 없습니다.");
        }
    }

    public List<Card> firstDrawCards() {
        return List.copyOf(cards.cards().subList(0, 2));
    }
}
