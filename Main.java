/*
* The Main program is a recreation of the ticTacToe program.
*
* @author  Ben Whitten
* @version 1.0
* @since   2021-1-11
*/

import java.util.Scanner;  // Import the Scanner class

///////////////////////////////////////////////////////////////////////////////
class Main {
  /**
   * This function is the recreation of the tikTacToe program.
   */
  public static int ticTacToe(String [] board, int turn) {
    try {
      if (turn > 8) {
        return 0;
      }
      System.out.println("");
      System.out.println("- Player Turn -");
      System.out.println("--------------------");
      // Creating a scanner.
      Scanner scanOptions = new Scanner(System.in);
      System.out.println("What is your move? (1-9)");
      String playerChoice = scanOptions.nextLine();
      int playerInt = Integer.parseInt(playerChoice);
    
      if (board[playerInt - 1].equals("X")
          || board[playerInt - 1].equals("O")) {
        System.out.println("Someone has already made that move.");
        return ticTacToe(board, turn);
      } else {
        board[playerInt - 1] = "X";
        showBoard(board);
        if (checkBoardPlayer(board)) {
          return 1;
        }
        if (turn == 8) {
          return ticTacToe(board, turn + 1);
        }
        int cpuInt = cpuTurn(board);
        board[cpuInt] = "O";
        System.out.println("");
        System.out.println("- Cpu Turn -");
        System.out.println("--------------------");
        showBoard(board);
        if (checkBoardCpu(board)) {
          return -1;
        }
        return ticTacToe(board, turn + 2);
      }
    } catch (Exception e) {
      System.out.println("Error");
      return ticTacToe(board, turn);
    }
  }

  /////////////////////////////////////////////////////////////////////////////
  /**
   * This function determines what move the cpu is going to make to ensure
   * it's victory.
   */
  public static int cpuTurn(String [] board) {

    // Bestposition is created so that the function will take winning over
    // blocking your win.
    int bestPosition = -1;

    // CPU takes the middle if it's free.
    if (board[4].equals("")) {
      return 4;
    }
    
    //Blocking the other player of going for the win
    for (int position = 0; position < board.length; position++) {
      if (board[position].equals("")) {
        board[position] = "O";
        if (checkBoardCpu(board)) {
          return position;
        }
        board[position] = "X";
        if (checkBoardPlayer(board)) {
          bestPosition = position;
        }
        board[position] = "";

        if (position == 0) {
          if (board[position + 1].equals("X")
              && board[position + 3].equals("X")) {
            return position;
            
          }
        } else if (position == 2) {
          if (board[position - 1].equals("X")
              && board[position + 3].equals("X")) {
            return position;
            
          }
        } else if (position == 6) {
          if (board[position + 1].equals("X")
              && board[position - 3].equals("X")) {
            return position;
            
          }
        } else if (position == 8) {
          if (board[position - 1].equals("X")
              && board[position - 3].equals("X")) {
            return position;
          }
        }
      }
    }

    // Deciding turn 2.
    if (bestPosition == -1) {
      for (bestPosition = 0; bestPosition < board.length; bestPosition++) {
        if (board[bestPosition].equals("")) {
          if (bestPosition == 1 && board[7].equals("")) {
            return bestPosition;
          } else if (bestPosition == 7 && board[1].equals("")) {
            return bestPosition;
          } else if (bestPosition == 3 && board[5].equals("")) {
            return bestPosition;
          } else if (bestPosition == 5 && board[3].equals("")) {
            return bestPosition;
          }
        }
      }
    } else {
      return bestPosition;
    }
    return 1;
  }

  /////////////////////////////////////////////////////////////////////////////
  /**
   * This function outputs the grid.
   */
  public static void showBoard(String [] board) {
    for (int position = 0; position < board.length; position++) {
      if ((position + 1) % 3 == 0 && position != 8) {
        if (board[position].equals("")) {
          System.out.print("  " + "\n=============\n");
        } else {
          System.out.print(" " + board[position] + "\n=============\n");
        }
      } else if (position == 8) {
        if (board[position].equals("")) {
          System.out.print("  ");
          System.out.println("");
        } else {
          System.out.print(" " + board[position]);
          System.out.println("");
        }
      } else {
        if (board[position].equals("")) {
          System.out.print("   ||");
        } else {
          System.out.print(" " + board[position] + " ||");
        }
      }
    }
  }

  /////////////////////////////////////////////////////////////////////////////

  /**
   * This function checks to see if the player has or will win.
   */
  public static boolean checkBoardPlayer(String[] board) {

    // Horizontal
    if (board[0].equals("X") && board[1].equals("X") && board[2].equals("X")) {
      return true;
    } else if (board[3].equals("X") && board[4].equals("X")
               && board[5].equals("X")) {
      return true;
    } else if (board[6].equals("X") && board[7].equals("X")
               && board[8].equals("X")) {
      return true;
    }
    // Vertical
    if (board[0].equals("X") && board[3].equals("X") && board[6].equals("X")) {
      return true;
    } else if (board[1].equals("X") && board[4].equals("X")
               && board[7].equals("X")) {
      return true;
    } else if (board[2].equals("X") && board[5].equals("X")
               && board[8].equals("X")) {
      return true;
    }
    // Diagonal
    if (board[0].equals("X") && board[4].equals("X") && board[8].equals("X")) {
      return true;
    } else if (board[2].equals("X") && board[4].equals("X")
               && board[6].equals("X")) {
      return true;
    } 
    
    return false;
  }

  /////////////////////////////////////////////////////////////////////////////

  /**
   * This function checks to see if the CPU has or will win.
   */
  public static boolean checkBoardCpu(String[] board) {

    // Horizontal
    if (board[0].equals("O") && board[1].equals("O") && board[2].equals("O")) {
      return true;
    } else if (board[3].equals("O") && board[4].equals("O")
               && board[5].equals("O")) {
      return true;
    } else if (board[6].equals("O") && board[7].equals("O")
               && board[8].equals("O")) {
      return true;
    }
    // Vertical
    if (board[0].equals("O") && board[3].equals("O") && board[6].equals("O")) {
      return true;
    } else if (board[1].equals("O") && board[4].equals("O")
               && board[7].equals("O")) {
      return true;
    } else if (board[2].equals("O") && board[5].equals("O")
               && board[8].equals("O")) {
      return true;
    }
    // Diagonal
    if (board[0].equals("O") && board[4].equals("O") && board[8].equals("O")) {
      return true;
    } else if (board[2].equals("O") && board[4].equals("O")
               && board[6].equals("O")) {
      return true;
    } 
    
    return false;
  }

  /////////////////////////////////////////////////////////////////////////////

  /**
   * This function handles the output of the program and calls the other
   * functions.
   */
  public static void main(String[] args) {
    System.out.println(" 1 || 2 || 3 ");
    System.out.println("=============");
    System.out.println(" 4 || 5 || 6 ");
    System.out.println("=============");
    System.out.println(" 7 || 8 || 9 ");
    String [] board = new String [9];
    for (int position = 0; position < 9; position++) {
      board[position] = "";
    }
    int end = ticTacToe(board, 0);
    System.out.println("");
    System.out.println("------------------------------------------");
    if (end == 0) {
      System.out.println("You TIED");
    } else if (end == 1) {
      System.out.println("You WON");
    } else if (end == -1) {
      System.out.println("You LOST");
    }
    System.out.println("------------------------------------------");
  }
}
