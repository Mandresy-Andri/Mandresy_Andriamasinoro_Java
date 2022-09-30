package com.company.Summative1AndriamasinoroMandresy.models;

import java.util.Objects;

public class Definition {

    private int id;
    private String word;
    private String definition;

    public Definition() {}

    public Definition(String word, String definition, int id){
        this.word=word;
        this.definition=definition;
        this.id=id;
    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    public String getWord() {return word; }

    public void setWord(String word) { this.word = word; }

    public String getDefinition() { return definition; }

    public void setDefinition(String definition) { this.definition = definition; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Definition that = (Definition) o;
        return Objects.equals(word, that.word) && Objects.equals(definition, that.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, definition);
    }
}
