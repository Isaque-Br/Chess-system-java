## PROJETO JOGO DE XADREZ ##

• First class: Position
    - Class Position [public]
    - OOP Topic:
        • Encapsulation (private atributos + get e set)
        • Constructors (construtor com argumentos)
        • ToString (Object / @Overriding) (toString quando fazemos estamos sobescrevendo)
____________________________________________________________________________________________
     Starting to implement Board and Piece

   Checklist:
    • Classes Piece, Board [public]
    • OOP Topics:
        - Associations
        - Encapsulation / Access Modifiers
    • Data Structures Topics:
        - Matrix
____________________________________________________________________________________________

Checklist:
    • Methods: Board.Piece(row, column) and Board.Piece(position)
    • Enum Chess.Color
    • Class Chess.ChessPiece [public]
    • Class Chess.ChessMatch [public]
    Class ChessConsole.UI
    • OOP Topics:
        - Enumerations
        - Encapsulation / Access Modifiers
        - Inheritance /
        - Downcasting
        - Static members
        Layers pattem // PADRÃO DE DESENVOLVIMENTO EM CAMADAS
    • Data Structures Topics:
        - Matrix
____________________________________________________________________________________________

        Placing pieces on the board

  Checklist:
      • Methods: Board.PlacePiece(piece, position)
      • Classes: Rook, King [public]
      • Method: ChessMatch.InitialSetup
      • OOP Topics:
        - Inheritance
        - Overriding
        - Polymorphism (toString)
____________________________________________________________________________________________

        BoardException and defensive programming

   Checklist:
         • Class: BoardException [public]
         • Methods: Board.PositionExists, Board.ThereIsAPiece [public]
         • Implement.defensive programming in Board methods
         • OOP Topics:
            - Exception
            - Constructors (a string must be informed to the exception)
____________________________________________________________________________________________

        ChessException and ChessPosition

   Checklist:
         • Class: ChessExceptions [public]
         • Class: ChessPosition [public]
         • Refactor ChessMatch.InitialSetup
         • OOP Topics:
            - Exception
            - Encapsulation
            - Constructors (a string must be informed to the exception)
            - Overriding
            - Static members
            - Layers pattem
____________________________________________________________________________________________

        Little improvement in board printing

   Color in terminal:
         • Windows: Git [public]
         • Class: ChessPosition [public]
         • Mac: Google "osx terminal color"
____________________________________________________________________________________________

        Moving pieces

   Checklist:
        • Method Board.RemovePiece
        • Method UI.ReadChessPosition
        • Method ChessMatch.MakeMove
            - Method ChessMatch.MakeMove
            - Method ChessMatch.ValidadeSourcePosition
        • Write basic logic on Program.cs
        • OOP Topics:
            - Exceptions
            - Encapsulation
____________________________________________________________________________________________

        Handling exceptions and clearing screen

   Checklist:
    • Clear screen using Java:
        // https://stackoverflow.com/questions/2979383/java-clear-the-console
        public static void clearScreen() {
            System.out.print("\033[H\033[2J";
            System.out.flush();
        }

    • ChessException
    • InputMismatchException
  ____________________________________________________________________________________________

        Possible moves of a piece

    Checklist:
     • Methods in Piece:
        – PossibleMoves [abstract]
        - PossibleMove
        - IsThereAnyPossibleMove
     • Basic PossibleMove implementation for Rook and King
     • Update ChessMatch.ValidadeSourcePosition
     • OOP Topics:
        - Abstract method / class
        - Exceptions

  Cada tipo de peça tem uma regra diferente para se mover.
    - A Torre pode se mover tanto para horizontal(quantas casas quiser) idem na vertical
Pode se mover para casas livres ou quando existir peça adversaria, que no caso, pode captura-la.
RERAS BASICAS DA TORRE.

Dado uma peça, os movimentos possiveis dela, será uma matrix de valores boolean contendo os possiveis movimentos
Nesta Matrix, terá os valores falsos e verdadeiros pra onde ela puder caminhar/mover.

____________________________________________________________________________________________

        Implementing possible moves of Rook

    Checklist:
     • Method ChessPiece.IsThereOpponentPiece(position) [protected]
     • Implement Rook.PossibleMoves
     • Method ChessMatch.ValidadeTargetPosition
     • OOP Topics:
        - Polymorphism
        - Encapsulation / access modifers [protected]
        - Exceptions