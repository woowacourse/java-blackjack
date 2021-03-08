package blackjack.domain;

import blackjack.view.dto.PlayerResultDto;

import java.util.*;

public class Result {

    private Result() {
    }

    public static Map<String, Queue<Outcome>> findResults(final Round round) {
        Map<String, Queue<Outcome>> results = new LinkedHashMap<>();
        Queue<Outcome> gameOutComes = round.makeRoundOutComes();
        results.put(round.getDealerName(), new ArrayDeque<>(gameOutComes));

        List<PlayerResultDto> playerResultDtos = round.makePlayerResults(gameOutComes);
        playerResultDtos.forEach(dto ->
                results.put(dto.getPlayerName(),
                        new ArrayDeque<>(Collections.singletonList(Outcome.getPlayerOutcomes(dto.getOutcome()))))
        );
        return new LinkedHashMap<>(results);
    }
}
