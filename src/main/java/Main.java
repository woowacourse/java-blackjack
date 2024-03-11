import controller.BlackjackController;
import domain.blackjack.Dealer;
import domain.blackjack.HoldingCards;
import domain.blackjack.Player;
import domain.card.Deck;
import java.util.List;
import view.NameInputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        Dealer dealer = Dealer.of(HoldingCards.of());
        OutputView.print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<Player> players = NameInputView.getNames().stream()
                .map(name -> Player.from(name, HoldingCards.of()))
                .toList();
        BlackjackController blackjackController = new BlackjackController(dealer, players);
        blackjackController.startBlackjackGame(Deck.fullDeck());
    }
}
