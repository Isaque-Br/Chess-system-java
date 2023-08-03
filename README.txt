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