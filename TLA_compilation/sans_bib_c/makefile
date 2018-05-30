exe:   test.tab.c lex.yy.c
	gcc  lex.yy.c test.tab.c  -lfl -o exe

test.tab.c: test.y
	bison  -d test.y
lex.yy.c: test.l
	lex test.l 

