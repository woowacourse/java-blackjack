package blackjack;

import blackjack.domain.Dealer;
import blackjack.domain.GameBoard;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.ShuffledDeckFactory;
import blackjack.dto.DealerDto;
import blackjack.dto.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        final GameBoard gameBoard = createGameBoard();
        drawInitialHands(gameBoard);

        hitGamers(gameBoard);

        //TODO: Dealer나 Players에 직접 접근하는 것보다 GameBoard로부터 필요한 정보를 얻는게 낫지 않을까?
        /**
         * 근데 GameBoard 객체가 진짜 필요한가? 게임 방식을 다르게 진행하고 싶다면, 새로운 Controller를 만들면 되는 것 아닌가?
         * 여기서 main을 Controller로 쓰기로 했다는 말은, 게임을 여러 방식으로 제공하지는 않겠다는 의미이다.
         * 그럼 Main은 GameBoard의 도움 없이 domain 객체들을 연결시켜서 게임을 수행하는 것이 자연스럽지 않나?
         * -> 현재 방식처럼 GameBoard를 두는게 좋아 보이는지, 아니면 Main에서 직접 관리하는게 좋아 보이는지 여쭤보자.
         *
         * GameBoard를 중간에 둔 이유
         * 컨트롤러가 게임이 정확히 어떻게 진행되는지(예를 들어, 초기 카드는 2장씩 나눠준다든지...)를 알아야할까? 라는 의문에서 시작했다.
         * -> 블랙잭 게임을 진행하기 위해 필요한 구체적인 행동들은 도메인에 속한다고 생각했다.
         */
        OutputView.printFinalState(
                createDealerDto(gameBoard.getDealer().getHand()), createPlayerDtos(gameBoard.getPlayers()));
        OutputView.printFinalProfits(gameBoard.calculateDealerProfit(), gameBoard.calculatePlayerProfits());
    }

    private static GameBoard createGameBoard() {
        final Deck deck = new ShuffledDeckFactory().create();
        final Dealer dealer = Dealer.from(deck);
        final Players players = Players.from(InputView.readPlayerNameAndBettingMoney());
        return new GameBoard(dealer, players);
    }

    private static void drawInitialHands(final GameBoard gameBoard) {
        gameBoard.drawInitialPlayersHand();
        gameBoard.drawInitialDealerHand();

        final DealerDto dealerDto = createDealerDto(gameBoard.openDealerFirstCard());
        final List<PlayerDto> playerDtos = createPlayerDtos(gameBoard.getPlayers());
        OutputView.printInitialState(dealerDto, playerDtos);
    }

    private static DealerDto createDealerDto(final Card card) {
        return createDealerDto(new Hand(List.of(card)));
    }

    private static DealerDto createDealerDto(final Hand hand) {
        return new DealerDto(hand.getCards(), hand.calculateOptimalSum());
    }

    private static List<PlayerDto> createPlayerDtos(final Players players) {
        return players.getPlayers().stream()
                .map(Application::createPlayerDto)
                .toList();
    }

    private static PlayerDto createPlayerDto(final Player player) {
        return new PlayerDto(player.getName(), player.getHand().getCards(), player.calculateScore());
    }

    private static void hitGamers(final GameBoard gameBoard) {
        hitPlayers(gameBoard);
        OutputView.printLineSeparator();
        hitDealer(gameBoard);
        OutputView.printLineSeparator();
    }

    private static void hitPlayers(final GameBoard gameBoard) {
        final Players players = gameBoard.getPlayers();
        for (Player player : players.getPlayers()) {
            hitPlayer(gameBoard, player);
        }
    }

    private static void hitPlayer(final GameBoard gameBoard, final Player player) {
        while (gameBoard.canPlayerHit(player) && InputView.readDoesWantHit(player.getName())) {
            gameBoard.hitPlayer(player);
            OutputView.printCurrentState(createPlayerDto(player));
        }
    }

    private static void hitDealer(final GameBoard gameBoard) {
        while (gameBoard.canDealerHit()) {
            gameBoard.hitDealer();
            OutputView.printDealerDrawMessage();
        }
    }
}
