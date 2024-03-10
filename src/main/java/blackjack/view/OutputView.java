package blackjack.view;

import java.util.List;

import blackjack.dto.CardDto;
import blackjack.dto.DealerInitialHandDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.GamerHandDto;
import blackjack.dto.PlayerResultsDto;

public class OutputView {

	private static final String LINE_SEPARATOR = System.lineSeparator();

	public void printInitialHands(DealerInitialHandDto dealerInitialHandDto, List<GamerHandDto> playersInitialHandDto) {
		System.out.printf("%s와 %s에게 2장을 나누었습니다." + LINE_SEPARATOR, dealerInitialHandDto.name(),
			joinPlayerNames(playersInitialHandDto));

		System.out.printf("%s 카드: %s" + LINE_SEPARATOR, dealerInitialHandDto.name(),
			formatCardName(dealerInitialHandDto.firstCard()));
		printAllPlayerHands(playersInitialHandDto);
	}

	private String joinPlayerNames(List<GamerHandDto> playersInitialHandDto) {
		return String.join(", ", getPlayerNames(playersInitialHandDto));
	}

	private List<String> getPlayerNames(List<GamerHandDto> playersInitialHandDto) {
		return playersInitialHandDto.stream().map(GamerHandDto::name).toList();
	}

	private String formatCardName(CardDto cardDto) {
		return cardDto.cardNumber() + cardDto.cardShape();
	}

	private void printAllPlayerHands(List<GamerHandDto> playersInitialHandDto) {
		playersInitialHandDto.forEach(this::printPlayerHand);
	}

	public void printPlayerHand(GamerHandDto playerHandDto) {
		System.out.println(buildPlayerHand(playerHandDto));
	}

	private StringBuilder buildPlayerHand(GamerHandDto gamerHandDto) {
		StringBuilder stringBuilder = new StringBuilder();
		return stringBuilder
			.append(gamerHandDto.name())
			.append(" 카드: ")
			.append(joinCardNames(gamerHandDto));
	}

	private String joinCardNames(GamerHandDto gamerHandDto) {
		return String.join(", ", getCardNames(gamerHandDto));
	}

	private List<String> getCardNames(GamerHandDto gamerHandDto) {
		return gamerHandDto.gamerHand().stream().map(this::formatCardName).toList();
	}

	public void printDealerMessage(String dealerName) {
		System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다." + LINE_SEPARATOR, dealerName);
	}

	public void printScore(GamerHandDto gamerHandDto, int score) {
		StringBuilder builder = buildPlayerHand(gamerHandDto)
			.append(" - ")
			.append("결과: ")
			.append(score);
		System.out.println(builder);
	}

	public void printResult(DealerResultDto dealerResultDto, PlayerResultsDto playerResultsDto) {
		System.out.println("## 최종 승패");
		printDealerResult(dealerResultDto);
		printPlayerResults(playerResultsDto);
	}

	private void printDealerResult(DealerResultDto dealerResultDto) {
		int winCount = dealerResultDto.winCount();
		int loseCount = dealerResultDto.loseCount();
		StringBuilder stringBuilder = new StringBuilder(dealerResultDto.name()).append(": ");

		if (winCount > 0) {
			stringBuilder.append(winCount).append("승 ");
		}
		if (loseCount > 0) {
			stringBuilder.append(loseCount).append("패");
		}
		System.out.println(stringBuilder);
	}

	private void printPlayerResults(PlayerResultsDto playerResultsDto) {
		playerResultsDto.playerNameResults().forEach((name, result) ->
			System.out.println(name + ": " + result)
		);
	}

	public void printEmptyLine() {
		System.out.println();
	}
}
