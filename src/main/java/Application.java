import domain.Card;
import domain.CardNumberGenerator;
import domain.Cards;
import domain.CardBox;
import domain.Name;
import domain.RandomCardNumberGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import view.InputView;

public class Application {

    private static InputView inputView = new InputView();
    private static CardBox cardBox = new CardBox();
    private static CardNumberGenerator cardNumberGenerator = new RandomCardNumberGenerator();

    public static void main(String[] args) {
        List<String> playerNames = inputView.getPlayer();

        List<Name> names = playerNames.stream()
                .map(Name::new)
                .collect(Collectors.toList());

        List<Cards> cardsCards = new ArrayList<>();

        for (int i = 0; i < names.size() + 1; i++) {
            List<Card> cards = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                cards.add(cardBox.get(cardNumberGenerator.generateIndex()));
            }
            cardsCards.add(new Cards(cards));
        }
    }
}
