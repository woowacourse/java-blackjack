package blackjack.controller.dto.request;

import java.util.List;

public class NamesRequestDto {

    private List<String> names;

    public NamesRequestDto(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

}
