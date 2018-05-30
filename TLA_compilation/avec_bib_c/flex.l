%{
#include <stdlib.h>
#include <stdio.h>
#include "bison.tab.h" 
extern YYSTYPE yylval;

%}



lettre [a-zA-Z]
chaine [a-zA-Z]+
chiffre [0-9]
underscore "_"
id {lettre}({lettre}|{chiffre}|{underscore})*
nb {chiffre}{chiffre}*
fmain "main"
define "DEFINE"
lire "scanf"
ecrire "printf"
tantque "while"
si "if"
sinon "else"
fdf "<<EOF>>"


%%

{fmain} { return(fmain); }
{define} { return(define); }
{lire} { return(lire); }
{ecrire} { return(ecrire);}
{si} { return(si); }
{sinon} { return(sinon); }
{tantque} {return(tantque);}
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

{lettre} {return lettre;}
{chiffre} {return entier;}
{nb} { yylval.num = atoi(yytext); return(ent); }
{chaine} { return(chaine); }
{id} { yylval.id = yytext[0]; return(ident); }
[ \n\t]   ;
.        ; 
%%

