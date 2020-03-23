import controller.BlackJackGame;
import domain.card.DeckFactory;
import domain.result.GameResult;
import domain.user.Dealer;
import domain.user.PlayersInfo;
import view.InputView;
import view.OutputView;
import view.dto.GameResultDto;
import view.dto.PlayersDto;
import view.dto.UserDto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        List<String> playerNames = InputView.receiveNameInput();
        Map<String, Integer> playersInfo = new LinkedHashMap<>();
        playerNames.forEach(name -> playersInfo.put(name, InputView.receiveMoneyInput(name)));

        PlayersInfo players = PlayersInfo.of(playersInfo);
        Dealer dealer = Dealer.appoint();

        BlackJackGame blackJackGame = BlackJackGame.getInstance(DeckFactory.createDeck());

        blackJackGame.firstDealOut(dealer, players);
        OutputView.printFirstDealOutResult(UserDto.of(dealer), PlayersDto.of(players));

        blackJackGame.additionalDealOut(dealer, players);

        GameResult gameResult = GameResult.of(dealer, players);
        OutputView.printTotalResult(GameResultDto.of(gameResult));
    }
}
