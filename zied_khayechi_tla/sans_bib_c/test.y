%{
#include<stdio.h>
#include<stdlib.h>
int yyparse();
int yylex();
int yyerror(char *s);
extern FILE* yyin;
%}
%union {int num; char id;} 
%token  <num>entier
%token <id>chaine
%token ident ent fmain define si sinon tantque lire ecrire affect
%token supegal
%left  plus moins
%right mult divi
%token virg pointvirg parouv parfer  cot por lettre 
%token inf infegal supergal egal diff sup
%token accouv accfer
%token fdf
%start FICHIER
%%
FICHIER : PROGRAMME fdf
;
PROGRAMME : DECL_CONST PROGRAMME2 
		| DECL_VAR PROG 
		| PROG
;
PROGRAMME2 : DECL_VAR  PROG 
		| PROG
;
DECL_CONST : define ident entier DECL_CONST2
;
DECL_CONST2 : 
;
DECL_VAR : ent ident DECL_VAR2
;
DECL_VAR2 : SUITE_VAR pointvirg DECL_VAR3 
			| pointvirg DECL_VAR
;
DECL_VAR3 : DECL_VAR 
			| 
;
SUITE_VAR : virg ident SUITE_VAR2
;
SUITE_VAR2 : SUITE_VAR 
			| 
;
PROG : fmain parouv parfer BLOC
      
;
BLOC : accouv BLOC2
;
BLOC2 : AUTRES_INST accfer
;
AUTRES_INST : INSTRUCTION AUTRES_INST
			| 
;
INSTRUCTION : CONDITIONNELLE 
			| ITERATION 
			| AFFECTATION 
			| LECTURE 
			| ECRITURE
;
CONDITIONNELLE : si parouv EXP parfer BLOC SUITE_COND
;
SUITE_COND : 
			| sinon BLOC
;
ITERATION : tantque parouv EXP parfer BLOC
;
AFFECTATION : ident affect EXP pointvirg
;

LECTURE : lire parouv ident  parfer pointvirg
	|lire parouv AJOUT ident  parfer pointvirg
;
AJOUT : cot por lettre por cot virg 
;
ECRITURE : ecrire parouv ECRITURE2
;
ECRITURE2 : parfer pointvirg                                            
| EXP_OU_CH ECRITURE3                                                  
;
ECRITURE3  :  AUTRES_ECRI  parfer  pointvirg               
| parfer pointvirg                                                              
;
AUTRES_ECRI : virg EXP_OU_CH AUTRES_ECRI2
;
AUTRES_ECRI2 : AUTRES_ECRI 
			|
;
EXP_OU_CH : EXP 
			| chaine
;
EXP : TERME EXP2
;
EXP2 : OP_BIN EXP 
		| OP_REL EXP 
		| 
;
TERME : entier 
		| ident 
		| parouv EXP parfer 
		| moins TERME
;
OP_BIN : plus 
		| moins 
		| mult 
		| divi
;
OP_REL : inf | infegal | diff | supegal |sup | egal 
;
%%
int yyerror(char *s)
{ 
printf ("%s \n",s);
return 1;
}


void main(int argc, char* argv[]) {
printf("\n\n[*]Fichier %s  :\n",argv[1]);
yyin=fopen(argv[1],"r");
if (yyin>0)printf("       [*]fichier DISPONIBLE\n");
else printf("             [*]fichier NON disonible");
if (yyparse()==0) printf("       [*]Test syntaxique est validé \n\n");
else yyerror("                   [*]ERRUR syntaxique");
}
