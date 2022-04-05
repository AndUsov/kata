package kata.utils;


public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.equals("");
    }

    public static String getStringBefore(String s, String before) {
        int index = s.indexOf(before);
        if (index > -1) {
            return s.substring(0, index);
        }
        return "";
    }

    public static String getStringAfter(String s, String after) {
        int start = s.indexOf(after);
        if (start > -1) {
            return s.substring(start + after.length());
        }
        return "";
    }

}
