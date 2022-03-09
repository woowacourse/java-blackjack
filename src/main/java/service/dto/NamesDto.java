package service.dto;

import java.util.ArrayList;
import java.util.List;

public class NamesDto {
    private final List<String> names;

    public NamesDto(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return new ArrayList<>(names);
    }
}
