import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        Gamers gamers = new Gamers(inputView.readPlayersNames());

        List<Card> cards = CardsGenerator.generateRandomCards();
        CardPack cardPack = new CardPack(cards);

        for (Gamer gamer : gamers.getGamers()) {
            gamer.hit(cardPack.pickOneCard());
            gamer.hit(cardPack.pickOneCard());

        }
        resultView.printInitialCards(gamers);

        for (Gamer gamer : gamers.callPlayers()) {
            HitOption hitOption = new HitOption(inputView.readHitOrNot(gamer));

            while (gamer.isNotBust() && hitOption.doHit()) {
                gamer.hit(cardPack.pickOneCard());
                resultView.printPlayerCards(gamer);
                hitOption = new HitOption(inputView.readHitOrNot(gamer));
            }
        }

        Dealer dealer = gamers.callDealer();
        System.out.println(dealer.getCards().get(1).getCardNumber().getScore());
        while (dealer.canHit()) {
            Card pickedCard = cardPack.pickOneCard();
            dealer.hit(pickedCard);
            resultView.printDealerHitMessage(dealer, pickedCard);
        }

        resultView.printAllGamersResult(gamers);

        Judge judge = new Judge();
        for (Player player : gamers.callPlayers()) {
            judge.decidePlayerResult(player, dealer);
        }
        Map<Player, WinState> playersResult = judge.getResult();
        Map<WinState, Integer> dealerResult = judge.decideDealerResult();

        resultView.printFinalResults(dealer, dealerResult, playersResult);
    }
}
