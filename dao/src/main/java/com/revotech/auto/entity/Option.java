package com.revotech.auto.entity;

/**
 * Class {@code Option} is an entity which defines necessary fields for option.
 * @author Revotech
 */
public class Option {
    private int id;
    private String label;

    public int getId() { return id; }
    public String getLabel() { return label; }
    public void setId(int id) { this.id = id; }
    public void setLabel(String label) { this.label = label; }

    public Option(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public Option() { super(); }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
