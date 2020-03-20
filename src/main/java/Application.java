import controller.BlackjackGame;
import domain.UserInterface;
import domain.card.Deck;
import domain.result.DefaultMatchRule;
import domain.result.MatchRule;
import infra.repository.SingleDeck;
import ui.Console;

public class Application {
    public static void main(String[] args) {
        BlackjackGame blackjackGame = setupBlackjackGame();
        try {
            blackjackGame.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 종료합니다 : %s", e.getMessage()));
            System.exit(-1);
        }

    }

    private static BlackjackGame setupBlackjackGame() {
        Deck deck = SingleDeck.setUp();
        MatchRule matchRule = new DefaultMatchRule();
        UserInterface userInterface = new Console();
        return new BlackjackGame(deck, matchRule, userInterface);
    }
}