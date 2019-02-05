exec: \
compile
	

compile: \
TMAN.java
	javac -cp ".:jfreechart-1.0.19.jar:jcommon-1.0.23.jar" TMAN.java
	
