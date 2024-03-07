import controller.BlackjackController;
import domain.Deck;
import domain.Gamer;
import domain.HoldingCards;
import java.util.List;
import view.NameInputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        Gamer dealer = new Gamer("딜러", HoldingCards.of());
        OutputView.print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<Gamer> players = NameInputView.getNames().stream()
                .map(name -> new Gamer(name, HoldingCards.of()))
                .toList();
        BlackjackController blackjackController = new BlackjackController(dealer, players);
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
