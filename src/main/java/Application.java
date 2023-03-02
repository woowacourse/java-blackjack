import domain.Card;
import domain.CardNumberGenerator;
import domain.Cards;
import domain.CardBox;
import domain.Dealer;
import domain.Name;
import domain.Player;
import domain.RandomCardNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

public class Application {

    private static InputView inputView = new InputView();
    private static OutputView outputView = new OutputView();
    private static CardBox cardBox = new CardBox();
    private static CardNumberGenerator cardNumberGenerator = new RandomCardNumberGenerator();

    public static void main(String[] args) {
        BlackJackGame game = new BlackJackGame(
                new InputView(),
                new OutputView(),
                new CardBox(),
                new RandomCardNumberGenerator()
        );

        game.run();
    }
}
