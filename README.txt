## PROJETO JOGO DE XADREZ ##

• First class: Position
    - Class Position [public]
    - OOP Topic:
        • Encapsulation (private atributos + get e set)
        • Constructors (construtor com argumentos)
        • ToString (Object / @Overriding) (toString quando fazemos estamos sobescrevendo)
________________________________________________________________________________________________________________________________________________________________________________
     Starting to implement Board and Piece

   Checklist:
    • Classes Piece, Board [public]
    • OOP Topics:
        - Associations
        - Encapsulation / Access Modifiers
    • Data Structures Topics:
        - Matrix
____________________________________________________________________________________________________________________________________________________

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
____________________________________________________________________________________________________________________________________________________

        Placing pieces on the board

  Checklist:
      • Methods: Board.PlacePiece(piece, position)
      • Classes: Rook, King [public]
      • Method: ChessMatch.InitialSetup
      • OOP Topics:
        - Inheritance
        - Overriding
        - Polymorphism (toString)
____________________________________________________________________________________________________________________________________________________

        BoardException and defensive programming

   Checklist:
         • Class: BoardException [public]
         • Methods: Board.PositionExists, Board.ThereIsAPiece [public]
         • Implement.defensive programming in Board methods
         • OOP Topics:
            - Exception
            - Constructors (a string must be informed to the exception)
____________________________________________________________________________________________________________________________________________________

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
____________________________________________________________________________________________________________________________________________________

        Little improvement in board printing

   Color in terminal:
         • Windows: Git [public]
         • Class: ChessPosition [public]
         • Mac: Google "osx terminal color"
____________________________________________________________________________________________________________________________________________________

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
____________________________________________________________________________________________________________________________________________________

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
  ____________________________________________________________________________________________________________________________________________________

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

____________________________________________________________________________________________________________________________________________________

        Implementing possible moves of Rook

    Checklist:
     • Method ChessPiece.IsThereOpponentPiece(position) [protected]
     • Implement Rook.PossibleMoves
     • Method ChessMatch.ValidadeTargetPosition
     • OOP Topics:
        - Polymorphism
        - Encapsulation / access modifers [protected]
        - Exceptions
____________________________________________________________________________________________________________________________________________________

        Printing possible moves

    Checklist:
     • Method ChessMatch.PossibleMoves
     • Method UI.PrintBoard [overload]
     • Refactor main program logic
     • OOP Topics:
        - Overloading // SOBRECARGA - QUANDO VOCE TEM MAIS DE UMA VERSÃO DO MESMO MÉTODO VARIANDO A LISTA DE PARAMETRO,
____________________________________________________________________________________________________________________________________________________

         Implementing possible moves of king

    Checklist:
     • Method King.CanMove[position) [private]
     • Implement King.PossibleMoves
     • OOP Topics:
       - Encapsulation
       - Polymorphism

        O rei.
King pode mover apenas uma casa em qualquer uma das direções


 Desclaimer - Nessa aula implementamos possiveis movimentos do King
 Usamos conceitos  Orientados a Objeto de Encapsulamento,
 e Polimorfismo, uma vez que estamos implementando comportamentos diferentes para cada tipo de peça

____________________________________________________________________________________________________________________________________________________

        Switching player each turn

     Checklist:
      • Class ChessMatch:
        - Properties Turn, CurrentPlayer [private set]
        - Method NextTum [private]
        - Update PerformChessMove
        - Update ValidadeSourcePosition
      • Method UI.PrintMatch
      • OOP Topics:
        - Encapsulation
        - Exceptions

       - Nesta aula implementamos troca de turno e aplicamos conceitos de POO de encapsulamento e exceções
____________________________________________________________________________________________________________________________________________________

        Handling captured pieces

     Checklist:
      • Method UI.PrintCapturedPieces
      • Update UI.PrintMatch
      • Update Program logic
      • Lists in ChessMatch: _piecesOnTheBoard, _capturedPieces
        - Update constructor
        - Update PlaceNewPiece
        - Update makeMove
      • OOP Topics:
        - Encasulation
        - Constructors
      • Data Structures Topics:
        - List

      - Nesta aula fizemos o tratamento das peças capturadas e aplicamos POO Encapsulamento e Construtores
____________________________________________________________________________________________________________________________________________________

        Check logic

      Rules: / SIGNFICA QUE O SEU KING ESTÁ SOBRE AMEAÇA DE PELO MENOS UMA PEÇA DO SEU OPONENTE
       • Check means your king under threat by at least one opponent piece
       • You can't put yourself in check / VC NÃO PODE SE COLOCAR EM CHECK

     Checklist:
       • Property ChessPiece.ChessPosition [get]
       • Class ChessMatch:
         - Method UndoMove
         - Property Check [private set]
         - Method Opponent [private]
         - Method King(color) [private]
         - Method TestCheck
         - Update PerformChessMove
       • Update UI.PrintMatch
____________________________________________________________________________________________________________________________________________________

        Checkmate logic
    Checklist:
      • Class ChessMatch:
        - Property Checkmate [private set]
        - Method testCheckmate [private]
        - Update PerformChessMove
      • Update UI.PrintMatch
      • Update Program logic
____________________________________________________________________________________________________________________________________________________

        Piece move count // CONTAGEM DOS MOVIMENTOS DAS PEÇAS

    Checklist:
        • Class ChessPiece:
            - Property MoveCount [private set]
            - Method IncreaseMoveCount [protected]
            - Method DecreaseMoveCount [protected]
        • Class ChessMatch:
            - Update MakeMove
            - Update UndoMove
        • OOP Topics:
            - Encapsulation
____________________________________________________________________________________________________________________________________________________

        Pawn

    Checklist:
        • Class Pawn:
        • Update ChessMatch.InitialSetup
        • ooP Topics:
            - Inheritance
            - Encapsulation
            - Polymorphism

// O PEÃO SÓ PODE SE MOVER UMA CASA PRA FRENTE, SE FOR O PRIMEIRO MOVIMENTO, ELE PODE SE MOVER DUAS CASAS,
SE TIVER UMA PEÇA ADVERSARIA NA DIAGONAL, ELE PODE CAPTURA-LA,
O PEÃO PRETO SEMPRE SE MOVE PRA BAIXO, E O PEÃO BRANCO SEMPRE SE MOVE PRA CIMA
____________________________________________________________________________________________________________________________________________________

        Bishop

    Checklist:
        • Class Bishop
        • Update ChessMatch.InitialSetup
        • OOP Topics:
            - Inheritance
            - Encapsulation
            - Polymorphism
// AS REGRAS BASICAS DO BISPO É QUE ELE SE MOVE NA DIAGONAL, ELE PODE SE MOVER QUANTAS CASAS QUISER,
ELE NÃO PODE PULAR PEÇAS, ELE PODE CAPTURAR PEÇAS ADVERSARIAS,
____________________________________________________________________________________________________________________________________________________

        Knight

    Checklist:
        • Class Knight
        • Update ChessMatch.InitialSetup
        • OOP Topics:
            - Inheritance
            - Encapsulation
            - Polymorphism
// O CAVALO TEM 8 MOVIMENTOS POSSIVEIS, ELE SE MOVE EM L, ELE PODE PULAR PEÇAS, ELE PODE CAPTURAR PEÇAS ADVERSARIAS,
____________________________________________________________________________________________________________________________________________________

        Queen

    Checklist:
        • Class Queen
        • Update ChessMatch.InitialSetup
        • OOP Topics:
            - Inheritance
            - Encapsulation
            - Polymorphism
// A RAINHA TEM OS MOVIMENTOS DA TORRE E DO BISPO, ELA PODE SE MOVER QUANTAS CASAS QUISER, ELA NÃO PODE PULAR PEÇAS,
ELA PODE MOVER TANTO NA HORIZONTAL, QUANTO NA VERTICAL(CONFORME A TORRE)  QUANTO NA DIAGONAL(CONFORME O BISHOP
____________________________________________________________________________________________________________________________________________________

        Special move - Castling // ROQUE
     Checklist:
        • Update King
        • Update ChessMatch.MakeMove
        • Update ChessMatch.UndoMove
// JOGADA ESPECIAL DO XADREZ, ONDE O REI SE MOVE DUAS CASAS PARA A ESQUERDA OU PARA A DIREITA, E A TORRE MOVE PARA A CASA DO LADO DO REI


____________________________________________________________________________________________________________________________________________________

        Special move - En passant // CAPTURA EN PASSANT
    Checklist:
        • Register a pawn which can be captured en passant by en passant on next turn
            - Property ChessMatch.EnPassantVulnerable
            - Update ChessMatch.PerformChessMove
        • update Pawn.PossibleMoves
        • Update ChessMatch.MakeMove
        • Update ChessMatch.UndoMove
        • Update ChessMatch.InitialSetup
// CAPTURA EN PASSANT, É UMA JOGADA ESPECIAL DO XADREZ, ONDE O PEÃO SE MOVE DUAS CASAS, E O ADVERSARIO PODE CAPTURA-LO COMO SE ELE TIVESSE SE MOVIMENTADO APENAS UMA CASA
____________________________________________________________________________________________________________________________________________________

        Special move - Promotion // PROMOÇÃO
    Checklist:
        • Property ChessMatch.Promoted
        • Update ChessMatch.PerformChessMove
        • Method ChessMatch.ReplacePromotedPiece
        • Update Program logic
// PROMOÇÃO É UMA JOGADA ESPECIAL DO XADREZ, ONDE O PEÃO CHEGA NO OUTRO LADO DO TABULEIRO, E ELE PODE SER PROMOVIDO A UMA OUTRA PEÇA, QUE NÃO SEJA O PEÃO

