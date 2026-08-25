package io.github.derexxd.nosensesmod.client;

public final class CosmeticFit {
    private CosmeticFit() {
    }

    public static float blindfoldY(String playerName) {
        if (playerName == null) {
            return 0.0F;
        }
        return switch (playerName) {
            case "plazamc", "lolfrosty" -> 1.0F;
            case "AnimalMace", "iShoya" -> 0.0F;
            default -> 0.0F;
        };
    }
}
