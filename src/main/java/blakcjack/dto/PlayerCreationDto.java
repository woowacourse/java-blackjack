package blakcjack.dto;

import java.util.List;

public class PlayerCreationDto {
    private final List<String> names;
    private final List<Integer> bettingMoneys;

    private PlayerCreationDto(final List<String> names, final List<Integer> bettingMoneys) {
        this.names = names;
        this.bettingMoneys = bettingMoneys;
    }

    public static PlayerCreationDto of(final List<String> names, final List<Integer> bettingMoneys) {
        return new PlayerCreationDto(names, bettingMoneys);
    }

    public List<String> getNames() {
        return names;
    }

    public List<Integer> getBettingMoneys() {
        return bettingMoneys;
    }
}
