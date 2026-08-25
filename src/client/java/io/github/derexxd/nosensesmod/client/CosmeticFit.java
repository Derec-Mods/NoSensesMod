package io.github.derexxd.nosensesmod.client;

public final class CosmeticFit {
    private static final float BLINDFOLD_Y = -8.0F;

    private CosmeticFit() {
    }

    public static float blindfoldY(String playerName) {
        if (playerName == null) {
            return 0.0F;
        }
        return switch (playerName) {
            case "AnimalMace" -> BLINDFOLD_Y;
            case "lolfrosty" -> BLINDFOLD_Y + 1.0F;
            case "iShoya" -> BLINDFOLD_Y;
            case "plazamc" -> 1.0F;
            default -> 0.0F;
        };
    }
}
