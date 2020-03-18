import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

public class Application {

    public static void main(String[] args) {
        List<String> playerNames = InputView.receiveNameInput();
        Map<String, Integer> players = new LinkedHashMap<>();
        playerNames.forEach(name -> players.put(name, InputView.receiveMoneyInput(name)));

        PlayersInfo playersInfo = PlayersInfo.of(players);
        Dealer dealer = Dealer.appoint();

        BlackJackGame blackJackGame = BlackJackGame.set(DeckFactory.createDeck());

        blackJackGame.firstDealOut(dealer, playersInfo);
        UserDto dealerDto = UserDto.of(dealer.getName(), dealer.getCards());
        OutputView.printFirstDealOutResult(dealerDto, PlayersDto.of(playersInfo.getPlayers()));

        blackJackGame.additionalDealOut(dealer, playersInfo);

        GameResult gameResult = GameResult.of(dealer, playersInfo);
        GameResultDto gameResultDto = GameResultDto.of(gameResult.getUserDtoToCardPoint(),
                gameResult.getProfitOfPlayers(), gameResult.getProfitOfDealer());
        OutputView.printTotalResult(gameResultDto);
    }
}
