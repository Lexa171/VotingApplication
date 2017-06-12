package by.ots.util;


public final class KeyGenerationUtil {

    static public String generationKey(String pollName, Long id) {
        return pollName.hashCode() + "" + id;
    }
}
