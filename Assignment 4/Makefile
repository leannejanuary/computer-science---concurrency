SRC=src
BIN=bin
DOC=doc

SRC_FILES=$(wildcard $(SRC)/*.java)
BIN_FILES=$(SRC_FILES:$(SRC)/%.java=$(BIN)/%.class)

COMPILER = javac
COMPILER_FLAGS = -d $(BIN) -sourcepath $(SRC)

build: $(BIN_FILES)

$(BIN)/%.class: $(SRC)/%.java
	$(COMPILER) $(COMPILER_FLAGS) $^

DOC_COMPILER_FLAGS = -d $(DOC)
docs:
	javadoc $(DOC_COMPILER_FLAGS) $(SRC)/*

clean:
	rm -rf $(BIN)/*
	rm -rf $(DOC)/*
