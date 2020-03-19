import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import domain.card.Deck;
import service.BlackJackGame;
import domain.card.DeckFactory;
import domain.user.Dealer;
import domain.user.PlayersInfo;
import view.InputView;
import view.OutputView;
import view.dto.GameResultDto;

public class Application {

    public static void main(String[] args) {
        List<String> playerNames = InputView.receiveNameInput();
        Map<String, Integer> players = new LinkedHashMap<>();
        playerNames.forEach(name -> players.put(name, InputView.receiveMoneyInput(name)));

        PlayersInfo playersInfo = PlayersInfo.of(players);
        Dealer dealer = Dealer.appoint();
        Deck deck = DeckFactory.createDeck();

        BlackJackGame.firstDealOut(deck, dealer, playersInfo);
        OutputView.printFirstDealOutResult(dealer, playersInfo);

        BlackJackGame.additionalDealOut(deck, dealer, playersInfo);

        GameResultDto gameResultDto = GameResultDto.of(BlackJackGame.getUserToCardPoint(dealer, playersInfo),
                BlackJackGame.getProfitOfPlayers(dealer, playersInfo),
                BlackJackGame.getProfitOfDealer(dealer, playersInfo));
        OutputView.printTotalResult(gameResultDto);
    }
}
