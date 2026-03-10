package assembler;

import domain.Dealer;
import domain.Judge;
import domain.Player;
import domain.card.Card;
import dto.*;

import java.util.ArrayList;
import java.util.List;

public class OutputDtoAssembler {
    private static final String DEALER_NAME = "딜러";

    public static HandDto toPlayerHandDto(Player player) {
        return new HandDto(
                player.getName(),
                player.getCards().stream()
                        .map(Card::getCardName)
                        .toList()
        );
    }

    public static HandDto toDealerHandDto(Dealer dealer) {
        return new HandDto(
                DEALER_NAME,
                dealer.getCards().stream()
                        .map(Card::getCardName)
                        .toList()
        );
    }

    public static BlackJackInitStatusDto toBlackJackInitStatusDto(Dealer dealer,
                                                                  List<Player> players) {
        return new BlackJackInitStatusDto(toDealerInitHandDto(dealer),
                players.stream()
                .map(OutputDtoAssembler::toPlayerHandDto)
                .toList());
    }

    public static FinalResultDto toFinalResultDto(Dealer dealer,
                                                  List<Player> players,
                                                  Judge judge) {
        List<ScoreResultDto> scoreResultDtos = createScoreResultDtos(dealer, players);
        int dealerWinCount = judge.judgeDealerWinCount();
        int dealerLoseCount = judge.judgeDealerLoseCount();
        return new FinalResultDto(scoreResultDtos, dealerWinCount, dealerLoseCount,
                judge.getPlayerResults());
    }

    private static HandDto toDealerInitHandDto(Dealer dealer) {
        return new HandDto(
                DEALER_NAME,
                List.of(dealer.getFirstCard().getCardName())
        );
    }

    private static ScoreResultDto toPlayerScoreResultDto(Player player) {
        return new ScoreResultDto(
                toPlayerHandDto(player),
                player.getScore()
        );
    }

    private static ScoreResultDto toDealerScoreResultDto(Dealer dealer) {
        return new ScoreResultDto(
                toDealerHandDto(dealer),
                dealer.getScore()
        );
    }

    private static List<ScoreResultDto> createScoreResultDtos(Dealer dealer,
                                                              List<Player> players) {
        List<ScoreResultDto> scoreResultDtos = new ArrayList<>();
        scoreResultDtos.add(toDealerScoreResultDto(dealer));
        scoreResultDtos.addAll(
                players.stream()
                        .map(OutputDtoAssembler::toPlayerScoreResultDto)
                        .toList()
        );
        return scoreResultDtos;
    }
}
