package view.dto;

import java.util.List;

public record PlayerDto(String name, List<String> cards) {
    public PlayerDto(String name) {
        this(name, null);
    }
}
