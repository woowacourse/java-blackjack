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

        List<Player> players = new ArrayList<>();
        Dealer dealer = new Dealer(new Name("딜러"), cardsCards.get(0));
        players.add(dealer);

        for (int i = 0; i < names.size(); i++) {
            players.add(new Player(names.get(i), cardsCards.get(i + 1)));
        }

        List<String> namesCopy = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        List<List<String>> cardsCardsCopy = cardsCards.stream()
                .map(Cards::getCards)
                .collect(Collectors.toList());

        outputView.printPlayerNames(namesCopy);
        outputView.printCardsPerPlayer(namesCopy, cardsCardsCopy);

        for (int i = 1; i < players.size(); i++) {
            while (true) {
                String yOrN = inputView.addOrStop(players.get(i).getName());
                if (yOrN.equals("n")) {
                    outputView.printCurrentPlayerResult(players.get(i).getName(), players.get(i).getCards());
                    break;
                }
                boolean flag2 = true;
                while (flag2) {
                    flag2 = !players.get(i).selectToPickOtherCard(cardBox, cardNumberGenerator);
                }
                outputView.printCurrentPlayerResult(players.get(i).getName(), players.get(i).getCards());
            }
        }

        System.out.println();
        while (dealer.isSumUnderStandard()) {
            outputView.noticeDealerUnderStandard();
            boolean flag2 = true;
            while (flag2) {
                flag2 = !dealer.selectToPickOtherCard(cardBox, cardNumberGenerator);
            }
        }
        System.out.println();
        for (Player player : players) {
            outputView.printAllCardResult(player.getName(), player.getCards(),
                    player.sumOfPlayerCards());
        }

        List<Integer> winningResult = new ArrayList<>();

        for (int i = 1; i < players.size(); i++) {
            winningResult.add(dealer.checkWinningResult(players.get(i)));
        }

        outputView.printWinningResult(winningResult, namesCopy);

    }
}
