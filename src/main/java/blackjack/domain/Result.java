package blackjack.domain;

import blackjack.view.dto.PlayerResultDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private static final Map<String, List<Outcome>> RESULTS = new LinkedHashMap<>();

    public static Map<String, List<Outcome>> findResults(Round round) {
        List<Outcome> gameOutComes = round.makeRoundOutComes();
        RESULTS.put(round.getDealerName(), new ArrayList<>(gameOutComes));

        List<PlayerResultDto> playerResultDtos = round.makePlayerResults(gameOutComes);
        playerResultDtos.forEach(dto ->
                RESULTS.put(dto.getPlayerName(), Outcome.getPlayerOutcomes(dto.getOutcome()))
        );
        return new LinkedHashMap<>(RESULTS);
    }
}
