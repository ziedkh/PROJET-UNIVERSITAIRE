%{
#include "my_syntax.h"
#include <stdlib.h>
#include <stdio.h>
#include "test.tab.h" 
extern YYSTYPE yylval;

%}

lettre [a-zA-Z]
chaine [a-zA-Z]+
chiffre [0-9]
underscore "_"
id {lettre}({lettre}|{chiffre}|{underscore})*
fdf "<<EOF>>"

%%
"VAR" {return(INT);}
"constant" {return(DEFINE);}
"main" { return(FMAIN); }
"lire" { return(LIRE); }
"ecrire" {return ECRIRE;}
"si" { return(SI); }
"sinon" { return(SINON); }
"tantque" {return(TANTQUE);}
{fdf} { return(fdf); }
\" { return(cot); }
\% { return(por); }
\= { return(affect); }
\+ { return(plus); }
\- { return(moins); }
\* { return(mult); }
\/ { return(divi); }
\, { return(virg); }
\; { return(pointvirg); }
\( { return(parouv); }
\) { return(parfer); }
\< { return(inf); }
\<= { return(infegal); }
\== { return(egal); }
\!= { return(diff); }
\> { return(sup); }
\>= { return(supergal); }
\{ { return(accouv); }
\} { return(accfer); }
{chiffre} {  yylval.num = atoi(yytext); return entier;}
{chaine} { return(chaine); }
{id} { yylval.id = yytext[0]; return(ident); }
[ \n\t]   ;
.        ; 
%%

