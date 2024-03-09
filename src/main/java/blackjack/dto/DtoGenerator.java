package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DtoGenerator {

    public static List<PlayerDto> createPlayerDtos(List<Player> players) {
        return players.stream()
                .map(DtoGenerator::createPlayerDto)
                .toList();
    }

    public static PlayerDto createPlayerDto(Player player) {
        return new PlayerDto(player.getPlayerName(), createHandDeckDto(player));
    }

    public static DealerDto createInitialDealerDto(Card card) {
        return new DealerDto(createHandDeckDtoByCard(card));
    }

    public static DealerDto createDealerDto(Dealer dealer) {
        return new DealerDto(createHandDeckDto(dealer));
    }

    public static PlayersResultDto createPlayerResultDto(List<Player> players, GameResult gameResult) {
        Map<String, String> playersResult = new LinkedHashMap<>();
        for (Player player : players) {
            String playerName = player.getPlayerName();
            String playerResult = gameResult.findPlayerResult(player).getName();
            playersResult.put(playerName, playerResult);
        }

        return new PlayersResultDto(playersResult);
    }

    public static DealerResultDto createDealerResultDro(GameResult gameResult) {
        return new DealerResultDto(
                gameResult.countDealerWins(),
                gameResult.countDealerTies(),
                gameResult.countDealerLoses()
        );
    }

    private static HandDeckDto createHandDeckDto(Gamer gamer) {
        List<CardDto> cardDtos = gamer.getHandDeck().stream()
                .map(DtoGenerator::createCardDto)
                .toList();

        int score = gamer.calculateTotalScore();

        return new HandDeckDto(cardDtos, score);
    }

    private static HandDeckDto createHandDeckDtoByCard(Card card) {
        List<CardDto> cardDto = List.of(createCardDto(card));
        int score = card.getScore();

        return new HandDeckDto(cardDto, score);
    }

    private static CardDto createCardDto(Card card) {
        String cardNumber = card.number().getName();
        String cardPattern = card.pattern().getName();
        return new CardDto(cardNumber, cardPattern);
    }
}
