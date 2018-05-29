exe:bison.tab.c lex.yy.c
	gcc  lex.yy.c bison.tab.c  -lfl -o exe
bison.tab.c: bison.y
	bison  -d bison.y
lex.yy.c: flex.l
	lex flex.l 

