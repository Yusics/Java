JCC = javac
JVM = java
CLASSPATH = ../lib/


all: 
	cd src && $(JCC) -d $(CLASSPATH) -cp $(CLASSPATH) PlayerB03202047.java && $(JCC) -d $(CLASSPATH) -cp $(CLASSPATH) POOCasino.java
	
run: 
	cd lib && $(JVM) POOCasino 100 100000 PlayerB03202047 PlayerB03202017 PlayerB03202047 PlayerB03202017

clean: 
	cd lib && rm PlayerB03202047.class && rm POOCasino.class