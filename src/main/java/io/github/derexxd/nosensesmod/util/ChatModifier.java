package io.github.derexxd.nosensesmod.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.util.Optional;

public final class ChatModifier {
    private ChatModifier() {
    }

    public static Text muffle(Text text) {
        MutableText muffled = Text.empty();
        text.visit((style, asString) -> {
            muffled.append(Text.literal(muffle(asString)).setStyle(style));
            return Optional.empty();
        }, Style.EMPTY);
        return muffled;
    }

    public static String muffle(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder muffled = new StringBuilder(text.length());
        boolean wordStart = true;
        for (int index = 0; index < text.length(); ) {
            int codePoint = text.codePointAt(index);
            if (Character.isLetter(codePoint)) {
                muffled.append(muffleLetter(codePoint, wordStart));
                wordStart = false;
            } else {
                muffled.appendCodePoint(codePoint);
                wordStart = !isWordJoiner(codePoint);
            }
            index += Character.charCount(codePoint);
        }
        return muffled.toString();
    }

    private static char muffleLetter(int codePoint, boolean wordStart) {
        char replacement = wordStart ? 'm' : muffleBody(codePoint);
        return Character.isUpperCase(codePoint) ? Character.toUpperCase(replacement) : replacement;
    }

    private static char muffleBody(int codePoint) {
        return switch (Character.toLowerCase(codePoint)) {
            case 'b', 'p' -> 'p';
            case 'd', 'f', 'l', 't', 'v' -> 'f';
            case 'g', 'h', 'j', 'k', 'q', 'x' -> 'h';
            default -> 'm';
        };
    }

    private static boolean isWordJoiner(int codePoint) {
        return codePoint == '\'' || codePoint == '’';
    }
}
