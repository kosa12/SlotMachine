import symbols.Symbol;

public class SlotGameLogic {

  private static boolean checkRows(Symbol[][] symbols) {
    for (Symbol[] symbol : symbols) {
      if (symbol[0].getSymbol().equals(symbol[1].getSymbol())
          && symbol[1].getSymbol().equals(symbol[2].getSymbol())) {
        return true;
      }
    }
    return false;
  }

  private static boolean checkColumns(Symbol[][] symbols) {
    for (int j = 0; j < symbols[0].length; j++) {
      if (symbols[0][j].getSymbol().equals(symbols[1][j].getSymbol())
          && symbols[1][j].getSymbol().equals(symbols[2][j].getSymbol())) {
        return true;
      }
    }
    return false;
  }

  private static boolean checkDiagonals(Symbol[][] symbols) {
    return (symbols[0][0].getSymbol().equals(symbols[1][1].getSymbol())
        && symbols[1][1].getSymbol().equals(symbols[2][2].getSymbol()))
        || (symbols[0][2].getSymbol().equals(symbols[1][1].getSymbol())
        && symbols[1][1].getSymbol().equals(symbols[2][0].getSymbol()));
  }

  public static boolean isWin(Symbol[][] symbols) {
    return checkRows(symbols) || checkColumns(symbols) || checkDiagonals(symbols);
  }

  public static double calculatePayout(Symbol[][] symbols, double betAmount) {
    double basePayout = 2.0;

    int winConditionsCount = 0;
    if (checkRows(symbols)) {
      winConditionsCount++;
    }
    if (checkColumns(symbols)) {
      winConditionsCount++;
    }
    if (checkDiagonals(symbols)) {
      winConditionsCount++;
    }

    return betAmount * (basePayout + 0.5 * (winConditionsCount - 1));
  }

  public static double updateBalance(double currentBalance, double betAmount, double payout) {
    return currentBalance + payout - betAmount;
  }
}
