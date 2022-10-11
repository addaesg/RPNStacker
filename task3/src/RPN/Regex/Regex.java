package RPN.Regex;

import RPN.Exceptions.InvalidCharException;
import RPN.tokens.TokenType;

import java.util.Arrays;
import java.util.Map;

// Regex Class
// (Soma, Subtração, Multiplicação, Divisão, Potência)
public class Regex {
    private static final String REGEX_NUM = "(\\d)+";
    private static final String REGEX_PLUS = "(\\+)";
    private static final String REGEX_MINUS = "(\\-)";
    private static final String REGEX_MULT = "(\\*)";
    private static final String REGEX_DIV = "(/)";
    private static final String REGEX_POW = "(\\^)";

    private static final String[] REGEX_TYPES = {REGEX_NUM, REGEX_PLUS, REGEX_MINUS, REGEX_MULT, REGEX_DIV, REGEX_POW};

    private static final Map<String, TokenType> MAP_REGEX_TOKEN = Map.ofEntries(
            Map.entry(REGEX_NUM, TokenType.NUM),
            Map.entry(REGEX_PLUS, TokenType.PLUS),
            Map.entry(REGEX_MULT, TokenType.STAR),
            Map.entry(REGEX_MINUS, TokenType.MINUS),
            Map.entry(REGEX_DIV, TokenType.SLASH),
            Map.entry(REGEX_POW, TokenType.POW)
    );

    // REGEX_TYPES = {REGEX_NUM, REGEX_PLUS, REGEX_MINUS, REGEX_MULT, REGEX_DIV, REGEX_POW}
    private static String getRegexType(String token) throws InvalidCharException {
        String regexType = Arrays.stream(REGEX_TYPES)
                .filter(regex -> token.matches(regex)).findFirst()
                .orElse(null);
        if (regexType == null) {
            System.out.println("Error: Unexpected character: '" + token + "'");
            throw new InvalidCharException(token);
        }
        return regexType;
    }

    // Retorna o tipo de token de um dado string.
    // Chama a função getRegexType
    //                  que usa matches para definir o REGEX_TYPE da string
    //                  e por fim retornar o TokenType correspondente.
    public static TokenType getTokenType(String token) throws InvalidCharException {
        String regexType = getRegexType(token);
        TokenType tokenType = MAP_REGEX_TOKEN.get(regexType);
        return tokenType;
    }

    public static boolean isNum(String token) {
        return token.matches(REGEX_NUM);
    }

    public static boolean isOP(String token) {
        return token.matches("(\\+|\\-|\\*|\\^|/)");
    }
}
