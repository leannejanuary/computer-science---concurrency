# Simple Makefile to compile Main.java, CloudData.java and Vector.java 

BINDIR=./bin
SRCDIR=./src
DOCDIR=./javadocs


.SUFFIXES: .java .class

# General build rule: .java => .class
# This allows dependencies in different directories
# -cp => specified CLASSPATH
# -d  => where to write .class files

${BINDIR}/%.class: ${SRCDIR}/%.java
	javac $< -cp ${BINDIR} -d ${BINDIR} 

# first build rule
${BINDIR}/Main.class: ${BINDIR}/CloudData.class  ${BINDIR}/Vector.class ${BINDIR}/SumArr.class ${BINDIR}/SumArr2.class ${SRCDIR}/Main.java

# second build rule
${BINDIR}/SumArr.class: ${BINDIR}/CloudData.class ${BINDIR}/Vector.class ${SRCDIR}/SumArr.java

# third build rule
${BINDIR}/SumArr2.class: ${BINDIR}/CloudData.class ${BINDIR}/Vector.class ${SRCDIR}/SumArr2.java

# fourth build rule
${BINDIR}/CloudData.class: ${BINDIR}/Vector.class ${SRCDIR}/CloudData.java

# fifth build rule
${BINDIR}/Vector.class: ${SRCDIR}/Vector.java


clean:
	rm -f ${BINDIR}/*.class

run:
	java -cp ./bin C

docs:
	javadoc  -classpath ${BINDIR} -d ${DOCDIR} ${SRCDIR}/*.java

cleandocs:
	rm -rf ${DOCDIR}/*


