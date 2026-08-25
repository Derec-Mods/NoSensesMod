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
            case "lolfrosty" -> BLINDFOLD_Y;
            case "iShoya" -> BLINDFOLD_Y;
            default -> 0.0F;
        };
    }
}
