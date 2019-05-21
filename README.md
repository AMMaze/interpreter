# interpreter

Interpreter for model language with following grammar:

<character>  ::= "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" | "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" | "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z" | "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z" | "_"
<digit>   ::= "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
<number> ::= <digit> | <digit> <number>
<identifier> ::= <character> | <identifier> <character>
<operation> ::= "+" | "-" | "*" | "/" | "%" | ">" | "<" | "="

<constant-expression> ::= "-" <number> | <number>
<binary-expression> ::= "(" <expression> <operation> <expression>  ")"
<argument-list> ::= <expression> | <expression> "," <argument-list>
<call-expression> ::= <identifier> "(" <argument-list> ")"
<if-expression> ::= "[" <expression> "]?(" <expression> "):("<expression>")"


<expression> ::= <identifier>
                  | <constant-expression>
                  | <binary-expression>
                  | <if-expression>
                  | <call-expression>

<parameter-list> ::= <identifier> | <identifier> "," <parameter-list>

<function-definition> ::= <identifier>"(" <parameter_list> ")" "={" <expression> "}"

<function-definition-list> : ""
                             | <function-definition> <EOL>
                             | <function-definition> <EOL> <function-definition-list>
