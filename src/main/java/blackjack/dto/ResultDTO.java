package blackjack.dto;

import blackjack.model.Result;
import java.util.List;
import java.util.stream.Collectors;

public class ResultDTO {
    private final EntryDTO entry;
    private final boolean win;

    private ResultDTO(EntryDTO entry, boolean win) {
        this.entry = entry;
        this.win = win;
    }

    public static List<ResultDTO> from(List<Result> results) {
        return results.stream()
                .map(ResultDTO::from)
                .collect(Collectors.toList());
    }

    public static ResultDTO from(Result result) {
        return new ResultDTO(EntryDTO.from(result.getEntry()), result.isWin());
    }

    public EntryDTO getEntry() {
        return entry;
    }

    public boolean isWin() {
        return win;
    }
}
