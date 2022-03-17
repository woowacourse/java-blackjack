package blackjack.view;

import java.util.List;

import blackjack.dto.CardDto;
import blackjack.dto.MatchResultDto;
import blackjack.dto.dealer.DealerDto;
import blackjack.dto.dealer.DealerInitialCardDto;
import blackjack.dto.player.PlayerDto;
import blackjack.dto.player.PlayerInitialCardsDto;
import blackjack.view.input.InputView;
import blackjack.view.output.OutputView;

public class BlackjackView {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackView(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public int requestPlayerBettingAmount(final String playerName) {
        outputView.printEmptyLine();
        outputView.printMessage(playerName + "의 베팅 금액은?");
        return inputView.requestBettingAmount();
    }

    public List<String> requestPlayerNames() {
        outputView.printMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return inputView.requestPlayerNames();
    }

    public void printInitiallyDistributedCards(final DealerInitialCardDto dealerInitiallyDrewCardDto,
                                               final List<PlayerInitialCardsDto> playerInitiallyDrewCardDtos) {
        outputView.printEmptyLine();
        outputView.printMessageOfInitiallyDistributeCards(playerInitiallyDrewCardDtos);
        outputView.printDistributedCardsOfDealer(dealerInitiallyDrewCardDto);
        playerInitiallyDrewCardDtos.forEach(outputView::printDistributedCardsOfPlayer);
        outputView.printEmptyLine();
    }

    public boolean requestDrawingCardChoice(final String playerName) {
        outputView.printMessageOfRequestDrawingCardChoice(playerName);
        return inputView.requestDrawingCardChoice();
    }

    public void printCurrentCardsOfPlayer(final String playerName, final List<CardDto> cardDtos) {
        outputView.printCurrentCardsOfPlayer(playerName, cardDtos);
    }

    public void printMessageOfDealerDrewCard() {
        outputView.printEmptyLine();
        outputView.printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalScores(final DealerDto dealerDto, final List<PlayerDto> playerDtos) {
        outputView.printEmptyLine();
        outputView.printFinalScoreOfDealer(dealerDto);
        outputView.printFinalScoreOfPlayers(playerDtos);
    }

    public void printMatchResult(final MatchResultDto matchResultDto) {
        outputView.printEmptyLine();
        outputView.printMessage("## 최종 승패");
        outputView.printMatchResult(matchResultDto);
    }

}
